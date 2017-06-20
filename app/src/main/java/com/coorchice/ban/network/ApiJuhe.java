package com.coorchice.ban.network;

import com.coorchice.ban.network.DataModel.NewsResponse;
import com.coorchice.ban.network.DataModel.WechatNewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/10
 * Notes:
 */

public interface ApiJuhe {

  @GET("toutiao/index")
  Call<BaseResponse<NewsResponse>> getNewsData(@Query("key") String key,
      @Query("type") String type);

  @GET("weixin/query")
  Call<BaseResponse<WechatNewsResponse>> getWechatNewsData(@Query("key") String key,
      @Query("pno") int pno, @Query("ps") int ps);

}
