package com.peaut.vegetables.weight

import android.content.Context
import android.support.annotation.StyleRes
import android.support.v7.app.AlertDialog
import android.view.Gravity
import android.view.View
import com.peaut.vegetables.R
import com.peaut.vegetables.util.getWindowHeight
import com.peaut.vegetables.util.getWindowWidth

/**
 * @author peaut
 * @package  com.peaut.vegetables.weight
 * @fileName CommonDialog
 * @date on  2019/3/20  16:35
 */
class CommonDialog private constructor(
        private var context: Context,
        private var view: View,
        private var width: Int,
        private var height: Int,
        @StyleRes private var themeResId: Int
) {
    constructor(builder: Builder) : this(
            builder.context,
            builder.view,
            builder.width,
            builder.height,
            builder.themeResId
    )

    companion object {
        inline fun builder(context: Context, block: Builder.() -> Unit): CommonDialog {
            return Builder(context).apply(block).build()
        }
    }

    private var dialog: AlertDialog? = null
    private fun create() {
        if (dialog == null) {
            dialog = AlertDialog.Builder(context, themeResId).create()
        }
        dialog?.setView(view)
    }

    fun getContentView(): View {
        return view
    }

    fun show() {
        dialog?.show()
        val window = dialog?.window
        window?.setGravity(Gravity.CENTER)
        window?.setLayout(width, height)
        window?.setWindowAnimations(R.style.ScaleAnimationStyle)
    }

    fun getDialog(): AlertDialog? {
        return dialog
    }

    fun dismiss() {
        dialog?.dismiss()
    }

    class Builder constructor(var context: Context) {

        lateinit var view: View
        var width: Int = (context.getWindowWidth() * 0.7).toInt()
        var height: Int = (context.getWindowHeight() * 0.2).toInt()
        @StyleRes
        var themeResId: Int = R.style.dialog

        fun build(): CommonDialog {
            val dialog = CommonDialog(this)
            dialog.create()
            return dialog
        }
    }
}