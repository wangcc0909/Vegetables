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
    private val addressRepo = AddressRepo(AddressDataSource(this))

    fun queryAddress() {
        addressRepo.queryAddress().observe(lifecycleOwner!!, Observer {
            addressLiveData.value = it
        })
    }

    fun getAddressData() = addressLiveData
}