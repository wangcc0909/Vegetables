package com.peaut.vegetables.datasource.base

import com.peaut.vegetables.db.model.Address
import com.peaut.vegetables.net.basis.callback.RequestCallback

/**
 * @author peaut
 * @package  com.peaut.vegetables.datasource.base
 * @fileName IAddressDataSource
 * @date on  2019/3/16  16:45
 */
interface IAddressDataSource {
    fun insertAddress()
    fun queryAddress(callback: RequestCallback<List<Address>>)
    fun queryAddress(id: Int, callback: RequestCallback<Address>)
    fun updateAddress()
    fun deleteAddress(id: Int)
}