package com.peaut.vegetables.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.peaut.vegetables.R
import com.peaut.vegetables.adapter.HistoryAdapter
import com.peaut.vegetables.util.hideSoftKeyboard
import com.peaut.vegetables.util.showKeyboard
import com.peaut.vegetables.view.BaseActivity
import com.peaut.vegetables.viewmodel.SearchHistoryViewModel
import com.peaut.vegetables.viewmodel.base.LViewModelProviders
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.toast

class SearchActivity : BaseActivity() {
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var hotAdapter: HistoryAdapter
    private lateinit var searchHistoryViewModel: SearchHistoryViewModel
    override fun getResId(): Int = R.layout.activity_search

    override fun initView(savedInstanceState: Bundle?) {
        setStatusBarWithBlack()
        setStatusBarHeight(myStatusBar)
        et_search.showKeyboard()
        initHistoryList()
        initHotList()
    }

    private fun initHotList() {
        val flexboxManager = FlexboxLayoutManager(this)
        flexboxManager.flexDirection = FlexDirection.ROW
        flexboxManager.alignItems = AlignItems.STRETCH
        flexboxManager.flexWrap = FlexWrap.WRAP
        rv_hot.layoutManager = flexboxManager
        hotAdapter = HistoryAdapter(this)
        rv_hot.adapter = hotAdapter
        //这里的数据可能来自网络 o ye
        val hotData = arrayListOf<String>()
        hotData.add("热门")
        hotData.add("热门")
        hotData.add("热门")
        hotData.add("热门")
        hotAdapter.addAll(hotData)
    }

    private fun initHistoryList() {
        val flexboxManager = FlexboxLayoutManager(this)
        flexboxManager.flexDirection = FlexDirection.ROW
        flexboxManager.alignItems = AlignItems.STRETCH
        flexboxManager.flexWrap = FlexWrap.WRAP
        rv_history.layoutManager = flexboxManager
        historyAdapter = HistoryAdapter(this)
        rv_history.adapter = historyAdapter
    }

    override fun initData(savedInstanceState: Bundle?) {
        searchHistoryViewModel.queryHistory()
    }

    override fun initListener(savedInstanceState: Bundle?) {
        super.initListener(savedInstanceState)
        tv_cancel.setOnClickListener {
            hideSoftKeyboard()
            this.finishAfterTransition()
        }
        //监听回车键
        et_search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                doSearch()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        iv_delete.setOnClickListener {
            searchHistoryViewModel.clearHistory()
        }
    }

    private fun doSearch() {
        val key = et_search.text.toString().trim()
        if (key.isNullOrEmpty()){
            toast(getString(R.string.et_search_empty))
            return
        }
        et_search.setText("")
        hideSoftKeyboard()
        searchHistoryViewModel.insertHistory(key)
        //todo 跳转到搜索结果界面
    }

    override fun initViewModel(): ViewModel? {
        searchHistoryViewModel = LViewModelProviders.of(this,SearchHistoryViewModel::class.java)
        searchHistoryViewModel.getHistoryData().observe(this, Observer {
            //这里就是查询到的历史数据
            historyAdapter.updateData(it!!)
        })
        return searchHistoryViewModel
    }

    override fun startLoading(message: String) {

    }
}
