package com.coorchice.ban.network

import com.coorchice.ban.network.DataModel.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/10
 * Notes:
 */
interface ApiAvatardata {

    @GET("Joke/QueryJokeByTime")
    abstract fun getNewsData(@Query("key") key: String,
                             @Query("time") time: String,
                             @Query("sort") sort: String,
                             @Query("page") page: Int,
                             @Query("rows") rows: Int): Call<BaseResponse<NewsResponse>>

}