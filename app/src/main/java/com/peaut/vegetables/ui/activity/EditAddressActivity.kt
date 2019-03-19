package com.peaut.vegetables.ui.activity

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.peaut.vegetables.R
import com.peaut.vegetables.common.Constant
import com.peaut.vegetables.model.PickerData
import com.peaut.vegetables.model.Province
import com.peaut.vegetables.view.BaseActivity
import com.peaut.vegetables.viewmodel.AddressViewModel
import com.peaut.vegetables.viewmodel.base.LViewModelProviders
import com.peaut.vegetables.weight.PickerView
import kotlinx.android.synthetic.main.activity_edit_address.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class EditAddressActivity : BaseActivity() {
    private lateinit var addressViewModel: AddressViewModel
    private var action: String? = null
    override fun getResId(): Int = R.layout.activity_edit_address

    override fun initView(savedInstanceState: Bundle?) {
        setStatusBarWithBlack()
        setStatusBarHeight(myStatusBar)
        action = intent.action
        if (Constant.ACTION_NEW_ADDRESS == action) {
            //新增地址  设置title
            tv_title.text = "新增收货地址"
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
        val pickdata = PickerData()
        pickdata.mFirstData = firstData
        pickdata.mSecondData = secondData
        pickdata.mThirdData = thirdData
        val pickerView = PickerView(this, pickdata)
        pickerView.setOnPickerClick(object : PickerView.OnPickerClicKListener{
            override fun onClick(pickerData: String) {
                et_area.text = pickerData
            }
        })
        et_area.setOnClickListener {
            pickerView.show()
        }
        ib_back.setOnClickListener { onBackPressed() }
    }

    override fun initViewModel(): ViewModel? {
        addressViewModel = LViewModelProviders.of(this, AddressViewModel::class.java)
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
