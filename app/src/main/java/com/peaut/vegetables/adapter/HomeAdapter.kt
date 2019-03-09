package com.peaut.vegetables.adapter

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.peaut.vegetables.R
import com.peaut.vegetables.base.BaseAdapter
import com.peaut.vegetables.base.BaseViewHolder
import com.peaut.vegetables.model.HomeItem
import com.peaut.vegetables.ui.activity.ProductDetailActivity
import com.peaut.vegetables.util.GlideApp
import com.peaut.vegetables.util.startActivity

/**
 * @author peaut
 * @package  com.peaut.vegetables.adapter
 * @fileName HomeAdapter
 * @date on  2019/2/26  15:14
 */
class HomeAdapter(context: Context) : BaseAdapter<HomeItem>(context) {
    override fun getItemLayoutId(): Int = R.layout.item_supplier_layout

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        bindItemView(holder, position)
    }

    private fun bindItemView(holder: BaseViewHolder, position: Int) {
        val textView = holder.getView<TextView>(R.id.tv_shop_name)
        val ivIcon = holder.getView<ImageView>(R.id.iv_icon)
        val tvStart = holder.getView<TextView>(R.id.tv_star)
        val tvAddress = holder.getView<TextView>(R.id.tv_address)
        val tvDistance = holder.getView<TextView>(R.id.tv_distance)
        val data = getData()[position]
        textView.text = data.name
        GlideApp.with(holder.itemView.context).load(data.icon).centerCrop().into(ivIcon)
        tvStart.text = "${data.start}"
        tvAddress.text = data.address
        tvDistance.text = data.distance

        holder.itemView.setOnClickListener {
            holder.itemView.context.startActivity<ProductDetailActivity>()
        }
    }
}