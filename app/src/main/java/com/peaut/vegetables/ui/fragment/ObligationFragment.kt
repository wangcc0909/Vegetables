package com.peaut.vegetables.ui.fragment

import android.arch.lifecycle.ViewModel
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.peaut.vegetables.R
import com.peaut.vegetables.adapter.ObligationAdapter
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
class ObligationFragment : BaseFragment() {  //待付款
    private lateinit var adapter: ObligationAdapter
    private lateinit var mLRecyclerViewAdapter: LRecyclerViewAdapter
    override fun getLayoutId(): Int = R.layout.fm_all_order

    override fun initViewModel(): ViewModel? {
        return null
    }

    override fun initView() {
        adapter = ObligationAdapter(requireContext())
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
                    (adapter.getData() as ArrayList).add(0,EmptyItem())
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }

    override fun initData() {
        super.initData()
        val title = "很长差离开房间拉进来卡是第几个拉克丝的结果拉肯定经过垃圾垃圾aljga垃圾费;agjlkadskldsjg拉开打机啊;"
        val icon = "https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg"
        val data = arrayListOf<MultiItemEntity>()
        data.add(OrderItem(title,icon,12.6f,2))
        data.add(OrderItem(title,icon,396f,1))
        data.add(OrderItem(title,icon,4645364f,1))
        data.add(OrderItem(title,icon,42.5f,1))
        data.add(TitleItem())
        data.add(PickItem(title,icon,12.3f))
        data.add(PickItem(title,icon,46f))
        data.add(PickItem(title,icon,28f))
        data.add(PickItem(title,icon,69f))
        adapter.addAll(data)
        mLRecyclerViewAdapter.notifyDataSetChanged()
    }

    override fun initListener() {
        super.initListener()

    }
}