package com.coorchice.ban.model.joke.adapter.itemview

import android.content.Context
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.coorchice.ban.R
import com.coorchice.ban.network.DataModel.JokePicture
import kotlinx.android.synthetic.main.item_view_joke_picture_list.view.*

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/26
 * Notes:
 */
class JokePictureListItemView(context: Context) : RelativeLayout(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.item_view_joke_picture_list, this, true)
        initView()
    }

    private fun initView() {

    }

    fun setData(data: JokePicture?, position: Int) {
        sd_pic.setImageURI(data?.url)
        tv_content.text = data?.content
        tv_time.text = data?.updatetime?.substring(0, 10)
    }
}