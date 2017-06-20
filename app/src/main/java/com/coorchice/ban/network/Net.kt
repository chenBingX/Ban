package com.coorchice.ban.network

import com.chenbing.oneweather.Utils.NetworkUtils
import com.coorchice.ban.BanApplication
import com.coorchice.ban.network.DataModel.NewsResponse
import com.coorchice.ban.network.DataModel.WechatNewsResponse
import com.coorchice.ban.utils.AppUtils
import com.coorchice.ban.utils.loge
import com.coorchice.ban.utils.logv
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/10
 * Notes:
 */

object Net {
    val BASE_URL_AVATARDATA = "http://api.avatardata.cn/"
    val BASE_URL_JUHE = "http://v.juhe.cn/"

    val API_AVATARDATA = getRetrofit(BASE_URL_AVATARDATA).create(ApiAvatardata::class.java)
    val API_JUHE = getRetrofit(BASE_URL_JUHE).create(ApiJuhe::class.java)

    private fun getRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }

    private fun getOkHttpClient(): OkHttpClient {
        val networkInterceptor = { chain: Interceptor.Chain ->
            chain.proceed(
                    addRequestHeader(
                            chain.request()
                    )
            )
        }
        return OkHttpClient.Builder()
                .addNetworkInterceptor(networkInterceptor)
                .retryOnConnectionFailure(true)
                .build()
    }

    private fun addRequestHeader(request: Request): Request {
        val rq = request.newBuilder()
        val imei = AppUtils.getImei(BanApplication.appContext!!)
        if (imei != null) {
            rq.addHeader("imei", imei!!)
            logv("imei: " + imei!!)
        }

        val imsi = AppUtils.getImsi(BanApplication.appContext!!)
        if (imsi != null) {
            rq.addHeader("imsi", imsi!!)
            logv("imsi: " + imsi!!)
        }

        val networkType = NetworkUtils.getCurrentNetworkType(BanApplication.appContext!!)
        if (networkType != null) {
            rq.addHeader("net", networkType!!)
            loge("net: " + networkType!!)
        }

        val sdk = AppUtils.sdkVersion.toString()
        rq.addHeader("cv", sdk)
        loge("cv: " + sdk)

        val os = "Android"
        rq.addHeader("os", os)
        loge("os: " + os)

        val tc = AppUtils.channelName
        rq.addHeader("tc", tc)
        loge("tc: " + tc)

        val dt = AppUtils.modelVersion
        rq.addHeader("dt", dt)
        loge("dt: " + dt)
        return rq.build()
    }

    private fun <T> request(call: Call<BaseResponse<T?>>,
                            onSuccessCallback: OnSuccessCallback<T?>,
                            onFailureCallback: OnFailureCallback<T?>) {
        call.enqueue(object : Callback<BaseResponse<T?>> {
            override fun onResponse(call: Call<BaseResponse<T?>>?, response: Response<BaseResponse<T?>>?) {
                onSuccessCallback.onSuccess(response?.body()?.result)
            }

            override fun onFailure(call: Call<BaseResponse<T?>>?, t: Throwable?) {
                onFailureCallback.onFailure(t?.message)
            }
        })
    }


    val KEY_NEWS_DATA = "ca8284abeb7ce27ef05965abad467ff9"
    fun getNewsData(type: String,
                    onSuccessCallback: OnSuccessCallback<NewsResponse?>,
                    onFailureCallback: OnFailureCallback<NewsResponse?>) {
        request(API_JUHE.getNewsData(KEY_NEWS_DATA, type), onSuccessCallback, onFailureCallback)
    }

    val KEY_WECHAT_NEWS_DATA = "4b162a0fe0809fd8f8bdc8a763e314b3"
    fun getWechatNewsData(pno: Int,
                          ps: Int,
                          onSuccessCallback: OnSuccessCallback<WechatNewsResponse?>,
                          onFailureCallback: OnFailureCallback<WechatNewsResponse?>) {
        request(API_JUHE.getWechatNewsData(KEY_WECHAT_NEWS_DATA, pno, ps), onSuccessCallback, onFailureCallback)
    }


}