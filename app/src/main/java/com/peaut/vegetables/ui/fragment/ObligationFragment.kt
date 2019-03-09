package com.peaut.vegetables.ui.fragment

import android.arch.lifecycle.ViewModel
import com.github.jdsjlzx.recyclerview.LRecyclerView
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.peaut.vegetables.R
import com.peaut.vegetables.adapter.ObligationAdapter
import com.peaut.vegetables.adapter.ObligationHeadAdapter
import com.peaut.vegetables.model.ProductItem
import com.peaut.vegetables.util.gridInit
import com.peaut.vegetables.util.inflate
import com.peaut.vegetables.util.listInit
import com.peaut.vegetables.view.BaseFragment
import kotlinx.android.synthetic.main.fm_all_order.*

/**
 * @author peaut
 * @package  com.peaut.vegetables.ui.fragment
 * @fileName AllOrderFragment
 * @date on  2019/3/9  15:01
 */
class ObligationFragment: BaseFragment(){  //待付款
    //给recyclerView添加头布局  判断是否有数据  有的话头布局中依然是个recyclerView  否侧头布局是个空布局表示无数据
    private lateinit var adapter: ObligationAdapter
    private lateinit var mLRecyclerViewAdapter: LRecyclerViewAdapter
    private lateinit var mHeadRecyclerView: LRecyclerView
    override fun getLayoutId(): Int = R.layout.fm_all_order

    override fun initViewModel(): ViewModel? {
        return null
    }

    override fun initView() {
        adapter = ObligationAdapter(requireContext())
        mLRecyclerViewAdapter = LRecyclerViewAdapter(adapter)
        mRecyclerView.gridInit(requireContext(),mLRecyclerViewAdapter)
        val headView = requireContext().inflate(R.layout.obl_head_layout)
        mHeadRecyclerView = headView.findViewById(R.id.headRecyclerView)
        mLRecyclerViewAdapter.addHeaderView(headView)
    }

    override fun initData() {
        super.initData()
        val headAdapter = ObligationHeadAdapter(requireContext())
        val mHeadLRecyclerViewAdapter = LRecyclerViewAdapter(headAdapter)
        mHeadRecyclerView.listInit(requireContext(),mHeadLRecyclerViewAdapter)

        val data = arrayListOf<ProductItem>()
        val title = "很长差离开房间拉进来卡是第几个拉克丝的结果拉肯定经过垃圾垃圾aljga垃圾费;agjlkadskldsjg拉开打机啊;"
        val icon = "https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg"
        data.add(ProductItem(title,12.5,icon,true,0,0))
        data.add(ProductItem(title,12.5,icon,true,0,0))
        data.add(ProductItem(title,12.5,icon,true,0,0))
        data.add(ProductItem(title,12.5,icon,true,0,0))
        adapter.addAll(data)
        mLRecyclerViewAdapter.notifyDataSetChanged()
    }

    override fun initListener() {
        super.initListener()

    }
}