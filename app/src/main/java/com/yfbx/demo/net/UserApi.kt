package com.yfbx.demo.net

import com.yfbx.demo.bean.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @Author Edward
 * @Date 2019/4/28 0028
 * @Description:
 */
interface UserApi {


    @FormUrlEncoded
    @POST("sys/login")
    fun login(
            @Field("mobile") user: String,
            @Field("code") code: String,
            @Field("password") password: String,
            @Field("type") type: Int
    ): Call<User>
}