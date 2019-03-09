package com.peaut.vegetables.ui.fragment

import android.arch.lifecycle.ViewModel
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.peaut.vegetables.R
import com.peaut.vegetables.adapter.HomeAdapter
import com.peaut.vegetables.model.HomeItem
import com.peaut.vegetables.ui.activity.CategoryVegetablesActivity
import com.peaut.vegetables.ui.activity.SearchActivity
import com.peaut.vegetables.util.*
import com.peaut.vegetables.view.BaseFragment
import com.youth.banner.Banner
import kotlinx.android.synthetic.main.fm_home.*

/**
 * @author peaut
 * @package  com.peaut.vegetables.ui.fragment
 * @fileName HomeFragment
 * @date on  2019/2/25  18:01
 */
class HomeFragment: BaseFragment(){
    private lateinit var mHeadBanner: View
    private lateinit var mHomeCategory1: LinearLayout
    private lateinit var mHomeCategory2: LinearLayout
    private lateinit var mHomeCategory3: LinearLayout
    private lateinit var mTvRcSearch: TextView
    override fun getLayoutId(): Int = R.layout.fm_home

    override fun initViewModel(): ViewModel? {
        return null
    }

    override fun initView() {
        setStatusBarHeight(myStatusBar)
        val adapter = HomeAdapter(requireContext())
        val mLRecyclerViewAdapter = LRecyclerViewAdapter(adapter)
        mRecyclerView.listInit(requireContext(),mLRecyclerViewAdapter)
        val headBanner = requireContext().inflate(R.layout.home_head_banner)
        mTvRcSearch = headBanner.findViewById(R.id.tv_rc_search)
        val mBanner = headBanner.findViewById<Banner>(R.id.mBanner)
        mHomeCategory1 = headBanner.findViewById(R.id.home_linearLayout1)
        mHomeCategory2 = headBanner.findViewById(R.id.home_linearLayout2)
        mHomeCategory3 = headBanner.findViewById(R.id.home_linearLayout3)
        mLRecyclerViewAdapter.addHeaderView(headBanner)
        val mData = ArrayList<HomeItem>()
        mData.add(HomeItem("xxx果蔬供应批发商","https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg",4.7f,"广州天河区","1.8km",1))
        mData.add(HomeItem("xxx果蔬供应批发商","https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg",4.7f,"广州天河区","1.8km",1))
        mData.add(HomeItem("xxx果蔬供应批发商","https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg",4.7f,"广州天河区","1.8km",1))
        mData.add(HomeItem("xxx果蔬供应批发商","https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg",4.7f,"广州天河区","1.8km",1))
        mData.add(HomeItem("xxx果蔬供应批发商","https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg",4.7f,"广州天河区","1.8km",1))
        mData.add(HomeItem("xxx果蔬供应批发商","https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg",4.7f,"广州天河区","1.8km",1))
        adapter.addAll(mData)
        mLRecyclerViewAdapter.notifyDataSetChanged()
        mStatusLayoutManager.showContent()

        val bannerImages = arrayListOf<String>()
        bannerImages.add("https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg")
        bannerImages.add("https://ws1.sinaimg.cn/large/0065oQSqgy1fwyf0wr8hhj30ie0nhq6p.jpg")
        bannerImages.add("https://ws1.sinaimg.cn/large/0065oQSqgy1fwgzx8n1syj30sg15h7ew.jpg")
        bannerImages.add("https://ws1.sinaimg.cn/large/0065oQSqly1g0ajj4h6ndj30sg11xdmj.jpg")
        mBanner.player(null,bannerImages)
    }

    override fun initListener() {
        super.initListener()
        mHomeCategory1.setOnClickListener {
            requireContext().startActivity<CategoryVegetablesActivity>()
        }
        mTvRcSearch.setOnClickListener {
//            requireContext().startActivity<SearchActivity>()
            requireActivity().startActivityWithAnimation<SearchActivity>(mTvRcSearch,getString(R.string.search_hint))
        }
    }
}