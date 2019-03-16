package com.peaut.vegetables.datasource

import com.peaut.vegetables.datasource.base.IAddressDataSource
import com.peaut.vegetables.db.AddressTable
import com.peaut.vegetables.db.ext.database
import com.peaut.vegetables.db.ext.parseList
import com.peaut.vegetables.db.model.Address
import com.peaut.vegetables.net.basis.BaseRemoteDataSource
import com.peaut.vegetables.net.basis.callback.RequestCallback
import com.peaut.vegetables.net.basis.config.HttpCode
import com.peaut.vegetables.net.basis.model.BaseResponseBody
import com.peaut.vegetables.util.applicationContext
import com.peaut.vegetables.viewmodel.base.BaseViewModel
import io.reactivex.Observable
import org.jetbrains.anko.db.select

/**
 * @author peaut
 * @package  com.peaut.vegetables.datasource
 * @fileName AddressDataSource
 * @date on  2019/3/16  16:49
 */
class AddressDataSource(baseViewModel: BaseViewModel) : BaseRemoteDataSource(baseViewModel), IAddressDataSource {
    override fun queryAddress(callback: RequestCallback<List<Address>>) {
        execute(getAddress(),callback)
    }

    override fun queryAddress(id: Int, callback: RequestCallback<Address>) {

    }

    override fun insertAddress() {

    }

    override fun updateAddress() {

    }

    override fun deleteAddress(id: Int) {

    }

    private fun getAddress(): Observable<BaseResponseBody<List<Address>>> {
        val data = applicationContext.database.use {
            select(AddressTable.TABLE_NAME).parseList { Address(HashMap(it)) }
        }
        val body = BaseResponseBody<List<Address>>()
        body.data = data
        body.msg = ""
        body.code = HttpCode.CODE_SUCCESS
        return Observable.just(body)
    }
}