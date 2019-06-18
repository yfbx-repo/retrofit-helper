package  com.yfbx.retrofit.callback

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.widget.Toast
import com.yfbx.retrofit.context.StackTop
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Author: Edward
 * Date: 2018/11/13
 * Description:
 */
open class NetCallback<T> : Callback<T> {

    var needLoading: Boolean = true
    var onSuccess: ((result: T?) -> Unit)? = null
    var onFail: ((String?) -> Unit)? = null

    private var loading: Dialog? = null
    private var activity: Activity? = null


    /**
     * 带成功回调的构造方法
     */
    constructor(needLoading: Boolean = true, onSuccess: (result: T?) -> Unit) {
        this.needLoading = needLoading
        this.onSuccess = onSuccess
    }


    /**
     * 带成功和失败回调的构造方法
     */
    constructor(needLoading: Boolean = true, onSuccess: (result: T?) -> Unit, onFail: (String?) -> Unit) {
        this.needLoading = needLoading
        this.onSuccess = onSuccess
        this.onFail = onFail
    }

    init {
        activity = StackTop.refs?.get()
        initLoading()
    }


    /**
     * 响应
     */
    override fun onResponse(call: Call<T>?, response: Response<T>?) {
        loading?.dismiss()

        //返回200
        if (response?.isSuccessful == true && StackTop.isAlive) {
            onSuccess?.invoke(response.body())
            return
        }
        //返回不是 200
        if (response?.isSuccessful == false) {
            onFailResponse(response.errorBody()?.string())
        }
    }


    /**
     * 响应失败
     */
    override fun onFailure(call: Call<T>?, t: Throwable?) {
        t?.printStackTrace()
        loading?.dismiss()
        onFailResponse(t?.message)
    }


    /**
     * 失败处理
     */
    private fun onFailResponse(error: String?) {
        //回调处理
        if (onFail != null && StackTop.isAlive) {
            onFail?.invoke(error)
            return
        }
        //统一处理
        showError(activity, error)
    }


    /**
     * Init Loading
     */
    private fun initLoading() {
        if (needLoading) {
            createLoading(activity)
            loading?.show()
        }
    }

    /**
     * 创建 loading Dialog (可自定义)
     */
    open fun createLoading(activity: Activity?) {
        loading = ProgressDialog(activity)
        loading?.setCanceledOnTouchOutside(false)
    }


    /**
     * 展示错误信息 (可自定义)
     */
    open fun showError(activity: Activity?, error: String?) {
        error?.let {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        }
    }

}
