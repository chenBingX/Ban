package com.coorchice.ban.model.joke.adapter.itemview

import android.content.Context
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.coorchice.ban.R
import com.coorchice.ban.network.DataModel.JokeWord
import kotlinx.android.synthetic.main.item_view_joke_word_list_left.*
import kotlinx.android.synthetic.main.item_view_joke_word_list_left.view.*

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/25
 * Notes:
 */
class JokeWordListLeftItemView(context: Context) : RelativeLayout(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.item_view_joke_word_list_left, this, true)
        initView()
    }

    private fun initView() {

    }

    fun setData(data: JokeWord?, position: Int){
        tv_content.text = data?.content
    }
}