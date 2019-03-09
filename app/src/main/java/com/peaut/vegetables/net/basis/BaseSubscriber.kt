package com.peaut.vegetables.net.basis

import android.net.ParseException
import android.widget.Toast
import com.google.gson.JsonParseException
import com.peaut.vegetables.R
import com.peaut.vegetables.net.basis.callback.RequestCallback
import com.peaut.vegetables.net.basis.callback.RequestMultiplyCallback
import com.peaut.vegetables.net.basis.config.HttpCode
import com.peaut.vegetables.net.basis.exception.ExceptionReason
import com.peaut.vegetables.net.basis.exception.base.BaseException
import com.peaut.vegetables.util.applicationContext
import com.peaut.vegetables.viewmodel.base.BaseViewModel
import io.reactivex.observers.DisposableObserver
import org.json.JSONException
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.UnknownHostException

/**
 * @author peaut
 * @package  com.peaut.httpmodule.http.basis
 * @fileName BaseSubscriber
 * @date on  2019/2/14  17:40
 */
class BaseSubscriber<T> : DisposableObserver<T> {
    private var baseViewModel: BaseViewModel? = null
    private var requestCallback: RequestCallback<T>? = null

    constructor(baseViewModel: BaseViewModel) : super() {
        this.baseViewModel = baseViewModel
    }

    constructor(baseViewModel: BaseViewModel, requestCallback: RequestCallback<T>) : super() {
        this.baseViewModel = baseViewModel
        this.requestCallback = requestCallback
    }

    override fun onComplete() {

    }

    override fun onNext(t: T) {
        requestCallback?.onSuccess(t)
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
        if (requestCallback is RequestMultiplyCallback) {
            val callback = requestCallback as RequestMultiplyCallback
            if (e is BaseException) {
                callback.onFail(e)
            } else {
                callback.onFail(BaseException(HttpCode.CODE_UNKOWN, e.message ?: ""))
            }
        } else {
            if (baseViewModel == null) {
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
            } else {
                baseViewModel?.showToast(e.message ?: "")
                if (e is HttpException) {
                    onException(ExceptionReason.BAD_NETWORK)
                } else if (e is ConnectException
                        || e is UnknownHostException) {
                    onException(ExceptionReason.CONNECT_ERROR)
                } else if (e is InterruptedIOException) {
                    onException(ExceptionReason.CONNECT_TIMEOUT)
                } else if (e is JsonParseException
                        || e is JSONException
                        || e is ParseException) {
                    onException(ExceptionReason.PARSE_ERROR)
                } else {
                    onException(ExceptionReason.UNKNOWN_ERROR)
                }
            }
        }
    }

    fun onException(reason: ExceptionReason) {
        when (reason) {
            ExceptionReason.CONNECT_ERROR -> baseViewModel?.connectError(applicationContext.getString(R.string.connect_error))
            ExceptionReason.CONNECT_TIMEOUT -> baseViewModel?.connectTimeout(applicationContext.getString(R.string.connect_timeout))
            ExceptionReason.BAD_NETWORK -> baseViewModel?.badNetwork(applicationContext.getString(R.string.bad_network))
            ExceptionReason.PARSE_ERROR -> baseViewModel?.parseError(applicationContext.getString(R.string.parse_error))
            ExceptionReason.UNKNOWN_ERROR -> baseViewModel?.unknownError(applicationContext.getString(R.string.unknown_error))
            else -> baseViewModel?.unknownError(applicationContext.getString(R.string.unknown_error))
        }
    }
}