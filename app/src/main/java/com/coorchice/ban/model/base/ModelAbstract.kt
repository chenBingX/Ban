package com.coorchice.ban.model.base

import com.coorchice.ban.network.DataModel.*

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/11
 * Notes:
 */
interface INewsModel {
    fun requestNewsData(type: String, onResponseListener: OnResponseListener<NewsResponse>)
}

interface IWeChatNewsModel {
    fun requestWechatNewsData(pno:Int, ps:Int, onResponseListener: OnResponseListener<WechatNewsResponse>)
}

interface IJokeModel {
    fun requestJokeWordData(page: Int, rows: Int, onResponseListener: OnResponseListener<List<JokeWord?>?>)

    fun requestJokePictureData(page: Int, rows: Int, onResponseListener: OnResponseListener<List<JokePicture?>?>)
}

interface IConstellationModel {
    fun requestConstellationInfo(consName: String, type: String, onResponseListener: OnResponseListener<ConstellationInfo?>)
}