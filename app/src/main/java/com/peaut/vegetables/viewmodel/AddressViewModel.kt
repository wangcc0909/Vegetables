package com.peaut.vegetables.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.peaut.vegetables.datasource.AddressDataSource
import com.peaut.vegetables.db.model.Address
import com.peaut.vegetables.repo.AddressRepo
import com.peaut.vegetables.viewmodel.base.BaseViewModel

/**
 * @author peaut
 * @package  com.peaut.vegetables.viewmodel
 * @fileName AddressViewModel
 * @date on  2019/3/16  16:42
 */
class AddressViewModel : BaseViewModel() {
    private val addressLiveData = MutableLiveData<List<Address>>()
    private val addressOneLiveData = MutableLiveData<Address>()
    private val defaultAddressLiveData = MutableLiveData<Address>()
    private val addressRepo = AddressRepo(AddressDataSource(this))

    fun queryAddress() {
        addressRepo.queryAddress().observe(lifecycleOwner!!, Observer {
            addressLiveData.value = it
        })
    }

    fun queryAddress(id: Int) {
        addressRepo.queryAddress(id).observe(lifecycleOwner!!, Observer {
            addressOneLiveData.value = it
        })
    }

    fun queryDefaultAddress(isDefault: Int) {
        addressRepo.queryDefaultAddress(isDefault)
    }

    fun insertAddress(username: String, phone: String, address: String, default: Int) {
        addressRepo.insertAddress(username, phone, address, default)
        queryAddress()
    }

    fun updateAddress(id: Int, vararg values: Pair<String, Any?>) {
        addressRepo.updateAddress(id, *values)
        queryAddress()
    }

    fun deleteAddress(id: Int) {
        addressRepo.deleteAddress(id)
        queryAddress()
    }

    fun getAddressData() = addressLiveData

    fun getOneAddressData() = addressOneLiveData
}