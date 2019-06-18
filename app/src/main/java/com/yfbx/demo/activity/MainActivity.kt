package com.yfbx.demo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yfbx.demo.R
import com.yfbx.demo.net.Net
import com.yfbx.demo.net.UserApi
import com.yfbx.retrofit.RetrofitLoader
import com.yfbx.retrofit.callback.FileCallback
import com.yfbx.retrofit.callback.NetCallback
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        loginBtn.setOnClickListener {
            login()
        }
    }


    /**
     * 请求
     */
    private fun login() {
        Net.create<UserApi>().login("13761687434", "", "123456", 2).enqueue(NetCallback {


        })

    }


    /**
     * 下载
     */
    private fun download(targetFile: File) {
        RetrofitLoader.download("").enqueue(FileCallback(targetFile).onProgress { load, total ->


        })

    }
}
