package com.peaut.vegetables.adapter

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.peaut.vegetables.R
import com.peaut.vegetables.base.BaseMultiAdapter
import com.peaut.vegetables.base.BaseViewHolder
import com.peaut.vegetables.common.Constant
import com.peaut.vegetables.model.HomeItem
import com.peaut.vegetables.ui.activity.ProductDetailActivity
import com.peaut.vegetables.util.loadFromUrl
import com.peaut.vegetables.util.startActivity

/**
 * @author peaut
 * @package  com.peaut.vegetables.adapter
 * @fileName SearchResultAdapter
 * @date on  2019/3/7  17:38
 */
class SearchResultAdapter(context: Context) : BaseMultiAdapter<HomeItem>(context) {
    private var isTable = false

    init {
        //默认线性布局
        addItemType(Constant.ITEM_LINE_TYPE, R.layout.item_supplier_layout)
        addItemType(Constant.ITEM_GRID_TYPE, R.layout.item_grid_layout)
    }

    override fun onBindItemView(holder: BaseViewHolder, position: Int) {
        val itemViewType = getItemViewType(position)
        if (itemViewType == Constant.ITEM_LINE_TYPE) {
            bindLineItemView(holder, position)
        } else {
            bindGridItemView(holder, position)
        }
    }

    private fun bindGridItemView(holder: BaseViewHolder, position: Int) {
        val ivIcon = holder.getView<ImageView>(R.id.iv_icon)
        val tvTitle = holder.getView<TextView>(R.id.tv_title)
        val tvPrice = holder.getView<TextView>(R.id.tv_price)
        val data = getData()[position]
        ivIcon.loadFromUrl(data.icon)
        tvTitle.text = data.name
        tvPrice.text = "${data.start}"
    }

    private fun bindLineItemView(holder: BaseViewHolder, position: Int) {
        val textView = holder.getView<TextView>(R.id.tv_shop_name)
        val ivIcon = holder.getView<ImageView>(R.id.iv_icon)
        val tvStart = holder.getView<TextView>(R.id.tv_star)
        val tvAddress = holder.getView<TextView>(R.id.tv_address)
        val tvDistance = holder.getView<TextView>(R.id.tv_distance)
        val data = getData()[position]
        textView.text = data.name
        ivIcon.loadFromUrl(data.icon)
        tvStart.text = "${data.start}"
        tvAddress.text = data.address
        tvDistance.text = data.distance

        holder.itemView.setOnClickListener {
            holder.itemView.context.startActivity<ProductDetailActivity>()
        }
    }

    fun switchType(isTable: Boolean){
        this.isTable = isTable
        notifyItemRangeChanged(0,getData().size)
    }

    override fun getItemViewType(position: Int): Int {
        return if (isTable) {
            Constant.ITEM_GRID_TYPE
        } else {
            Constant.ITEM_LINE_TYPE
        }
    }
}