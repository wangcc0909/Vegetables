package com.peaut.vegetables.net.basis.interceptor

import com.peaut.vegetables.net.basis.exception.ConnectionException
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody

/**
 * @author peaut
 * @package  com.peaut.httpmodule.http.basis.interceptor
 * @fileName HttpInterceptor
 * @date on  2019/2/14  16:06
 */
class HttpInterceptor: Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var originalResponse: Response
        try {
            originalResponse = chain.proceed(request)
        }catch (e: Exception) {
            throw ConnectionException()
        }
        /*if (originalResponse.code() != 200){
            throw ResultInvalidException()
        }*/
        val source = originalResponse.body()?.source()
        source?.request(Long.MAX_VALUE)
        val byteString = source?.buffer()?.snapshot()?.utf8()
        val responseBody = ResponseBody.create(null,byteString)
        return originalResponse.newBuilder().body(responseBody).build()
    }
}