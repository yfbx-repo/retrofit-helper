package com.yfbx.retrofit.context

import android.app.Activity
import android.app.Application
import android.os.Bundle
import java.lang.ref.WeakReference

/**
 * @Author: Edward
 * @Date: 2019/06/17
 * @Description:生命周期监听
 */
class StackTop : Application.ActivityLifecycleCallbacks {


    companion object {

        var refs: WeakReference<Activity?>? = null
        var isAlive = false

    }


    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        print("-----------Created-------------")
        refs = WeakReference(activity)
        isAlive = true
    }

    override fun onActivityStarted(activity: Activity?) {
        print("-----------Started-------------")
    }

    override fun onActivityResumed(activity: Activity?) {
        print("-----------Resumed-------------")
    }

    override fun onActivityPaused(activity: Activity?) {
        print("-----------Paused-------------")
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        print("-----------SaveInstance-------------")
    }

    override fun onActivityStopped(activity: Activity?) {
        print("-----------Stopped-------------")
    }

    override fun onActivityDestroyed(activity: Activity?) {
        print("-----------Destroyed-------------")
        isAlive = false
    }
}