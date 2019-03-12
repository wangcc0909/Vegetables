package com.peaut.vegetables.ui.fragment

import android.arch.lifecycle.ViewModel
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.peaut.vegetables.R
import com.peaut.vegetables.adapter.TakeGoodsAdapter
import com.peaut.vegetables.base.MultiItemEntity
import com.peaut.vegetables.common.Constant
import com.peaut.vegetables.listener.OnTextViewClickListener
import com.peaut.vegetables.model.EmptyItem
import com.peaut.vegetables.model.OrderItem
import com.peaut.vegetables.model.PickItem
import com.peaut.vegetables.model.TitleItem
import com.peaut.vegetables.util.gridInit
import com.peaut.vegetables.view.BaseFragment
import kotlinx.android.synthetic.main.fm_all_order.*

/**
 * @author peaut
 * @package  com.peaut.vegetables.ui.fragment
 * @fileName AllOrderFragment
 * @date on  2019/3/9  15:01
 */
class TakeGoodsFragment: BaseFragment(){  //待收货
    private lateinit var adapter: TakeGoodsAdapter
    private lateinit var mLRecyclerViewAdapter: LRecyclerViewAdapter
    override fun getLayoutId(): Int = R.layout.fm_all_order

    override fun initViewModel(): ViewModel? {
        return null
    }

    override fun initView() {
        adapter = TakeGoodsAdapter(requireContext())
        mLRecyclerViewAdapter = LRecyclerViewAdapter(adapter)
        mRecyclerView.gridInit(requireContext(), mLRecyclerViewAdapter)
        mLRecyclerViewAdapter.setSpanSizeLookup { _, position ->
            val viewType = adapter.getItemViewType(position)
            return@setSpanSizeLookup if (viewType == Constant.ITEM_COMMENT_TYPE) 1 else 2
        }
        adapter.setOnTextViewClickListener(object : OnTextViewClickListener {
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

    //直接进入这个fragment不会走这个方法  走onFirstUserVisible
    override fun onUserVisible() {
        super.onUserVisible()
        val title = "深刻了发设计费爱上了副科级按理说文件安啦是第几个拉设计费拉设计大方"
        val icon = "https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg"
        val data = arrayListOf<MultiItemEntity>()
        data.add(OrderItem(title,icon,43f,20))
        data.add(OrderItem(title,icon,0.01f,11))
        data.add(OrderItem(title,icon,11654f,15))
        data.add(TitleItem())
        data.add(PickItem(title,icon,99f))
        data.add(PickItem(title,icon,998f))
        data.add(PickItem(title,icon,15.6f))
        data.add(PickItem(title,icon,29f))
        data.add(PickItem(title,icon,14f))
        data.add(PickItem(title,icon,76f))
        adapter.addAll(data)
        mLRecyclerViewAdapter.notifyDataSetChanged()
    }
}