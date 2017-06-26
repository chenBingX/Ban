package com.coorchice.ban.model.joke.presenter

import com.coorchice.ban.model.base.IJokeFragment
import com.coorchice.ban.model.base.IJokeModel
import com.coorchice.ban.model.base.OnResponseListener
import com.coorchice.ban.model.joke.model.JokeModel
import com.coorchice.ban.network.DataModel.JokePicture
import com.coorchice.ban.network.DataModel.JokeWord
import com.coorchice.ban.utils.loge

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/25
 * Notes:
 */
class JokeFragmentPresenter<T>(var view: IJokeFragment<T>) {
    val model: IJokeModel = JokeModel()
    val datas = mutableListOf<T>()
    var page: Int = 0
    val rows: Int = 10

    fun requestJokeWordData(refresh: Boolean){
        page++
        if (refresh){
            page = 0
            datas.clear()
        }
        model.requestJokeWordData(page, rows, object:OnResponseListener<List<JokeWord?>?>{
            override fun onSuccess(data: List<JokeWord?>?) {
                if (data != null && !data.isEmpty()){
                    datas.addAll(data as List<T>)
                    val newDatas = mutableListOf<T>()
                    newDatas.addAll(datas)
                    view.updateJokeData(newDatas)
                } else {
                    // 没有了

                }
            }

            override fun onFailure(message: String?) {
                loge(message?:"无错误信息")
            }
        })
    }

    fun requestJokePictureData(refresh: Boolean){
        page++
        if (refresh){
            page = 0
            datas.clear()
        }
        model.requestJokePictureData(page, rows, object:OnResponseListener<List<JokePicture?>?>{
            override fun onSuccess(data: List<JokePicture?>?) {
                if (data != null && !data.isEmpty()){
                    datas.addAll(data as List<T>)
                    val newDatas = mutableListOf<T>()
                    newDatas.addAll(datas)
                    view.updateJokeData(newDatas)
                } else {
                    // 没有了

                }
            }

            override fun onFailure(message: String?) {
                loge(message?:"无错误信息")
            }
        })
    }
}