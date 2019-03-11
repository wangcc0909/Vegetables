package com.peaut.vegetables.ui.fragment

import android.arch.lifecycle.ViewModel
import android.support.v7.widget.RecyclerView
import android.widget.RelativeLayout
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.peaut.vegetables.R
import com.peaut.vegetables.adapter.ObligationAdapter
import com.peaut.vegetables.base.MultiItemEntity
import com.peaut.vegetables.common.Constant
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
    //给recyclerView添加头布局  判断是否有数据  有的话头布局中依然是个recyclerView  否侧头布局是个空布局表示无数据
    private lateinit var adapter: ObligationAdapter
    private lateinit var mLRecyclerViewAdapter: LRecyclerViewAdapter
    private lateinit var mHeadRecyclerView: RecyclerView
    private lateinit var mEmptyView: RelativeLayout
    override fun getLayoutId(): Int = R.layout.fm_all_order

    override fun initViewModel(): ViewModel? {
        return null
    }

    //用多条目布局试试  4种布局  直接添加空布局和title布局  然后不显示空布局 设置一个bool值是否显示空布局
    override fun initView() {
        //这里可以做成多条目的布局  把猜你喜欢放上去  // 添加多个头布局试试
        adapter = ObligationAdapter(requireContext())
        mLRecyclerViewAdapter = LRecyclerViewAdapter(adapter)
//        val headView = requireContext().inflate(R.layout.obl_head_layout)
//        mHeadRecyclerView = headView.findViewById(R.id.headRecyclerView)
//        mEmptyView = headView.findViewById(R.id.ll_middle_view)
//        mLRecyclerViewAdapter.addHeaderView(headView)
//        val headView2 = requireContext().inflate(R.layout.item_wanner_layout)
//        mLRecyclerViewAdapter.addHeaderView(headView2)
        mRecyclerView.gridInit(requireContext(), mLRecyclerViewAdapter)
        mLRecyclerViewAdapter.setSpanSizeLookup { gridLayoutManager, position ->
            val viewType = adapter.getItemViewType(position)
            return@setSpanSizeLookup if (viewType == Constant.ITEM_COMMENT_TYPE) 1 else 2
        }
        adapter.setOnTextViewClickListener(object : ObligationAdapter.OnTextViewClickListener{
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
//        data.add(EmptyItem())
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
        /*val headAdapter = ObligationHeadAdapter(requireContext())
        mHeadRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mHeadRecyclerView.setHasFixedSize(true)
        mHeadRecyclerView.adapter = headAdapter
        mHeadRecyclerView.itemDecoration(requireContext())
        headAdapter.setOnTextViewClickListener(object : ObligationHeadAdapter.OnTextViewClickListener {
            override fun onTextViewClick(position: Int) {
                mLRecyclerViewAdapter.notifyDataSetChanged()
                if (headAdapter.getData().isEmpty()) {
                    mEmptyView.visibility = View.VISIBLE
                    mHeadRecyclerView.visibility = View.GONE
                }
            }
        })

        val data = arrayListOf<ProductItem>()
        val title = "很长差离开房间拉进来卡是第几个拉克丝的结果拉肯定经过垃圾垃圾aljga垃圾费;agjlkadskldsjg拉开打机啊;"
        val icon = "https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg"
        data.add(ProductItem(title, 12.5, icon, true, 0, 0))
        data.add(ProductItem(title, 12.5, icon, true, 0, 0))
        data.add(ProductItem(title, 12.5, icon, true, 0, 0))
        data.add(ProductItem(title, 12.5, icon, true, 0, 0))
        adapter.addAll(data)
        mLRecyclerViewAdapter.notifyDataSetChanged()

        val headData = arrayListOf<ProductItem>()
        headData.add(ProductItem(title, 38.2, icon, true, 4, 1))
        headData.add(ProductItem(title, 38.2, icon, true, 4, 1))
        headAdapter.addAll(headData)*/

    }

    override fun initListener() {
        super.initListener()

    }
}