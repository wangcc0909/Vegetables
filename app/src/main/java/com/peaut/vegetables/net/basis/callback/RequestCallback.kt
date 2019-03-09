package com.peaut.vegetables.net.basis.callback

/**
 * @author peaut
 * @package  com.peaut.httpmodule.http.basis
 * @fileName RequestCallback
 * @date on  2019/2/14  15:15
 */
interface RequestCallback<T> {
    fun onSuccess(t: T)
}