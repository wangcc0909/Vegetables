package com.peaut.vegetables.adapter

import android.content.Context
import com.peaut.vegetables.R
import com.peaut.vegetables.base.BaseAdapter
import com.peaut.vegetables.base.BaseViewHolder
import com.peaut.vegetables.model.ProductItem

/**
 * @author peaut
 * @package  com.peaut.vegetables.adapter
 * @fileName ObligationHeadAdapter
 * @date on  2019/3/9  17:38
 */
class ObligationHeadAdapter(context: Context): BaseAdapter<ProductItem>(context){
    override fun getItemLayoutId(): Int = R.layout.item_obi_head_layout

    override fun onBindView(holder: BaseViewHolder, position: Int) {

    }
}