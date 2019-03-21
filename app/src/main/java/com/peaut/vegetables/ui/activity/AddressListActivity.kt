package com.peaut.vegetables.ui.activity

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.peaut.vegetables.R
import com.peaut.vegetables.adapter.AddressListAdapter
import com.peaut.vegetables.common.Constant
import com.peaut.vegetables.db.model.Address
import com.peaut.vegetables.listener.OnAddressItemListener
import com.peaut.vegetables.util.intent
import com.peaut.vegetables.view.BaseActivity
import com.peaut.vegetables.viewmodel.AddressViewModel
import com.peaut.vegetables.viewmodel.base.LViewModelProviders
import kotlinx.android.synthetic.main.activity_address_list.*

class AddressListActivity : BaseActivity(), OnAddressItemListener {

    companion object {
        val newAddressCode = 1000
    }
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
        addressViewModel.queryAddress()
    }

    override fun initListener(savedInstanceState: Bundle?) {
        super.initListener(savedInstanceState)
        ib_back.setOnClickListener { onBackPressed() }
        tv_add_address.setOnClickListener { addNewAddress() }
        adapter.setOnAddressItemListener(this)
    }

    private fun addNewAddress() {
        //需要带action
        startActivityForResult(intent<EditAddressActivity> {
            this.action = Constant.ACTION_NEW_ADDRESS
        }, newAddressCode)
    }

    override fun initViewModel(): ViewModel? {
        addressViewModel = LViewModelProviders.of(this,AddressViewModel::class.java)
        addressViewModel.getAddressData().observe(this, Observer {
            adapter.updateData(it!!)
        })
        return addressViewModel
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == newAddressCode) {
                addressViewModel.queryAddress()
            }
        }
    }

    override fun onAddressEdit(address: Address) {
        startActivityForResult(intent<EditAddressActivity> {
            this.action = Constant.ACTION_UPDATE_ADDRESS
            this.putExtra(Constant.ADDRESS_KEY,address)
        }, newAddressCode)
    }
}
