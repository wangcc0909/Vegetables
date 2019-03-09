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
 * @fileName ObligationAdapter
 * @date on  2019/3/9  16:15
 */
class ObligationAdapter(context: Context): BaseAdapter<ProductItem>(context){
    override fun getItemLayoutId(): Int = R.layout.item_maybe_like

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        val ivIcon = holder.getView<ImageView>(R.id.iv_icon)
        val tvDes = holder.getView<TextView>(R.id.tv_des)
        val tvComments = holder.getView<TextView>(R.id.tv_comments)
        val tvPrice = holder.getView<TextView>(R.id.tv_price)
        val data = getData()[position]
        ivIcon.loadFromUrl(data.icon)
        tvDes.text = data.title
        tvComments.text = data.title
        tvPrice.text = "¥ ${data.price}"
    }

    /*init {
        addItemType(Constant.ITEM_OBLIGATION_TYPE, R.layout.item_obligation_layout)
        addItemType(Constant.ITEM_LIKE,R.layout.item_like)
    }
    override fun onBindItemView(holder: BaseViewHolder, position: Int) {

    }*/
}