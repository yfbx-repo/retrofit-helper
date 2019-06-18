package com.yfbx.retrofit.callback

import android.os.Handler
import android.os.Looper
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.concurrent.thread


/**
 * @Author Edward
 * @Date 2019/4/28 0028
 * @Description:文件回调
 */
class FileCallback(
    private var targetFile: File,
    private val listener: ((Boolean) -> Unit)? = null
) : Callback<ResponseBody> {


    private var onProgress: ((load: Long, total: Long) -> Unit)? = null


    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
        if (response.isSuccessful) {
            saveFile(response.body())
        } else {
            callback(false)
        }
    }

    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
        t.printStackTrace()
        callback(false)
    }


    /**
     * 进度监听
     */
    fun onProgress(onProgress: (load: Long, total: Long) -> Unit): FileCallback {
        this.onProgress = onProgress
        return this
    }


    /**
     * 保存文件
     */
    private fun saveFile(body: ResponseBody?) {
        if (body == null) {
            callback(false)
            return
        }

        thread {
            val inputStream = body.byteStream()
            val outputStream = FileOutputStream(targetFile)
            val fileReader = ByteArray(4096)

            val totalSize = body.contentLength()
            var loadSize: Long = 0

            try {
                while (true) {
                    val read = inputStream.read(fileReader)
                    if (read == -1) {
                        break
                    }
                    outputStream.write(fileReader, 0, read)
                    loadSize += read
                    onProgress(loadSize, totalSize)
                }
                outputStream.flush()
                //完成，回调
                callback(true)
            } catch (e: IOException) {
                e.printStackTrace()
                callback(false)
            } finally {
                inputStream.close()
                outputStream.close()
            }
        }
    }


    /**
     * 结果回调
     */
    private fun callback(isSuccess: Boolean) {
        Handler(Looper.getMainLooper()).post { listener?.invoke(isSuccess) }
    }

    /**
     * 进度回调
     */
    private fun onProgress(loadSize: Long, totalSize: Long) {
        Handler(Looper.getMainLooper()).post { onProgress?.invoke(loadSize, totalSize) }
    }

}