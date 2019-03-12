package com.peaut.vegetables.model

import com.peaut.vegetables.base.MultiItemEntity
import com.peaut.vegetables.common.Constant

/**
 * @author peaut
 * @package  com.peaut.vegetables.model
 * @fileName OrderItem
 * @date on  2019/3/11  19:18
 */
data class OrderItem(val title: String,val icon: String,val price: Float,val count: Int,val viewType: Int = Constant.ITEM_ORDER_TYPE): MultiItemEntity{
    override fun getItemType(): Int {
        return this.viewType
    }
}