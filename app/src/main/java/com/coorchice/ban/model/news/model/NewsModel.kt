package com.coorchice.ban.model.news.model

import com.coorchice.ban.model.base.INewsModel
import com.coorchice.ban.model.base.OnResponseListener
import com.coorchice.ban.network.DataModel.NewsResponse
import com.coorchice.ban.network.Net
import com.coorchice.ban.network.OnFailureCallback
import com.coorchice.ban.network.OnSuccessCallback

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/11
 * Notes:
 */
class NewsModel : INewsModel {

    override fun requestNewsData(type: String, onResponseListener: OnResponseListener<NewsResponse>) {
        Net.getNewsData(type,
                object : OnSuccessCallback<NewsResponse?> {
                    override fun onSuccess(data: NewsResponse?) {
                        onResponseListener.onSuccess(data)
                    }
                },

                object : OnFailureCallback<NewsResponse?> {
                    override fun onFailure(message: String?) {
                        onResponseListener.onFailure(message)
                    }

                }
        )
    }
}