package com.coorchice.ban.model.joke.model

import com.coorchice.ban.model.base.IJokeModel
import com.coorchice.ban.model.base.OnResponseListener
import com.coorchice.ban.network.DataModel.JokePicture
import com.coorchice.ban.network.DataModel.JokeWord
import com.coorchice.ban.network.Net
import com.coorchice.ban.network.OnFailureCallback
import com.coorchice.ban.network.OnSuccessCallback

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/25
 * Notes:
 */
class JokeModel : IJokeModel {

    override fun requestJokeWordData(page: Int, rows: Int, onResponseListener: OnResponseListener<List<JokeWord?>?>) {
        Net.getJokeWordData(page, rows,
                object : OnSuccessCallback<List<JokeWord?>?> {
                    override fun onSuccess(data: List<JokeWord?>?) {
                        onResponseListener.onSuccess(data)
                    }

                },
                object : OnFailureCallback<List<JokeWord?>?> {
                    override fun onFailure(message: String?) {
                        onResponseListener.onFailure(message)
                    }

                }
        )
    }

    override fun requestJokePictureData(page: Int, rows: Int, onResponseListener: OnResponseListener<List<JokePicture?>?>) {
        Net.getJokePictureData(page, rows,
                object : OnSuccessCallback<List<JokePicture?>?> {
                    override fun onSuccess(data: List<JokePicture?>?) {
                        onResponseListener.onSuccess(data)
                    }

                },
                object : OnFailureCallback<List<JokePicture?>?> {
                    override fun onFailure(message: String?) {
                        onResponseListener.onFailure(message)
                    }

                }
        )
    }
}