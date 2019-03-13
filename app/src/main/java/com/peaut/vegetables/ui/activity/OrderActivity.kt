package com.peaut.vegetables.ui.activity

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.peaut.vegetables.R
import com.peaut.vegetables.adapter.OrderAdapter
import com.peaut.vegetables.ui.fragment.*
import com.peaut.vegetables.util.getColorCompat
import com.peaut.vegetables.view.BaseActivity
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : BaseActivity() {
    private lateinit var fragments: ArrayList<Fragment>
    private lateinit var orderAdapter: OrderAdapter
    override fun getResId(): Int = R.layout.activity_order

    override fun initView(savedInstanceState: Bundle?) {
        setStatusBarWithBlack()
        setStatusBarHeight(myStatusBar)
        val position = intent.getIntExtra("position", 0)
        fragments = arrayListOf()
        fragments.add(AllOrderFragment())
        fragments.add(ObligationFragment())
        fragments.add(DeliveryFragment())
        fragments.add(TakeGoodsFragment())
        fragments.add(CommentFragment())
        val titles = arrayOf("全部", "待付款", "待发货", "待收货", "评价")
        orderAdapter = OrderAdapter(this, supportFragmentManager, fragments, titles)
        viewpager.adapter = orderAdapter
        tabLayout.setupWithViewPager(viewpager)
        viewpager.currentItem = position
        setupTabBadge()
    }

    //这里只是初始化
    private fun setupTabBadge() {
        for (i in 0 until fragments.size) {
            val tab = tabLayout.getTabAt(i)
            val customView = tab?.customView
            if (customView != null) {
                val parent = customView.parent
                if (parent != null) {
                    (parent as ViewGroup).removeView(customView)
                }
            }
            val tabView = orderAdapter.getTabView(i)
            val tabRed = tabView.findViewById<TextView>(R.id.iv_tab_red)
            if (i == tabLayout.selectedTabPosition){
                orderAdapter.getTabTitleView(i).setTextColor(getColorCompat(R.color.color_DF1C4B))
                tabRed.visibility = View.GONE
            }
            tab?.customView = tabView
        }
        tabLayout.getTabAt(tabLayout.selectedTabPosition)?.customView?.isSelected = true
//        tabLayout.setSelectedTabIndicatorColor(getColorCompat(R.color.color_DF1C4B))
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun initListener(savedInstanceState: Bundle?) {
        super.initListener(savedInstanceState)
        ib_back.setOnClickListener {
            onBackPressed()
        }
        tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val position = tab?.position
                val tabTitle = orderAdapter.getTabTitleView(position!!)
                tabTitle.setTextColor(getColorCompat(R.color.color_37))
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab?.position
                val tabTitle = orderAdapter.getTabTitleView(position!!)
                tabTitle.setTextColor(getColorCompat(R.color.color_DF1C4B))
                val tabRed = orderAdapter.getTabRedView(position)
                tabRed.visibility = View.GONE
            }
        })
    }

    override fun initViewModel(): ViewModel? {
        return null
    }
}
