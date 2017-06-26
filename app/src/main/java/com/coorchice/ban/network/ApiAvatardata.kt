package com.coorchice.ban.network

import com.coorchice.ban.network.DataModel.JokePicture
import com.coorchice.ban.network.DataModel.JokeWord
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

    @GET("Joke/NewstJoke")
    abstract fun getJokeWordData(@Query("key") key: String,
                                 @Query("page") page: Int,
                                 @Query("rows") rows: Int): Call<BaseResponse<List<JokeWord?>?>>

    @GET("Joke/NewstImg")
    abstract fun getJokePictureData(@Query("key") key: String,
                                    @Query("page") page: Int,
                                    @Query("rows") rows: Int): Call<BaseResponse<List<JokePicture?>?>>

}