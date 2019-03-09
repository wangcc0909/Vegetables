package com.peaut.vegetables.ui.fragment

import android.arch.lifecycle.ViewModel
import com.peaut.vegetables.R
import com.peaut.vegetables.view.BaseFragment

/**
 * @author peaut
 * @package  com.peaut.vegetables.ui.fragment
 * @fileName GoodsDesFragment
 * @date on  2019/3/8  17:35
 */
class GoodsDesFragment: BaseFragment(){
    //这个fragment就维护一个图片 + 文字两种item的recyclerView
    override fun getLayoutId(): Int = R.layout.fm_des

    override fun initViewModel(): ViewModel? {
        return null
    }

    override fun initView() {

    }
}