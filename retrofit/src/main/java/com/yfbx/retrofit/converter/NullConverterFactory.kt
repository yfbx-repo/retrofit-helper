package com.yfbx.retrofit.converter

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * @Author: Edward
 * @Date: 2019/06/15
 * @Description: 响应体 size = 0 时，必需在GsonConverterFactory之前，以避免解析异常
 */
class EmptyConverterFactory : Converter.Factory() {

    override fun responseBodyConverter(type: Type?, annotations: Array<Annotation>?, retrofit: Retrofit?): Converter<ResponseBody, *>? {
        val delegate = retrofit?.nextResponseBodyConverter<Any>(this, type!!, annotations!!)
        return Converter<ResponseBody, Any> { if (it.contentLength() == 0L) null else delegate?.convert(it) }
    }
}