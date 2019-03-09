package com.peaut.vegetables.net.basis

import com.peaut.vegetables.BuildConfig
import com.peaut.vegetables.net.basis.config.HttpCode
import com.peaut.vegetables.net.basis.exception.ServerResultException
import com.peaut.vegetables.net.basis.interceptor.HeaderInterceptor
import com.peaut.vegetables.net.basis.interceptor.HttpInterceptor
import com.peaut.vegetables.net.basis.model.BaseResponseBody
import com.peaut.vegetables.util.applicationContext
import com.readystatesoftware.chuck.ChuckInterceptor
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit


/**
 * @author peaut
 * @package  com.peaut.httpmodule.http.basis
 * @fileName RetrofitManagement
 * @date on  2019/2/14  15:37
 */
class RetrofitManagement private constructor() {
    private object Holder {
        val INSTANCE = RetrofitManagement()
    }

    companion object {
        val instance: RetrofitManagement by lazy { Holder.INSTANCE }
        val READ_TIMEOUT: Long = 6000
        val WRITE_TIMEOUT: Long = 6000
        val CONNECT_TIMEOUT: Long = 6000
        val serviceMap: ConcurrentHashMap<String, Any> = ConcurrentHashMap()
    }

    private fun createRetrofit(url: String): Retrofit {
        val builder = OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(HttpInterceptor())
                .addInterceptor(HeaderInterceptor())
//                .addInterceptor(FilterInterceptor())
                .retryOnConnectionFailure(true)
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(httpLoggingInterceptor)
            builder.addInterceptor(ChuckInterceptor(applicationContext))
        }
        val client = builder.build()
        return Retrofit.Builder()
                .client(client)
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    fun <T> applySchedulers(): ObservableTransformer<BaseResponseBody<T>, T> {
        return ObservableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap { result ->
                        when (result.code) {
                            HttpCode.CODE_SUCCESS -> createData(result.data)
                            else -> throw ServerResultException(result.code, result.msg ?: "")
                        }
                    }
        }
    }

    private fun <T> createData(t: T): Observable<T> {
        return Observable.create {
            try {
                it.onNext(t)
                it.onComplete()
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }

    fun <T> getService(clz: Class<T>): T {
        return getService(clz,"http://v.juhe.cn/")
    }


    @Suppress("UNCHECKED_CAST")
    fun <T> getService(clz: Class<T>, host: String): T {
        val value: T
        if (serviceMap.containsKey(host)) {
            val obj = serviceMap[host]
            if (obj == null) {
                value = createRetrofit(host).create(clz)
                serviceMap[host] = value as Any
            } else {
                value = obj as T
            }
        } else {
            value = createRetrofit(host).create(clz)
            serviceMap[host] = value as Any
        }
        return value
    }
}