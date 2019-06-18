package com.yfbx.retrofit.context

import android.app.Application
import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

/**
 * @Author Edward
 * @Date 2019/5/29 0029
 * @Description:ContentProvider 获取Application 以注册生命周期回调
 */
class AppProvider : ContentProvider() {


    override fun onCreate(): Boolean {
        val app = context as Application
        //注册生命周期监听
        app.registerActivityLifecycleCallbacks(StackTop())
        return true
    }


    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        return null
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }


}