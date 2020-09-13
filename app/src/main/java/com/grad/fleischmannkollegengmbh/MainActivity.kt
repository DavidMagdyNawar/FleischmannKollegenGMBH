package com.grad.fleischmannkollegengmbh

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.grad.fleischmannkollegengmbh.adapter.SiteHeadingAdapter
import com.grad.fleischmannkollegengmbh.model.Site
import com.grad.fleischmannkollegengmbh.viewmodel.SitesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private lateinit var siteAdapter: SiteHeadingAdapter
    private var siteList: MutableList<Site> = mutableListOf()
    private lateinit var viewModel: SitesViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
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

        search.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                filter(s.toString());
            }
        })

    }
    private fun filter(text: String) {
        val filteredList: ArrayList<Site> = ArrayList()
        for (item in siteList) {
            if (item.siteName.toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))) {
                filteredList.add(item)
            }
        }
        siteAdapter.filterList(filteredList)
    }
}