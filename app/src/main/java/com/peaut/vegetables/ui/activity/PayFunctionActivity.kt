package com.peaut.vegetables.ui.activity

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import com.peaut.vegetables.R
import com.peaut.vegetables.view.BaseActivity
import kotlinx.android.synthetic.main.activity_pay_function.*

class PayFunctionActivity : BaseActivity() {
    override fun getResId(): Int = R.layout.activity_pay_function

    override fun initView(savedInstanceState: Bundle?) {
        setStatusBarWithBlack()
        setStatusBarHeight(myStatusBar)
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun initListener(savedInstanceState: Bundle?) {
        super.initListener(savedInstanceState)
        ib_back.setOnClickListener { onBackPressed() }
        wx_pay.setOnClickListener {  }
        ali_pay.setOnClickListener {  }
    }

    override fun initViewModel(): ViewModel? {
       return null
    }
}
