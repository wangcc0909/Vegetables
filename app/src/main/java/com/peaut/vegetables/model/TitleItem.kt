package com.peaut.vegetables.model

import com.peaut.vegetables.base.MultiItemEntity
import com.peaut.vegetables.common.Constant

/**
 * @author peaut
 * @package  com.peaut.vegetables.model
 * @fileName TitleItem
 * @date on  2019/3/11  19:23
 */
data class TitleItem(val viewType: Int = Constant.ITEM_Title_TYPE): MultiItemEntity{
    override fun getItemType(): Int {
        return this.viewType
    }
}