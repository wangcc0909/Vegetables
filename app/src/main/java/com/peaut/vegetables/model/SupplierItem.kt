package com.peaut.vegetables.model

import com.peaut.vegetables.base.MultiItemEntity
import com.peaut.vegetables.common.Constant

/**
 * @author peaut
 * @package  com.peaut.vegetables.model
 * @fileName SupplierItem
 * @date on  2019/3/4  17:46
 */
data class SupplierItem(val name: String,val supplierId: Int,var isChecked: Boolean = false) : MultiItemEntity {
    override fun getItemType(): Int {
        return Constant.ITEM_SUPPLIER_TYPE
    }
}