package com.peaut.vegetables.weight

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.annotation.StyleRes
import android.support.v7.app.AlertDialog
import android.util.SparseArray
import android.view.Gravity
import android.view.View
import androidx.annotation.IdRes
import com.peaut.vegetables.R
import com.peaut.vegetables.util.getWindowHeight
import com.peaut.vegetables.util.getWindowWidth
import com.peaut.vegetables.util.inflate

/**
 * @author peaut
 * @package  com.peaut.vegetables.weight
 * @fileName SmartDialog
 * @date on  2019/3/22  10:22
 */
class SmartDialog private constructor(
        private var context: Context,
        private var view: View?,
        @LayoutRes private var resId: Int?,
        private var width: Int,
        private var height: Int,
        private var gravity: Int,
        private var animation: Int,
        private var isAutoShow: Boolean,
        @StyleRes private var themeResId: Int
) {
    constructor(builder: Builder) : this(
            builder.context,
            builder.view,
            builder.resId,
            builder.width,
            builder.height,
            builder.gravity,
            builder.animation,
            builder.isAutoShow,
            builder.themeResId
    )

    companion object {
        const val ScaleAnimation = R.style.ScaleAnimationStyle
        const val BottomToUpAnimation = R.style.BottomInAndOutStyle

        inline fun builder(context: Context, block: Builder.() -> Unit): SmartDialog {
            return Builder(context).apply(block).build()
        }
    }

    private val views: SparseArray<View> = SparseArray()
    private var dialog: AlertDialog? = null
    private fun create() {
        if (view == null && resId == null) {
            throw IllegalArgumentException("SmartDialog: view or resId not initialization")
        }
        if (view != null && resId != null){
            resId = null
        }
        if (view == null && resId != null) {
            view = context.inflate(resId!!)
        }
        if (dialog == null) {
            dialog = AlertDialog.Builder(context, themeResId).create()
        }
        dialog?.setView(view)
        if (isAutoShow) {
            show()
        }
    }

    fun getContentView(): View {
        return view!!
    }

    fun show() {
        if (dialog?.isShowing == false) {
            dialog?.show()
            val window = dialog?.window
            window?.setGravity(gravity)
            window?.setLayout(width, height)
            window?.setWindowAnimations(animation)
        }
    }

    fun getDialog(): AlertDialog? {
        return dialog
    }

    fun dismiss() {
        if (dialog?.isShowing == true) {
            dialog?.dismiss()
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : View> getView(@IdRes viewId: Int): T {
        var view = views.get(viewId)
        if (view == null) {
            view = getContentView().findViewById(viewId)
            views.put(viewId, view)
        }
        return view as T
    }

    class Builder constructor(var context: Context) {

        var view: View? = null
        @LayoutRes var resId: Int? = null
        private var widthPercent: Float = 0.7f
        private var heightPercent: Float = 0.2f
        var width: Int = (context.getWindowWidth() * widthPercent).toInt()
        var height: Int = (context.getWindowHeight() * heightPercent).toInt()
        var gravity: Int = Gravity.CENTER
        var isAutoShow: Boolean = false
        @StyleRes
        var animation: Int = R.style.ScaleAnimationStyle
        @StyleRes
        var themeResId: Int = R.style.dialog

        fun build(): SmartDialog {
            val dialog = SmartDialog(this)
            dialog.create()
            return dialog
        }
    }
}