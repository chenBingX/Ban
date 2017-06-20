package com.coorchice.ban.model.news.adapter.ItemView

import android.content.Context
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.coorchice.ban.R
import com.coorchice.ban.model.base.OnItemViewClickListener
import com.coorchice.ban.model.news.entry.NewsNavigation
import kotlinx.android.synthetic.main.item_view_news_navigator_list.view.*

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/10
 * Notes:
 */
class NewsNavigatorListItemView(context: Context?) : RelativeLayout(context) {

    var onItemViewClickListener: OnItemViewClickListener<NewsNavigation>? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.item_view_news_navigator_list, this, true)
        initView()
    }

    private fun initView() {
    }

    fun setData(data: NewsNavigation, position: Int) {
        bg.solid = resources.getColor(data.bg)
        iv_icon.setImageResource(data.icon)
        tv_title.text = data.title
        setOnClickListener {
            onItemViewClickListener?.OnItemViewClick(data, position)
        }
    }


}

