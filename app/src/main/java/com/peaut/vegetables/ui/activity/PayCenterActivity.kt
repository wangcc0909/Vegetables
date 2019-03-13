package com.peaut.vegetables.ui.activity

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.peaut.vegetables.R
import com.peaut.vegetables.adapter.PayCenterAdapter
import com.peaut.vegetables.util.inflate
import com.peaut.vegetables.util.lineInit
import com.peaut.vegetables.view.BaseActivity
import kotlinx.android.synthetic.main.activity_pay_center.*

class PayCenterActivity : BaseActivity() {
    private lateinit var adapter: PayCenterAdapter
    private lateinit var mLRecyclerViewAdapter: LRecyclerViewAdapter
    override fun getResId(): Int = R.layout.activity_pay_center

    override fun initView(savedInstanceState: Bundle?) {
        setStatusBarWithBlack()
        setStatusBarHeight(myStatusBar)
        adapter = PayCenterAdapter(this)
        mLRecyclerViewAdapter = LRecyclerViewAdapter(adapter)
        mRecyclerView.lineInit(this, mLRecyclerViewAdapter)
        mRecyclerView.setLoadMoreEnabled(false)
        val headView = inflate(R.layout.pay_center_head_layout, findViewById(android.R.id.content))
        mLRecyclerViewAdapter.addHeaderView(headView)
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun initViewModel(): ViewModel? {
        return null
    }
}
