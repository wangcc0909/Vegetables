package com.peaut.vegetables.ui.activity

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.peaut.vegetables.R
import com.peaut.vegetables.adapter.SearchResultAdapter
import com.peaut.vegetables.model.HomeItem
import com.peaut.vegetables.view.BaseActivity
import com.peaut.vegetables.weight.SpacesItemDecoration
import kotlinx.android.synthetic.main.activity_search_result.*

class SearchResultActivity : BaseActivity() {
    private lateinit var adapter: SearchResultAdapter
    private lateinit var mLRecyclerViewAdapter: LRecyclerViewAdapter
    private lateinit var key: String
    private lateinit var gridLayoutManager: GridLayoutManager
    private var isTable = false
    override fun getResId(): Int = R.layout.activity_search_result

    override fun initView(savedInstanceState: Bundle?) {
        key = intent.getStringExtra("key")
        setStatusBarWithBlack()
        setStatusBarHeight(myStatusBar)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        //false 是线性布局 true是表格布局
        adapter = SearchResultAdapter(this)
        mLRecyclerViewAdapter = LRecyclerViewAdapter(adapter)
        gridLayoutManager = GridLayoutManager(this, 1)
        mRecyclerView.layoutManager = gridLayoutManager
        mRecyclerView.adapter = mLRecyclerViewAdapter
        mRecyclerView.setPullRefreshEnabled(false)
        val spacing = resources.getDimensionPixelSize(R.dimen.dp_4)
        mRecyclerView.addItemDecoration(SpacesItemDecoration.
                newInstance(spacing,spacing,2,ContextCompat.getColor(this,R.color.color_f4)))
        mRecyclerView.itemAnimator = null
        mLRecyclerViewAdapter.notifyDataSetChanged()
    }

    override fun initData(savedInstanceState: Bundle?) {
        val mData = arrayListOf<HomeItem>()
        mData.add(HomeItem("xxx果蔬供应批发商", "https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg", 4.7f, "广州天河区", "1.8km", 1))
        mData.add(HomeItem("xxx果蔬供应批发商", "https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg", 4.7f, "广州天河区", "1.8km", 1))
        mData.add(HomeItem("xxx果蔬供应批发商", "https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg", 4.7f, "广州天河区", "1.8km", 1))
        mData.add(HomeItem("xxx果蔬供应批发商", "https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg", 4.7f, "广州天河区", "1.8km", 1))
        mData.add(HomeItem("xxx果蔬供应批发商", "https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg", 4.7f, "广州天河区", "1.8km", 1))
        mData.add(HomeItem("xxx果蔬供应批发商", "https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg", 4.7f, "广州天河区", "1.8km", 1))
        adapter.switchType(isTable)
        adapter.addAll(mData)
    }

    override fun initListener(savedInstanceState: Bundle?) {
        super.initListener(savedInstanceState)
        ib_back.setOnClickListener {
            finish()
        }
        ib_menu.setOnClickListener {
            isTable = !isTable
            if (isTable) {
                ib_menu.setImageResource(R.drawable.ic_menu_black)
                gridLayoutManager.spanCount = 2
                adapter.switchType(true)
            }else {
                ib_menu.setImageResource(R.drawable.ic_grid_black)
                gridLayoutManager.spanCount = 1
                adapter.switchType(false)
            }
        }
    }

    override fun initViewModel(): ViewModel? {
        return null
    }

}
