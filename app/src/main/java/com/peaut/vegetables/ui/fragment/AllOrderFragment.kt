package com.peaut.vegetables.ui.fragment

import android.arch.lifecycle.ViewModel
import android.support.v4.content.ContextCompat
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.peaut.vegetables.R
import com.peaut.vegetables.adapter.AllOrderAdapter
import com.peaut.vegetables.base.MultiItemEntity
import com.peaut.vegetables.common.Constant
import com.peaut.vegetables.listener.OnTextViewClickListener
import com.peaut.vegetables.model.EmptyItem
import com.peaut.vegetables.model.OrderItem
import com.peaut.vegetables.util.lineInit
import com.peaut.vegetables.view.BaseFragment
import kotlinx.android.synthetic.main.fm_all_order.*

/**
 * @author peaut
 * @package  com.peaut.vegetables.ui.fragment
 * @fileName AllOrderFragment
 * @date on  2019/3/9  15:01
 */
class AllOrderFragment: BaseFragment(){  //所有订单
    private lateinit var adapter: AllOrderAdapter
    private lateinit var mLRecyclerViewAdapter: LRecyclerViewAdapter
    override fun getLayoutId(): Int = R.layout.fm_all_order

    override fun initViewModel(): ViewModel? {
        return null
    }

    override fun initView() {
        adapter = AllOrderAdapter(requireContext())
        mLRecyclerViewAdapter = LRecyclerViewAdapter(adapter)
        mRecyclerView.lineInit(requireContext(),mLRecyclerViewAdapter)
        val spacing = resources.getDimensionPixelSize(R.dimen.dp_10)
        mRecyclerView.addItemDecoration(com.peaut.vegetables.weight.SpacesItemDecoration.newInstance(
                spacing, spacing, 2, ContextCompat.getColor(requireContext(),R.color.color_f4)))
        adapter.setOnTextViewClickListener(object : OnTextViewClickListener{
            override fun onTextViewClick(position: Int) {
                var isHasOrder = false
                for (item in adapter.getData()){
                    if (item is OrderItem){
                        isHasOrder = true
                    }
                }
                if (!isHasOrder){
                    (adapter.getData() as ArrayList).add(0, EmptyItem())
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }

    override fun initData() {
        super.initData()
        val title = "深刻了发设计费爱上了副科级按理说文件安啦是第几个拉设计费拉设计大方"
        val icon = "https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg"
        val data = arrayListOf<MultiItemEntity>()
        data.add(OrderItem(title,icon,43f,20,Constant.ITEM_COMPLETE_TYPE))
        data.add(OrderItem(title,icon,0.01f,11,Constant.ITEM_OBLIGATION_TYPE))
        data.add(OrderItem(title,icon,11654f,15,Constant.ITEM_DELIVERY_TYPE))
        data.add(OrderItem(title,icon,11654f,15,Constant.ITEM_TAKE_GOODS_TYPE))
        adapter.addAll(data)
        mLRecyclerViewAdapter.notifyDataSetChanged()
    }
}