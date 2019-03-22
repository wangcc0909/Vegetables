package com.peaut.vegetables.qrhandle.activity

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import com.peaut.vegetables.R
import com.peaut.vegetables.common.Constant
import com.peaut.vegetables.view.BaseActivity
import kotlinx.android.synthetic.main.activity_print.*

class PrintActivity : BaseActivity() {
    override fun getResId(): Int = R.layout.activity_print

    override fun initView(savedInstanceState: Bundle?) {
        setStatusBarWithBlack()
        setStatusBarHeight(myStatusBar)
        val result = intent.getStringExtra(Constant.QR_RESULT)
        tv_result.text = result
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun initListener(savedInstanceState: Bundle?) {
        super.initListener(savedInstanceState)
        ib_back.setOnClickListener { onBackPressed() }
    }

    override fun initViewModel(): ViewModel? {
        return null
    }
}
