package com.yfbx.demo.net

import android.app.Activity
import com.yfbx.retrofit.callback.NetCallback

/**
 * @Author: Edward
 * @Date: 2019/06/17
 * @Description:
 */
class MyCallback<T>(needLoading: Boolean = true, onSuccess: (result: T?) -> Unit)
    : NetCallback<T>(needLoading, onSuccess) {


    constructor(needLoading: Boolean = true, onSuccess: (result: T?) -> Unit, onFail: (String?) -> Unit) : this(needLoading, onSuccess) {
        this.onFail = onFail
    }

    /**
     * 自定义 Loading
     */
    override fun createLoading(activity: Activity?) {
        //TODO
    }


    /**
     * 错误处理
     */
    override fun showError(activity: Activity?, error: String?) {
        //TODO
    }
}