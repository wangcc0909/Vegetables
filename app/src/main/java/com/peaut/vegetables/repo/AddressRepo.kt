package com.peaut.vegetables.repo

import android.arch.lifecycle.MutableLiveData
import com.peaut.vegetables.datasource.base.IAddressDataSource
import com.peaut.vegetables.db.model.Address
import com.peaut.vegetables.net.basis.BaseRepo
import com.peaut.vegetables.net.basis.callback.RequestCallback

/**
 * @author peaut
 * @package  com.peaut.vegetables.repo
 * @fileName AddressRepo
 * @date on  2019/3/16  16:44
 */
class AddressRepo(remoteDataSource: IAddressDataSource): BaseRepo<IAddressDataSource>(remoteDataSource){
    fun queryAddress(): MutableLiveData<List<Address>>{
        val addressLiveData = MutableLiveData<List<Address>>()
        remoteDataSource.queryAddress(object : RequestCallback<List<Address>>{
            override fun onSuccess(t: List<Address>) {
                addressLiveData.value = t
            }
        })
        return addressLiveData
    }
}