package com.coorchice.ban.model.news.presenter

import com.coorchice.ban.model.base.OnResponseListener
import com.coorchice.ban.model.base.INewsModel
import com.coorchice.ban.model.news.model.NewsModel
import com.coorchice.ban.model.base.INewsListFragment
import com.coorchice.ban.network.DataModel.NewsResponse

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/11
 * Notes:
 */
class NewsListFragmentPresenter(var view: INewsListFragment) {

    val newsModel: INewsModel = NewsModel()

    fun requestNewsData(type: String) {
        newsModel.requestNewsData(type, object : OnResponseListener<NewsResponse> {
            override fun onSuccess(data: NewsResponse?) {
                view?.updateNewsData(data?.data)
            }

            override fun onFailure(message: String?) {
            }
        })
    }




}