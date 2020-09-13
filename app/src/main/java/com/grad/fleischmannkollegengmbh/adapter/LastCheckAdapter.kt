package com.grad.fleischmannkollegengmbh.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.grad.fleischmannkollegengmbh.R
import com.grad.fleischmannkollegengmbh.model.LastChecked
import kotlinx.android.synthetic.main.list_last_check_item.view.*

class LastCheckAdapter(val context: Context, val lastChecks: MutableCollection<LastChecked>) :
    RecyclerView.Adapter<LastCheckAdapter.ViewHolder>() {
    val listLastCheck = lastChecks.toList()
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(lastChecks: LastChecked?) {
            if (lastChecks != null) {
                date.text = lastChecks.date
                time.text = lastChecks.time
                agent.text = lastChecks.agent
                if (lastChecks.solved) {
                solved.setImageResource(R.drawable.ic_baseline_check_circle_24)
                }
                else{
                    solved.setImageResource(R.drawable.ic_baseline_cancle)

                }
            }

        }

        val date = view.date
        val time = view.time
        val agent = view.agent
        val solved = view.solved
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_last_check_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listLastCheck[position])

    }

    override fun getItemCount(): Int {
        return lastChecks.size
    }
}