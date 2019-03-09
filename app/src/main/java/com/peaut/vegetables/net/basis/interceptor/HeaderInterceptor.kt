package com.peaut.vegetables.net.basis.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author peaut
 * @package  com.peaut.httpmodule.http.basis.interceptor
 * @fileName HeaderInterceptor
 * @date on  2019/2/14  16:25
 */
class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
                .addHeader("Accept-Encoding","gzip")
                .addHeader("Accept","application/json")
                .addHeader("Content-Type","application/json; charset=utf-8")
                .method(originalRequest.method(),originalRequest.body())
        return chain.proceed(requestBuilder.build())
    }
}