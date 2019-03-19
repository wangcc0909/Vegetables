package com.peaut.vegetables.db.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

/**
 * @author peaut
 * @package  com.peaut.vegetables.db.model
 * @fileName Address
 * @date on  2019/3/16  11:43
 */
@Parcelize
data class Address constructor(private val map: @RawValue MutableMap<String, Any?>): Parcelable {

    var _id: Int by map
    var username: String by map
    var phone: String by map
    var addressInfo: String by map
    var isDefault: Int by map   //0 表示不是默认  1 表示默认
    var isSelect: Boolean = false

    constructor(username: String, phone: String, addressInfo: String, isDefault: Int?,isSelect: Boolean = false) : this(HashMap()) {
        this.username = username
        this.phone = phone
        this.addressInfo = addressInfo
        this.isDefault = isDefault ?: 0
        this.isSelect = isSelect
    }
}