package com.peaut.vegetables.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.peaut.vegetables.R

/**
 * @author peaut
 * @package  com.peaut.vegetables.adapter
 * @fileName OrderAdapter
 * @date on  2019/3/9  14:49
 */
class OrderAdapter : FragmentPagerAdapter {
    private var fragments: List<Fragment>
    private var titles: Array<String>
    private var context: Context
    private lateinit var tabViews: SparseArray<View>
    constructor(context: Context,fm: FragmentManager,fragments: List<Fragment>,titles: Array<String>) : super(fm){
        this.context = context
        this.fragments = fragments
        this.titles = titles
        initTabView()
    }

    private fun initTabView() {
        tabViews  = SparseArray()
        for (i in 0 until fragments.size){
            val tabView = LayoutInflater.from(context).inflate(R.layout.tab_wait_for_pay,null)
            val tabTitle = tabView.findViewById<TextView>(R.id.tv_tab_title)
            tabTitle.text = titles[i]
            tabViews.put(i,tabView)
        }
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

    fun getTabView(position: Int): View {
        return tabViews.get(position)
    }

    fun getTabTitleView(position: Int): TextView {
        val view = getTabView(position)
        return view.findViewById(R.id.tv_tab_title)
    }

    fun getTabRedView(position: Int): TextView {
        val view = getTabView(position)
        return view.findViewById(R.id.iv_tab_red)
    }
}