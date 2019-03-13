package com.peaut.vegetables.adapter

import android.content.Context
import com.peaut.vegetables.R
import com.peaut.vegetables.base.BaseMultiAdapter
import com.peaut.vegetables.base.BaseViewHolder
import com.peaut.vegetables.base.MultiItemEntity
import com.peaut.vegetables.common.Constant

/**
 * @author peaut
 * @package  com.peaut.vegetables.adapter
 * @fileName PayCenterAdapter
 * @date on  2019/3/13  16:16
 */
class PayCenterAdapter(context: Context): BaseMultiAdapter<MultiItemEntity>(context){

    init {
        addItemType(Constant.ITEM_SUPPLIER_TYPE, R.layout.item_pay_supplier_layout)
        addItemType(Constant.ITEM_PRODUCT_TYPE,R.layout.item_pay_product_layout)
    }

    override fun onBindItemView(holder: BaseViewHolder, position: Int) {

    }
}