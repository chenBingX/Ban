package com.coorchice.ban.model.base

import com.coorchice.ban.network.DataModel.ConstellationInfo
import com.coorchice.ban.network.DataModel.News
import com.coorchice.ban.network.DataModel.WechatNews

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/11
 * Notes:
 */
interface INewsListFragment {
    fun updateNewsData(data: List<News>?)
}

interface IWechatNewsFragment {
    fun updateWechatNewsData(data: List<WechatNews>?)

    fun onSpecialSituation(message: String?)
}

interface IJokeFragment<T> {
    fun updateJokeData(data: List<T>?)
}

interface IConstellationFragment{
    fun updateConstellationData(data: ConstellationInfo?)
}