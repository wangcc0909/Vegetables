package com.peaut.vegetables.datasource

import com.peaut.vegetables.datasource.base.IAddressDataSource
import com.peaut.vegetables.db.AddressTable
import com.peaut.vegetables.db.ext.database
import com.peaut.vegetables.db.ext.deleteOneData
import com.peaut.vegetables.db.ext.parseList
import com.peaut.vegetables.db.ext.parseOpt
import com.peaut.vegetables.db.model.Address
import com.peaut.vegetables.net.basis.BaseRemoteDataSource
import com.peaut.vegetables.net.basis.callback.RequestCallback
import com.peaut.vegetables.net.basis.config.HttpCode
import com.peaut.vegetables.net.basis.model.BaseResponseBody
import com.peaut.vegetables.util.applicationContext
import com.peaut.vegetables.viewmodel.base.BaseViewModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.update

/**
 * @author peaut
 * @package  com.peaut.vegetables.datasource
 * @fileName AddressDataSource
 * @date on  2019/3/16  16:49
 */
class AddressDataSource(baseViewModel: BaseViewModel) : BaseRemoteDataSource(baseViewModel), IAddressDataSource {

    override fun queryAddress(callback: RequestCallback<List<Address>>) {
        execute(getAddress(), callback)
    }

    override fun queryAddress(id: Int, callback: RequestCallback<Address>) {

    }

    override fun queryDefaultAddress(isDefault: Int, callback: RequestCallback<Address>) {
        compositeDisposable.add(getDefaultAddress(isDefault)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }

    override fun insertAddress(username: String, phone: String, address: String, default: Int) {
        compositeDisposable.add(saveAddress(username, phone, address, default)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }

    override fun updateAddress(id: Int, vararg values: Pair<String, Any?>) {
        compositeDisposable.add(updateAddressWith(id, *values)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }

    override fun deleteAddress(id: Int) {
        compositeDisposable.add(deleteOneAddress(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }

    private fun updateAddressWith(id: Int, vararg values: Pair<String, Any?>): Completable {
        return Completable.fromAction {
            applicationContext.database.use {
                update(AddressTable.TABLE_NAME, *values).whereSimple("_id = ?", "$id").exec()
            }
        }
    }

    private fun saveAddress(username: String, phone: String, address: String, default: Int): Completable {
        return Completable.fromAction {
            applicationContext.database.use {
                insert(AddressTable.TABLE_NAME, AddressTable.USERNAME to username,
                        AddressTable.PHONE to phone,
                        AddressTable.ADDRESS_INFO to address,
                        AddressTable.IS_DEFAULT to default)
            }
        }
    }

    private fun getDefaultAddress(isDefault: Int): Observable<BaseResponseBody<Address>> {
        val data = applicationContext.database.use {
            select(AddressTable.TABLE_NAME).whereSimple("isDefault = ?", "$isDefault").parseOpt { Address(HashMap(it)) }
        }
        val body = BaseResponseBody<Address>()
        body.data = data
        body.msg = ""
        body.code = HttpCode.CODE_SUCCESS
        return Observable.just(body)
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

    private fun deleteOneAddress(id: Int): Completable {
        return Completable.fromAction {
            applicationContext.database.use {
                deleteOneData(AddressTable.TABLE_NAME,"_id = ?","$id")
            }
        }
    }
}