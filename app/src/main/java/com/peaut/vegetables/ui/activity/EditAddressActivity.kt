package com.peaut.vegetables.ui.activity

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import com.peaut.vegetables.R
import com.peaut.vegetables.common.Constant
import com.peaut.vegetables.model.PickerData
import com.peaut.vegetables.view.BaseActivity
import com.peaut.vegetables.viewmodel.AddressViewModel
import com.peaut.vegetables.viewmodel.base.LViewModelProviders
import com.peaut.vegetables.weight.PickerView
import kotlinx.android.synthetic.main.activity_edit_address.*

class EditAddressActivity : BaseActivity() {
    private lateinit var addressViewModel: AddressViewModel
    private var action: String? = null
    override fun getResId(): Int = R.layout.activity_edit_address

    override fun initView(savedInstanceState: Bundle?) {
        setStatusBarWithBlack()
        setStatusBarHeight(myStatusBar)
        action = intent.action
        if (Constant.ACTION_NEW_ADDRESS == action){
            //新增地址  设置title
            tv_title.text = "新增收货地址"
        }

    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun initListener(savedInstanceState: Bundle?) {
        super.initListener(savedInstanceState)
        val data = arrayListOf<String>()
        data.add("北京")
        data.add("上海")
        data.add("广州")
        data.add("深圳")
        et_area.setOnClickListener {
            showToast("点击了地址")
            val pickdata = PickerData()
            pickdata.mFirstData = data
            val pickerView = PickerView(this,pickdata)
            pickerView.show()
        }
        ib_back.setOnClickListener { onBackPressed() }
    }

    override fun initViewModel(): ViewModel? {
        addressViewModel = LViewModelProviders.of(this,AddressViewModel::class.java)
        return addressViewModel
    }
}
