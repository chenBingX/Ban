package com.coorchice.ban.model.news.entry

import com.coorchice.ban.R

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/10
 * Notes:
 */
enum class NewsNavigation(val title: String, val bg : Int, val icon : Int, val requestCode : String) {

    INTERNATIONAL("国际", R.color.news_navigator_1_bg, R.drawable.international, "guoji"),
    INLAND("国内", R.color.news_navigator_2_bg, R.drawable.inland, "guonei"),
    TECHNOLOGY("科技", R.color.news_navigator_3_bg, R.drawable.technology, "keji"),
    CURIOSITY("奇闻", R.color.news_navigator_4_bg, R.drawable.curiosity, "shehui"),
    ENTERTAINMENT("娱乐", R.color.news_navigator_5_bg, R.drawable.entertainment, "yule"),
    PE("体育", R.color.news_navigator_6_bg, R.drawable.pe, "tiyu")

}