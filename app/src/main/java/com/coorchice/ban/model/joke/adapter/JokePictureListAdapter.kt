package com.coorchice.ban.model.joke.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.coorchice.ban.model.base.BaseRecyclerViewHolder
import com.coorchice.ban.model.joke.adapter.itemview.JokePictureListItemView
import com.coorchice.ban.network.DataModel.JokePicture

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/26
 * Notes:
 */
class JokePictureListAdapter(var context: Context, var data: List<JokePicture>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return data?.size as Int

    }

    override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): RecyclerView.ViewHolder {
        return BaseRecyclerViewHolder(JokePictureListItemView(context))
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder?, p1: Int) {
        val itemView = p0?.itemView
        if (itemView is JokePictureListItemView) {
            itemView.setData(data?.get(p1), p1)
        }
    }

    fun updateData(data:List<JokePicture>?){
        this.data = data
        notifyDataSetChanged()
    }
}