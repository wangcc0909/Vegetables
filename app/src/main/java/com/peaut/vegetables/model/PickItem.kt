package com.peaut.vegetables.model

import com.peaut.vegetables.base.MultiItemEntity
import com.peaut.vegetables.common.Constant

/**
 * @author peaut
 * @package  com.peaut.vegetables.model
 * @fileName PickItem
 * @date on  2019/3/11  19:18
 */
data class PickItem(val title: String, val icon: String, val price: Float) : MultiItemEntity {
    override fun getItemType(): Int {
        return Constant.ITEM_COMMENT_TYPE
    }
}