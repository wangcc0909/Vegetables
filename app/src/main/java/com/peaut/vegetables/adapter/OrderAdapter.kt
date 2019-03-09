package com.peaut.vegetables.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * @author peaut
 * @package  com.peaut.vegetables.adapter
 * @fileName OrderAdapter
 * @date on  2019/3/9  14:49
 */
class OrderAdapter : FragmentPagerAdapter {
    private var fragments: List<Fragment>
    private var titles: Array<String>
    constructor(fm: FragmentManager,fragments: List<Fragment>,titles: Array<String>) : super(fm){
        this.fragments = fragments
        this.titles = titles
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
}