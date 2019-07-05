package com.peaut.vegetables.qrhandle

import android.content.Context
import com.peaut.vegetables.common.Constant
import com.peaut.vegetables.qrhandle.activity.PrintActivity
import com.peaut.vegetables.util.intent

/**
 * @author peaut
 * @package  com.peaut.vegetables.qrhandle
 * @fileName PrintHandler
 * @date on  2019/3/22  16:53
 */
class PrintHandler: QRHandler{
    constructor(context: Context) : super(context)
    constructor(context: Context, next: QRHandler?) : super(context, next)

    override fun handle(msg: String) {
        context.startActivity(context.intent<PrintActivity> {
            putExtra(Constant.QR_RESULT,msg)
        })
    }

    override fun canHandle(msg: String): Boolean {
        return true
    }
}