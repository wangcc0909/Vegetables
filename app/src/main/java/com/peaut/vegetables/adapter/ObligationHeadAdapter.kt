package com.peaut.vegetables.adapter

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.peaut.vegetables.R
import com.peaut.vegetables.base.BaseAdapter
import com.peaut.vegetables.base.BaseViewHolder
import com.peaut.vegetables.model.ProductItem
import com.peaut.vegetables.util.loadFromUrl

/**
 * @author peaut
 * @package  com.peaut.vegetables.adapter
 * @fileName ObligationHeadAdapter
 * @date on  2019/3/9  17:38
 */
class ObligationHeadAdapter(context: Context): BaseAdapter<ProductItem>(context){

    override fun getItemLayoutId(): Int = R.layout.item_obi_head_layout

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        val ivIcon = holder.getView<ImageView>(R.id.iv_icon)
        val tvTitle = holder.getView<TextView>(R.id.tv_title)
        val data = getData()[position]
        ivIcon.loadFromUrl(data.icon)
        tvTitle.text = data.title
    }
}