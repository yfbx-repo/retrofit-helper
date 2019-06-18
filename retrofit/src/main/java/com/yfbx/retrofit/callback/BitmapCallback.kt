package com.yfbx.retrofit.callback

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.Toast
import com.yfbx.retrofit.context.StackTop
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * @Author Edward
 * @Date 2019/4/28 0028
 * @Description:图片回调
 */
class BitmapCallback(private val listener: ((Bitmap?) -> Unit)) : Callback<ResponseBody> {


    /**
     * 响应
     */
    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
        if (response.isSuccessful && response.body() != null) {
            val bitmap = BitmapFactory.decodeStream(response.body()!!.byteStream())
            listener.invoke(bitmap)
        } else {
            onFail(response.errorBody()?.string())
        }
    }


    /**
     * 响应失败
     */
    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
        t.printStackTrace()
        onFail(t.message)
    }


    /**
     * 失败处理
     */
    private fun onFail(error: String?) {
        listener.invoke(null)
        val context = StackTop.refs?.get()
        context?.let { Toast.makeText(it, error ?: "UNKNOWN ERROR", Toast.LENGTH_SHORT).show() }
    }

}