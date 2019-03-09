package com.peaut.vegetables.ui.activity

import android.app.ActivityOptions
import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.os.Bundle
import android.transition.Explode
import android.transition.Fade
import android.view.Window
import android.widget.TextView
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.peaut.vegetables.adapter.CategoryAdapter
import com.peaut.vegetables.model.CategoryItem
import com.peaut.vegetables.util.inflate
import com.peaut.vegetables.util.listInit
import com.peaut.vegetables.view.BaseActivity
import kotlinx.android.synthetic.main.activity_category_vegetables.*



class CategoryVegetablesActivity : BaseActivity() {

    override fun prepareLayout() {
        super.prepareLayout()
        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS)
    }

    override fun getResId(): Int = com.peaut.vegetables.R.layout.activity_category_vegetables

    override fun initView(savedInstanceState: Bundle?) {
        window.exitTransition = Fade()
        window.enterTransition = Explode()
        setStatusBarWithBlack()
        setStatusBarHeight(myStatusBar)
    }

    override fun initData(savedInstanceState: Bundle?) {
        val adapter = CategoryAdapter(this)
        val mLRecyclerViewAdapter = LRecyclerViewAdapter(adapter)
        mRecyclerView.listInit(this,mLRecyclerViewAdapter)
        val categoryHeadLayout = inflate(com.peaut.vegetables.R.layout.category_head_layout,findViewById(android.R.id.content))
        val tvCabbage = categoryHeadLayout.findViewById<TextView>(com.peaut.vegetables.R.id.tv_cabbage)
        tvCabbage.text = "白菜"
        mLRecyclerViewAdapter.addHeaderView(categoryHeadLayout)
        val icon = "https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg"
        val itemDatas = arrayListOf<CategoryItem>()
        itemDatas.add(CategoryItem(icon,"老二家的水果店","大量库存","$15.8","北京上海路南京店深圳区"))
        itemDatas.add(CategoryItem(icon,"老二家的水果店","大量库存","$15.8","北京上海路南京店深圳区"))
        itemDatas.add(CategoryItem(icon,"老二家的水果店","大量库存","$15.8","北京上海路南京店深圳区"))
        itemDatas.add(CategoryItem(icon,"老二家的水果店","大量库存","$15.8","北京上海路南京店深圳区"))
        itemDatas.add(CategoryItem(icon,"老二家的水果店","大量库存","$15.8","北京上海路南京店深圳区"))
        itemDatas.add(CategoryItem(icon,"老二家的水果店","大量库存","$15.8","北京上海路南京店深圳区"))
        itemDatas.add(CategoryItem(icon,"老二家的水果店","大量库存","$15.8","北京上海路南京店深圳区"))
        adapter.addAll(itemDatas)
        mLRecyclerViewAdapter.notifyDataSetChanged()
    }

    override fun initListener(savedInstanceState: Bundle?) {
        super.initListener(savedInstanceState)
        ib_back.setOnClickListener { onBackPressed() }
        ib_menu.setOnClickListener {
            val intent = Intent(this, ShareActivity::class.java)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }
    }

    override fun initViewModel(): ViewModel? {
        return null
    }

}
