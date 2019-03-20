package com.peaut.vegetables.ui.activity

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.peaut.vegetables.R
import com.peaut.vegetables.common.Constant
import com.peaut.vegetables.db.AddressTable
import com.peaut.vegetables.db.model.Address
import com.peaut.vegetables.model.PickerData
import com.peaut.vegetables.model.Province
import com.peaut.vegetables.util.inflate
import com.peaut.vegetables.util.isPhone
import com.peaut.vegetables.view.BaseActivity
import com.peaut.vegetables.viewmodel.AddressViewModel
import com.peaut.vegetables.viewmodel.base.LViewModelProviders
import com.peaut.vegetables.weight.CommonDialog
import com.peaut.vegetables.weight.PickerView
import kotlinx.android.synthetic.main.activity_edit_address.*
import org.jetbrains.anko.toast
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class EditAddressActivity : BaseActivity() {
    private lateinit var addressViewModel: AddressViewModel
    private var action: String? = null
    private var oldAddress: Address? = null
    override fun getResId(): Int = R.layout.activity_edit_address

    override fun initView(savedInstanceState: Bundle?) {
        setStatusBarWithBlack()
        setStatusBarHeight(myStatusBar)
        action = intent.action
        if (Constant.ACTION_NEW_ADDRESS == action) {
            //新增地址  设置title
            tv_title.text = "新增收货地址"
        } else if (Constant.ACTION_UPDATE_ADDRESS == action) {
            tv_title.text = "修改地址"
            //需要获取地址
            oldAddress = intent.getParcelableExtra(Constant.ADDRESS_KEY)
            Log.e("oldAddress",oldAddress?.toString())
            ib_delete.visibility = View.VISIBLE
            et_username.setText(oldAddress?.username)
            et_phone.setText(oldAddress?.phone)
            val area = oldAddress?.addressInfo
            val areaSplit = area?.split(" ")
            et_area.text = areaSplit?.get(0)
            et_detail_address.setText(areaSplit?.get(1))
            if (oldAddress?.isDefault == 1) {
                cb_default_address.isChecked = true
            }
        }
    }

    private var firstData: ArrayList<String> = arrayListOf()
    private var secondData: HashMap<String, List<String>> = hashMapOf()
    private var thirdData: HashMap<String, List<String>> = hashMapOf()
    override fun initData(savedInstanceState: Bundle?) {
        val gson = Gson()
        val data = gson.fromJson<List<Province>>(getCityJson(), object : TypeToken<List<Province>>() {}.type)
//        val data = gson.fromJson<List<Province>>(getCityJson())
        generateData(data)
    }

    private fun generateData(data: List<Province>) {
        for (item in data) {
            firstData.add(item.label)
            val cityList = item.children
            val cityListString = arrayListOf<String>()
            for (city in cityList) {
                cityListString.add(city.label)
                val areaList = city.children
                val areaListString = arrayListOf<String>()
                for (area in areaList) {
                    areaListString.add(area.label)
                }
                thirdData[city.label] = areaListString

            }
            secondData[item.label] = cityListString
        }
    }

    override fun initListener(savedInstanceState: Bundle?) {
        super.initListener(savedInstanceState)
        val pickData = PickerData()
        pickData.mFirstData = firstData
        pickData.mSecondData = secondData
        pickData.mThirdData = thirdData
        val pickerView = PickerView(this, pickData)
        pickerView.setOnPickerClick(object : PickerView.OnPickerClicKListener {
            override fun onClick(pickerData: String) {
                et_area.text = pickerData
            }
        })
        et_area.setOnClickListener { pickerView.show() }
        ib_back.setOnClickListener { onBackPressed() }
        ib_delete.setOnClickListener { deleteAddress() }
        tv_save.setOnClickListener { saveOrUpdate() }
    }

    private fun deleteAddress() {
        //弹出dialog
        val contentView = inflate(R.layout.edit_delete_layout)
        val tvCancel = contentView.findViewById<TextView>(R.id.tv_cancel)
        val tvConfirm = contentView.findViewById<TextView>(R.id.tv_confirm)
        val dialog = CommonDialog.builder(this) {
            this.view = contentView
        }
        dialog.show()

        tvCancel.setOnClickListener { dialog.dismiss() }
        tvConfirm.setOnClickListener {
            addressViewModel.deleteAddress(oldAddress?._id!!)
            dialog.dismiss()
            val intent = Intent()
            setResult(Activity.RESULT_OK, intent)
            onBackPressed()
        }
    }

    private fun saveOrUpdate() {
        if (checkEditEmpty()) {
            return
        }
        val isDefault = cb_default_address.isChecked
        var default = 0
        if (isDefault) {
            default = 1
        }
        if (Constant.ACTION_NEW_ADDRESS == action) {
            addressViewModel.insertAddress(username, phone, address, default)
        } else if (Constant.ACTION_UPDATE_ADDRESS == action) {
            addressViewModel.updateAddress(oldAddress?._id!!, AddressTable.USERNAME to username,
                    AddressTable.PHONE to phone,
                    AddressTable.ADDRESS_INFO to address,
                    AddressTable.IS_DEFAULT to default)
        }
        val intent = Intent()
        setResult(Activity.RESULT_OK, intent)
        onBackPressed()
    }

    private lateinit var username: String
    private lateinit var phone: String
    private lateinit var address: String
    private fun checkEditEmpty(): Boolean {
        username = et_username.text.toString().trim()
        if (username.isNullOrEmpty()) {
            toast("收货人姓名不能为空")
            return true
        }
        phone = et_phone.text.toString().trim()
        if (phone.isNullOrEmpty()) {
            toast("手机号不能为空")
            return true
        }

        if (!phone.isPhone()) {
            toast("请输入正确的手机号")
            return true
        }

        val area = et_area.text.toString().trim()
        if (area.isNullOrEmpty()) {
            toast("请选择地区")
            return true
        }
        val addr = et_detail_address.text.toString().trim()
        if (addr.isNullOrEmpty()) {
            toast("请输入详细地址")
            return true
        }
        address = "$area $addr"
        return false
    }

    override fun initViewModel(): ViewModel? {
        addressViewModel = LViewModelProviders.of(this, AddressViewModel::class.java)
        addressViewModel.getOneAddressData().observe(this, Observer {

        })
        return addressViewModel
    }

    private fun getCityJson(): String {
        val sb = StringBuilder()
        try {
            val assetManager = this.assets
            val bf = BufferedReader(InputStreamReader(assetManager.open("region.json")))
            bf.forEachLine {
                sb.append(it)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return sb.toString()
    }
}
