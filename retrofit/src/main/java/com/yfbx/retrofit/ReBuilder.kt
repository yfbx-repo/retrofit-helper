package com.yfbx.retrofit

import com.yfbx.retrofit.converter.EmptyConverterFactory
import com.yfbx.retrofit.cookie.PersistentCookies
import com.yfbx.retrofit.interceptor.LoggerInterceptor
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @Author: Edward
 * @Date: 2019/06/15
 * @Description:
 */


class ReBuilder(val baseUrl: String) {

    val client = OkHttpClient.Builder()
    val builder = Retrofit.Builder()
    val headers = Headers.Builder()


    /**
     * 添加请求头
     */
    fun addHeader(line: String): ReBuilder {
        headers.add(line)
        return this
    }


    /**
     * 添加请求头
     */
    fun addHeader(key: String, value: String): ReBuilder {
        headers[key] = value
        return this
    }

    /**
     * 添加请求头
     */
    fun addHeaders(vararg pairs: Pair<String, String>): ReBuilder {
        pairs.forEach { headers[it.first] = it.second }
        return this
    }


    /**
     * 添加拦截器
     */
    fun addInterceptor(interceptor: Interceptor): ReBuilder {
        client.addInterceptor(interceptor)
        return this
    }


    /**
     * Cookie 持久化
     */
    fun persistentCookies(): ReBuilder {
        client.cookieJar(PersistentCookies)
        return this
    }

    /**
     * Debug模式下 打印log
     */
    fun debug(debug: Boolean): ReBuilder {
        if (debug) {
            //最好最后添加LoggerInterceptor，如果先添加，后面添加的Interceptor信息不打印
            client.addInterceptor(LoggerInterceptor())
        }
        return this
    }


    /**
     * Gson解析器
     */
    fun addGsonConverter(): ReBuilder {
        //空数据解析器，防止没有返回数据时，Gson解析出错
        builder.addConverterFactory(EmptyConverterFactory())
        builder.addConverterFactory(GsonConverterFactory.create())
        return this
    }


    fun build(): Retrofit {
        return builder.client(client.build()).build()
    }

}



