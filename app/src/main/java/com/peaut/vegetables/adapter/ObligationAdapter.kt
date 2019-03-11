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
class ObligationAdapter(context: Context) : BaseAdapter<ProductItem>(context) {
    /*init {
        addItemType(Constant.ITEM_SUPPLIER_TYPE, R.layout.item_like)
        addItemType(Constant.ITEM_PRODUCT_TYPE, R.layout.item_maybe_like)
    }

    override fun onBindItemView(holder: BaseViewHolder, position: Int) {
        if (position > 0) {
            val ivIcon = holder.getView<ImageView>(R.id.iv_icon)
            val tvDes = holder.getView<TextView>(R.id.tv_des)
            val tvComments = holder.getView<TextView>(R.id.tv_comments)
            val tvPrice = holder.getView<TextView>(R.id.tv_price)
            val data = getData()[position - 1]
            ivIcon.loadFromUrl(data.icon)
            tvDes.text = data.title
            tvComments.text = data.title
            tvPrice.text = "¥ ${data.price}"
        } else {

        }
    }

    override fun getItemCount(): Int {
        return getData().size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            Constant.ITEM_SUPPLIER_TYPE
        } else {
            Constant.ITEM_PRODUCT_TYPE
        }
    }
*/
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
}