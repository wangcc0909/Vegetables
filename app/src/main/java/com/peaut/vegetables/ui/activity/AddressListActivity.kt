package com.peaut.vegetables.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.peaut.vegetables.R
import com.peaut.vegetables.adapter.AddressListAdapter
import com.peaut.vegetables.db.model.Address
import com.peaut.vegetables.view.BaseActivity
import com.peaut.vegetables.viewmodel.AddressViewModel
import com.peaut.vegetables.viewmodel.base.LViewModelProviders
import kotlinx.android.synthetic.main.activity_address_list.*

class AddressListActivity : BaseActivity() {
    private lateinit var adapter: AddressListAdapter
    private lateinit var addressViewModel: AddressViewModel
    override fun getResId(): Int = R.layout.activity_address_list

    override fun initView(savedInstanceState: Bundle?) {
        setStatusBarWithBlack()
        setStatusBarHeight(myStatusBar)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.setHasFixedSize(true)
        adapter = AddressListAdapter(this)
        mRecyclerView.adapter = adapter
    }

    override fun initData(savedInstanceState: Bundle?) {
        val data = arrayListOf<Address>()
        data.add(Address("王冲冲","15279832376","深圳市宝安区西乡街道西乡大道华盛辉大厦C栋大门岗亭旁丰巢柜",0,true))
        data.add(Address("王冲冲","15279832376","深圳市宝安区西乡街道西乡大道华盛辉大厦C栋大门岗亭旁丰巢柜",0,false))
        data.add(Address("王冲冲","15279832376","深圳市宝安区西乡街道西乡大道华盛辉大厦C栋大门岗亭旁丰巢柜",0,false))
        data.add(Address("王冲冲","15279832376","深圳市宝安区西乡街道西乡大道华盛辉大厦C栋大门岗亭旁丰巢柜",0,false))
        adapter.addAll(data)
        addressViewModel.queryAddress()
    }

    override fun initListener(savedInstanceState: Bundle?) {
        super.initListener(savedInstanceState)
        ib_back.setOnClickListener { onBackPressed() }
    }

    override fun initViewModel(): ViewModel? {
        addressViewModel = LViewModelProviders.of(this,AddressViewModel::class.java)
        addressViewModel.getAddressData().observe(this, Observer {
            adapter.updateData(it!!)
        })
        return addressViewModel
    }
}
