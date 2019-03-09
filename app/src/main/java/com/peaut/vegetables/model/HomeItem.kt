package com.peaut.vegetables.model

import com.peaut.vegetables.base.MultiItemEntity

/**
 * @author peaut
 * @package  com.peaut.vegetables.model
 * @fileName HomeItem
 * @date on  2019/2/26  17:21
 */
data class HomeItem(val name: String,val icon: String,val start: Float,val address: String,val distance: String,var viewType: Int): MultiItemEntity {
    override fun getItemType(): Int {
        return this.viewType
    }
}