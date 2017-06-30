package com.coorchice.ban.model.news.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import com.coorchice.ban.model.base.BaseRecyclerViewHolder
import com.coorchice.ban.model.base.OnItemViewClickListener
import com.coorchice.ban.model.news.adapter.ItemView.NewsNavigatorListItemView
import com.coorchice.ban.model.news.entry.NewsNavigation
import com.coorchice.ban.utils.ScaleAnimation
import com.coorchice.ban.utils.playScaleAnimation

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/10
 * Notes:
 */
class NewsNavigatorListAdapter(val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onItemViewClickListener: OnItemViewClickListener<NewsNavigation>? = null
    var selectedItem: Int = 0
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(viewGroup: ViewGroup?, position: Int): RecyclerView.ViewHolder {
        return BaseRecyclerViewHolder(NewsNavigatorListItemView(context))
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder?, position: Int) {
        val itemView = viewHolder?.itemView as NewsNavigatorListItemView
        itemView.setData(NewsNavigation.values()[position], position)
        itemView.onItemViewClickListener = onItemViewClickListener
        playScaleAnimation(itemView, 1f, 0, LinearInterpolator())
        itemView.alpha = 0.5f
        if (selectedItem == position) {
            playScaleAnimation(itemView, 1.02f, 300, AccelerateInterpolator())
            itemView.alpha = 1f
        }
    }

    override fun getItemCount(): Int {
        return NewsNavigation.values().size
    }


}