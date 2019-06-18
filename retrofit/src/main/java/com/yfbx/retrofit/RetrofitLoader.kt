package com.yfbx.retrofit

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

/**
 * @Author Edward
 * @Date 2019/4/28 0028
 * @Description:简单的下载器
 */
object RetrofitLoader {


    /**
     * 下载
     */
    fun download(@Url url: String): Call<ResponseBody> {
        return Retrofit.Builder()
                .client(OkHttpClient())
                .build()
                .create(LoadApi::class.java)
                .download(url)
    }


    interface LoadApi {

        @Streaming
        @GET
        fun download(@Url fileUrl: String): Call<ResponseBody>
    }

}