package com.peaut.vegetables.adapter

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.peaut.vegetables.R
import com.peaut.vegetables.base.BaseAdapter
import com.peaut.vegetables.base.BaseViewHolder
import com.peaut.vegetables.model.CategoryItem
import com.peaut.vegetables.ui.activity.ProductDetailActivity
import com.peaut.vegetables.util.GlideApp
import com.peaut.vegetables.util.startActivity

/**
 * @author peaut
 * @package  com.peaut.vegetables.adapter
 * @fileName CategoryAdapter
 * @date on  2019/3/2  15:26
 */
class CategoryAdapter(context: Context) : BaseAdapter<CategoryItem>(context) {
    override fun getItemLayoutId(): Int = R.layout.item_category_layout

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        bindItemView(holder, position)
    }

    private fun bindItemView(holder: BaseViewHolder, position: Int) {
        val icon = holder.getView<ImageView>(R.id.iv_icon)
        val tvTitle = holder.getView<TextView>(R.id.tv_title)
        val tvStore = holder.getView<TextView>(R.id.tv_store)
        val tvPrice = holder.getView<TextView>(R.id.tv_price)
        val tvAddress = holder.getView<TextView>(R.id.tv_address)
        val itemData = getData()[position]
        tvTitle.text = itemData.title
        tvStore.text = itemData.store
        tvPrice.text = itemData.price
        tvAddress.text = itemData.address
        GlideApp.with(holder.itemView.context).load(itemData.icon).centerCrop().into(icon)
        holder.itemView.setOnClickListener {
            holder.itemView.context.startActivity<ProductDetailActivity>()
        }
    }
}