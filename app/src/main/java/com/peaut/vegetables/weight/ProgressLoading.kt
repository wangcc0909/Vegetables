package com.peaut.vegetables.weight

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import com.peaut.vegetables.R

/**
 * @author peaut
 * @package  com.peaut.vegetables.weight
 * @fileName ProgressLoading
 * @date on  2019/2/25  14:46
 */
class ProgressLoading constructor(context: Context) : Dialog(context, R.style.LightProgressDialog) {
    private var animDrawable: AnimationDrawable? = null

    init {
        setContentView(R.layout.progress_dialog)
        setCancelable(true)
        setCanceledOnTouchOutside(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //获取动画
        val loadingView = findViewById<ImageView>(R.id.iv_loading)
        animDrawable = loadingView.background as AnimationDrawable
    }

    /**
     * 显示对话框
     */
    fun showLoading() {
        if (!this.isShowing) {
            this.show()
        }
        animDrawable?.start()
    }

    /**
     * 隐藏对话框
     */
    fun hideLoading() {
        if (this.isShowing) {
//            this.hide()
            this.dismiss()
        }
        animDrawable?.stop()
    }
}