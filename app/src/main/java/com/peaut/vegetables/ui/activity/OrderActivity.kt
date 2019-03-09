package com.peaut.vegetables.ui.activity

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.support.v4.app.Fragment
import com.peaut.vegetables.R
import com.peaut.vegetables.adapter.OrderAdapter
import com.peaut.vegetables.ui.fragment.*
import com.peaut.vegetables.view.BaseActivity
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : BaseActivity() {

    override fun getResId(): Int = R.layout.activity_order

    override fun initView(savedInstanceState: Bundle?) {
        setStatusBarWithBlack()
        setStatusBarHeight(myStatusBar)
        val position = intent.getIntExtra("position",0)
        val fragments = arrayListOf<Fragment>()
        fragments.add(AllOrderFragment())
        fragments.add(ObligationFragment())
        fragments.add(DeliveryFragment())
        fragments.add(TakeGoodsFragment())
        fragments.add(CommentFragment())
        val titles = arrayOf("全部","待付款","待发货","待收货","评价")
        viewpager.adapter = OrderAdapter(supportFragmentManager,fragments,titles)
        tabLayout.setupWithViewPager(viewpager)
        viewpager.currentItem = position
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun initViewModel(): ViewModel? {
       return null
    }
}
