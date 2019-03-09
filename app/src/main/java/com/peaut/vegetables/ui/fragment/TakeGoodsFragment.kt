package com.peaut.vegetables.ui.fragment

import android.arch.lifecycle.ViewModel
import com.peaut.vegetables.R
import com.peaut.vegetables.view.BaseFragment

/**
 * @author peaut
 * @package  com.peaut.vegetables.ui.fragment
 * @fileName AllOrderFragment
 * @date on  2019/3/9  15:01
 */
class TakeGoodsFragment: BaseFragment(){  //待收货
    override fun getLayoutId(): Int = R.layout.fm_all_order

    override fun initViewModel(): ViewModel? {
        return null
    }

    override fun initView() {

    }
}