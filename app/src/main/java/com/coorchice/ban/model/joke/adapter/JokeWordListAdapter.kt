package com.coorchice.ban.model.joke.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.coorchice.ban.model.base.BaseRecyclerViewHolder
import com.coorchice.ban.model.joke.adapter.itemview.JokeWordListLeftItemView
import com.coorchice.ban.model.joke.adapter.itemview.JokeWordListRightItemView
import com.coorchice.ban.network.DataModel.JokeWord
import com.coorchice.ban.utils.loge

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/25
 * Notes:
 */
class JokeWordListAdapter(var context: Context, var data: List<JokeWord>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_LEFT = 1
    private val TYPE_RIGHT = 2

    override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): RecyclerView.ViewHolder {
        if (p1 == TYPE_LEFT) {
            loge("position = $p1, type = left")
            return BaseRecyclerViewHolder(JokeWordListLeftItemView(context))
        } else {
            loge("position = $p1, type = right")
            return BaseRecyclerViewHolder(JokeWordListRightItemView(context))
        }
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder?, p1: Int) {
        if (p0?.itemView is JokeWordListLeftItemView) {
            val itemView = p0?.itemView as JokeWordListLeftItemView
            itemView.setData(data?.get(p1), p1)
        } else {
            val itemView = p0?.itemView as JokeWordListRightItemView
            itemView.setData(data?.get(p1), p1)
        }
    }

    override fun getItemCount(): Int {
        return data?.size as Int
    }

    override fun getItemViewType(position: Int): Int {
        if ((position % 2) == 0) {
            return TYPE_LEFT
        } else {
            return TYPE_RIGHT
        }
    }


    fun updateData(data: List<JokeWord>?) {
        this.data = data
        notifyDataSetChanged()
    }
}