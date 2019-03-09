package com.peaut.vegetables.net.basis.callback

import com.peaut.vegetables.net.basis.exception.base.BaseException

/**
 * @author peaut
 * @package  com.peaut.httpmodule.http.basis
 * @fileName RequestMultiplyCallback
 * @date on  2019/2/14  15:16
 */
interface RequestMultiplyCallback<T>: RequestCallback<T> {
    fun onFail(e: BaseException)
}