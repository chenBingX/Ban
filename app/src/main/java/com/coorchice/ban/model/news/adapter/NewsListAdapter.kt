package com.coorchice.ban.model.news.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.ViewGroup
import com.coorchice.ban.model.base.BaseRecyclerViewHolder
import com.coorchice.ban.model.news.adapter.ItemView.NewsListMorePicItemView
import com.coorchice.ban.model.news.adapter.ItemView.NewsListSinglePicItemView
import com.coorchice.ban.network.DataModel.News

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/11
 * Notes:
 */
class NewsListAdapter(val context: Context, var data: List<News>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val SINGLE_PIC = 1
    val MORE_PIC = 2

    override fun onCreateViewHolder(viewGroup: ViewGroup?, position: Int): RecyclerView.ViewHolder {
        if (getItemViewType(position) == SINGLE_PIC) {
            return BaseRecyclerViewHolder(NewsListSinglePicItemView(context))
        } else {
            return BaseRecyclerViewHolder(NewsListMorePicItemView(context))
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder?, position: Int) {
        var itemView = viewHolder?.itemView
        if (itemView is NewsListSinglePicItemView) {
            itemView.setData(data?.get(position), position)
        } else if (itemView is NewsListMorePicItemView) {
            itemView.setData(data?.get(position), position)
        }
    }

    override fun getItemCount(): Int {
        return data?.size as Int
    }

    override fun getItemViewType(position: Int): Int {
        if (TextUtils.isEmpty(data?.get(position)?.thumbnail_pic_s02)) {
            return SINGLE_PIC
        } else {
            return MORE_PIC
        }
    }

    fun updateData(data: List<News>?) {
        this.data = data
        notifyDataSetChanged()
    }
}