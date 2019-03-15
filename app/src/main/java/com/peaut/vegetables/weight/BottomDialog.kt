package com.peaut.vegetables.weight

import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.Gravity
import android.view.View
import com.peaut.vegetables.R
import com.peaut.vegetables.util.getWindowHeight
import com.peaut.vegetables.util.getWindowWidth

/**
 * @author peaut
 * @package  com.peaut.yuvdemo
 * @fileName BottomDialog
 * @date on  2019/3/15  10:58
 */
//view  width height
class BottomDialog private constructor(
    private var context: Context,
    private var view: View,
    private var width: Int,
    private var height: Int
) {
    constructor(builder: Builder) : this(
        builder.context,
        builder.view,
        builder.width,
        builder.height
    )

    companion object {
        inline fun builder(context: Context, block: Builder.() -> Unit): BottomDialog {
            return Builder(context).apply(block).build()
        }
    }

    private var dialog: AlertDialog? = null
    private fun create() {
        if (dialog == null) {
            dialog = AlertDialog.Builder(context, R.style.dialog).create()
        }
        dialog?.setView(view)
    }

    fun getContentView(): View {
        return view
    }

    fun show() {
        dialog?.show()
        val window = dialog?.window
        window?.setGravity(Gravity.BOTTOM)
        window?.setLayout(width, height)
        window?.setWindowAnimations(R.style.BottomInAndOutStyle)
    }

    fun dismiss() {
        dialog?.dismiss()
    }

    class Builder constructor(var context: Context) {

        lateinit var view: View
        var width: Int = context.getWindowWidth()
        var height: Int = (context.getWindowHeight() * 0.7).toInt()
        fun build(): BottomDialog {
            val dialog = BottomDialog(this)
            dialog.create()
            return dialog
        }
    }
}