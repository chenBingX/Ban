package com.coorchice.ban.model.base

import com.coorchice.ban.network.DataModel.JokePicture
import com.coorchice.ban.network.DataModel.JokeWord
import com.coorchice.ban.network.DataModel.NewsResponse
import com.coorchice.ban.network.DataModel.WechatNewsResponse

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