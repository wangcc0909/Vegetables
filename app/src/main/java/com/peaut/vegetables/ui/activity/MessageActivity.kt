package com.peaut.vegetables.ui.activity

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.peaut.vegetables.R
import com.peaut.vegetables.adapter.MessageAdapter
import com.peaut.vegetables.model.Message
import com.peaut.vegetables.util.lineInit
import com.peaut.vegetables.view.BaseActivity
import kotlinx.android.synthetic.main.activity_message.*

class MessageActivity : BaseActivity() {
    private lateinit var adapter: MessageAdapter
    private lateinit var mLRecyclerViewAdapter: LRecyclerViewAdapter
    override fun getResId(): Int = R.layout.activity_message

    override fun initView(savedInstanceState: Bundle?) {
        setStatusBarHeight(myStatusBar)
        setStatusBarWithBlack()
        adapter = MessageAdapter(this)
        mLRecyclerViewAdapter = LRecyclerViewAdapter(adapter)
        mRecyclerView.lineInit(this,mLRecyclerViewAdapter)
    }

    override fun initData(savedInstanceState: Bundle?) {
        val data = arrayListOf<Message>()
        val icon = "https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg"
        data.add(Message(icon,"瑾萱活动","花了大价钱开展的活动","2019-03-12"))
        data.add(Message(icon,"通知消息","我是个消息","2019-03-12"))
        data.add(Message(icon,"客户助手","有事找警察,别找我","2019-03-12"))
        adapter.addAll(data)
        mLRecyclerViewAdapter.notifyDataSetChanged()
    }

    override fun initListener(savedInstanceState: Bundle?) {
        super.initListener(savedInstanceState)
        ib_back.setOnClickListener { onBackPressed() }
    }

    override fun initViewModel(): ViewModel? {
        return null
    }
}
