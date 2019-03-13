package com.peaut.vegetables.ui.fragment

import android.arch.lifecycle.ViewModel
import com.peaut.vegetables.R
import com.peaut.vegetables.ui.activity.MessageActivity
import com.peaut.vegetables.ui.activity.OrderActivity
import com.peaut.vegetables.util.intent
import com.peaut.vegetables.util.startActivity
import com.peaut.vegetables.view.BaseFragment
import kotlinx.android.synthetic.main.fm_me.*

/**
 * @author peaut
 * @package  com.peaut.vegetables.ui.fragment
 * @fileName MeFragment
 * @date on  2019/2/28  11:36
 */
class MeFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fm_me

    override fun initViewModel(): ViewModel? {
        return null
    }

    override fun initView() {
        setStatusBarHeight(myStatusBar)
    }

    override fun initListener() {
        super.initListener()
        tv_all_order.setOnClickListener {
            //这里需要携带跳转到那个fragment
            requireContext().startActivity<OrderActivity>()
        }
        tv_payment.setOnClickListener {
            requireContext().startActivity(requireActivity().intent<OrderActivity> {
                putExtra("position",1)
            })
        }
        tv_shopped.setOnClickListener {
            requireContext().startActivity(requireActivity().intent<OrderActivity> {
                putExtra("position",2)
            })
        }
        tv_received.setOnClickListener {
            requireContext().startActivity(requireActivity().intent<OrderActivity> {
                putExtra("position",3)
            })
        }
        tv_comment.setOnClickListener {
            requireContext().startActivity(requireActivity().intent<OrderActivity> {
                putExtra("position",4)
            })
        }

        ib_msg.setOnClickListener {
            requireContext().startActivity<MessageActivity>()
        }
    }

}