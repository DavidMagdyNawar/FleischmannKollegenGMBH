package com.grad.fleischmannkollegengmbh

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.grad.fleischmannkollegengmbh.adapter.SiteHeadingAdapter
import com.grad.fleischmannkollegengmbh.model.Site
import com.grad.fleischmannkollegengmbh.viewmodel.SitesViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var siteAdapter: SiteHeadingAdapter
    private var siteList: MutableList<Site> = mutableListOf()
    private lateinit var viewModel: SitesViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        viewModel = ViewModelProviders.of(this).get(
            SitesViewModel::class.java
        )



        rvSites.layoutManager  = LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.VERTICAL,
            false
        )
        siteAdapter = SiteHeadingAdapter(
            siteList,
            applicationContext
        )

        val sitesLiveData = viewModel.getDataSnapshotLiveData()
        sitesLiveData.observe(this,
            Observer<DataSnapshot?> { dataSnapshot ->
                siteList.clear()
                val d = dataSnapshot!!.children
                d.forEach {
                    val site: Site? = it.getValue(
                        Site::class.java
                    )
                    if (site != null) {
                        siteList.add(site)
                    }
                }
                siteAdapter.notifyDataSetChanged()
                rvSites.adapter = siteAdapter
            }
        )
    }
}