package com.peaut.vegetables.ui.activity

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.Window
import com.peaut.vegetables.R
import com.peaut.vegetables.view.BaseActivity

class ShareActivity : BaseActivity() {

    override fun prepareLayout() {
        super.prepareLayout()
        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        window.exitTransition = Slide(Gravity.START)
        window.enterTransition = Slide(Gravity.START)
    }
    override fun getResId(): Int = R.layout.activity_share

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun initViewModel(): ViewModel? {
       return null
    }
}
