package com.grad.fleischmannkollegengmbh.adapter


import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.grad.fleischmannkollegengmbh.R
import com.grad.fleischmannkollegengmbh.model.Site
import kotlinx.android.synthetic.main.list_item_main.view.*


class SiteHeadingAdapter(
    val sites: MutableList<Site>,
    val context: Context
) : RecyclerView.Adapter<SiteHeadingAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_main, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tag = position

        holder.itemView.siteNameTV.text = sites[position].siteName
        holder.itemView.streetNameTV.text = sites[position].siteStreet

        if (sites[position].dirty) {
            holder.itemView.isDirtyIV.setImageResource(R.drawable.ic_baseline_not_reflect)
        } else {
            holder.itemView.isDirtyIV.setImageResource(R.drawable.ic_baseline_reflect)
        }

        if (sites[position].thereGap) {
            holder.itemView.isThereGapIV.setSvgColor(R.color.red)
        } else {
            holder.itemView.isThereGapIV.setSvgColor(R.color.green)
        }

        if (sites[position].lightOff) {
            holder.itemView.isLightening.setSvgColor(R.color.red)
        } else {
            holder.itemView.isLightening.setSvgColor(R.color.green)
        }



        holder.itemView.setOnClickListener {

            holder.itemView.siteDetailsCardView.isVisible =
                !holder.itemView.siteDetailsCardView.isVisible
            if (holder.itemView.siteDetailsCardView.isVisible) {
                holder.itemView.expand.setImageResource(R.drawable.ic_minus_black)
            } else {
                holder.itemView.expand.setImageResource(R.drawable.ic_plus_black)
            }
        }
        holder.itemView.siteImage.setOnClickListener {
            val gmmIntentUri =
                Uri.parse("google.navigation:q=${sites[position].siteLocation.lat},${sites[position].siteLocation.long}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            mapIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mapIntent)
        }
    }

    override fun getItemCount(): Int {
        return sites.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val tvAnimalType = view.tvMain
    }

    fun ImageView.setSvgColor(@ColorRes color: Int) =
        setColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_IN)
}