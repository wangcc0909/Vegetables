package com.peaut.vegetables.qrhandle

import android.content.Context

/**
 * @author peaut
 * @package  com.peaut.vegetables.qrhandle
 * @fileName QRParserImp
 * @date on  2019/3/22  16:33
 */
class QRParserImp private constructor(context: Context): Parser {
    private var printHandler = PrintHandler(context)
    private var start = WebURLHandler(context,printHandler)
    companion object {
        private var instance: QRParserImp? = null
        @Synchronized
        fun getInstance(context: Context): QRParserImp {
            if (instance == null) {
                instance = QRParserImp(context)
            }
            return instance!!
        }
    }

    override fun parse(msg: String) {
        start.requestHandle(msg)
    }
}