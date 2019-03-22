package com.peaut.vegetables.qrhandle

import android.content.Context
import com.peaut.vegetables.common.Constant
import com.peaut.vegetables.qrhandle.activity.WebActivity
import com.peaut.vegetables.util.intent
import com.peaut.vegetables.util.isWebURL

/**
 * @author peaut
 * @package  com.peaut.vegetables.qrhandle
 * @fileName WebURLHandler
 * @date on  2019/3/22  16:36
 */
class WebURLHandler : QRHandler {
    constructor(context: Context) : super(context)
    constructor(context: Context, next: QRHandler?) : super(context, next)

    override fun handle(msg: String) {
        context.startActivity(context.intent<WebActivity> {
            putExtra(Constant.WEB_URL,msg)
        })
    }

    override fun canHandle(msg: String): Boolean {
        return msg.isWebURL()
    }
}