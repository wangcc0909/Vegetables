package com.peaut.vegetables.model

import com.peaut.vegetables.base.MultiItemEntity
import com.peaut.vegetables.common.Constant

/**
 * @author peaut
 * @package  com.peaut.vegetables.model
 * @fileName EmptyItem
 * @date on  2019/3/11  19:21
 */
data class EmptyItem(val viewType: Int = Constant.ITEM_EMPTY_TYPE) : MultiItemEntity {
    override fun getItemType(): Int {
        return this.viewType
    }
}