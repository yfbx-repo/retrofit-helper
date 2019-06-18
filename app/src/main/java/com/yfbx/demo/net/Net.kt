package com.yfbx.demo.net

import com.yfbx.demo.BuildConfig
import com.yfbx.retrofit.ReBuilder

/**
 * @Author: Edward
 * @Date: 2019/06/15
 * @Description:
 */
object Net {


    val baseUrl = "https://www.yuxiaor.com/api/v1/"


    inline fun <reified T : Any> create(): T {
        return ReBuilder(baseUrl)
            .addHeader("", "")
            .addGsonConverter()
            .debug(BuildConfig.DEBUG)
            .build()
            .create(T::class.java)
    }


}