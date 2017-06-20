package com.coorchice.ban.model.wechat.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.coorchice.ban.model.base.BaseRecyclerViewHolder
import com.coorchice.ban.model.wechat.adapter.itemview.WechatNewsListItemView
import com.coorchice.ban.network.DataModel.WechatNews

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/12
 * Notes:
 */
class WechatNewsListAdapter(var context: Context, var data : List<WechatNews>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup?, position: Int): RecyclerView.ViewHolder {
        return BaseRecyclerViewHolder(WechatNewsListItemView(context))
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder?, position: Int) {
        var itemView = viewHolder?.itemView as WechatNewsListItemView
        itemView?.setData(data?.get(position), position)
    }

    override fun getItemCount(): Int {
        return data?.size as Int
    }

    fun updateData(data: List<WechatNews>?){
        this.data = data
        notifyDataSetChanged()
    }
}