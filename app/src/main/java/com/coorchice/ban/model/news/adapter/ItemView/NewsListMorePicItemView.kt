package com.coorchice.ban.model.news.adapter.ItemView

import android.content.Context
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.coorchice.ban.R
import com.coorchice.ban.model.base.toWebActivity
import com.coorchice.ban.network.DataModel.News
import kotlinx.android.synthetic.main.item_view_news_list_more_pic.view.*

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/11
 * Notes:
 */
class NewsListMorePicItemView(context: Context) : RelativeLayout(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.item_view_news_list_more_pic, this, true)
        initView()
    }

    private fun initView() {

    }

    fun setData(data: News?, position: Int) {
        title.text = data?.title
        pic_1.setImageURI(data?.thumbnail_pic_s)
        pic_2.setImageURI(data?.thumbnail_pic_s02)
        pic_3.setImageURI(data?.thumbnail_pic_s03)
        author.text = data?.author_name
        time.text = data?.date
        setOnClickListener {
            toWebActivity(context, data?.url, data?.title)
        }
    }
}