package com.peaut.vegetables.model

import com.peaut.vegetables.base.MultiItemEntity
import com.peaut.vegetables.common.Constant

/**
 * @author peaut
 * @package  com.peaut.vegetables.model
 * @fileName ProductItem
 * @date on  2019/3/4  17:46
 */
data class ProductItem(val title: String, val price: Double, val icon: String,
                       val isExtent: Boolean, var count: Int,val supplierId: Int, var isSelected: Boolean = false) : MultiItemEntity {
    override fun getItemType(): Int {
        return Constant.ITEM_PRODUCT_TYPE
    }
}