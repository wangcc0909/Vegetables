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
    fun insertAddress(username: String, phone: String, address: String, default: Int)
    fun queryAddress(callback: RequestCallback<List<Address>>)
    fun queryAddress(id: Int, callback: RequestCallback<Address>)
    fun queryDefaultAddress(isDefault: Int,callback: RequestCallback<Address>)
    fun updateAddress(id: Int,vararg values: Pair<String, Any?>)
    fun deleteAddress(id: Int)
}