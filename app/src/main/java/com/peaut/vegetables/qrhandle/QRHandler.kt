package com.peaut.vegetables.qrhandle

import android.content.Context

/**
 * @author peaut
 * @package  com.peaut.vegetables.qrhandle
 * @fileName QRHandler
 * @date on  2019/3/22  16:25
 */
abstract class QRHandler {
    var context: Context
    var next: QRHandler? = null
    constructor(context: Context) : this(context, null)
    constructor(context: Context, next: QRHandler?) {
        this.context = context
        this.next = next
    }

    fun requestHandle(msg: String) {
        if (canHandle(msg)){
            handle(msg)
        }else {
            passToNext(msg)
        }
    }

    abstract fun handle(msg: String)

    private fun passToNext(msg: String) {
        if (next != null) {
            next?.requestHandle(msg)
        }else {
            onNotHandler(msg)
        }
    }

    open fun onNotHandler(msg: String) {

    }

    abstract fun canHandle(msg: String): Boolean

}