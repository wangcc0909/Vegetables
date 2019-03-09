package com.peaut.vegetables.ui.fragment

import android.arch.lifecycle.ViewModel
import com.peaut.vegetables.R
import com.peaut.vegetables.view.BaseFragment

/**
 * @author peaut
 * @package  com.peaut.vegetables.ui.fragment
 * @fileName GoodsAnswerFragment
 * @date on  2019/3/8  17:36
 */
class GoodsAnswerFragment: BaseFragment(){
    //维护一个 title + 问答两种item的recycler
    override fun getLayoutId(): Int = R.layout.fm_answer

    override fun initViewModel(): ViewModel? {
       return null
    }

    override fun initView() {

    }

}