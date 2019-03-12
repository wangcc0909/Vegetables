package com.peaut.vegetables.adapter

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.peaut.vegetables.R
import com.peaut.vegetables.base.BaseMultiAdapter
import com.peaut.vegetables.base.BaseViewHolder
import com.peaut.vegetables.base.MultiItemEntity
import com.peaut.vegetables.common.Constant
import com.peaut.vegetables.listener.OnTextViewClickListener
import com.peaut.vegetables.model.OrderItem
import com.peaut.vegetables.model.PickItem
import com.peaut.vegetables.util.loadFromUrl

/**
 * @author peaut
 * @package  com.peaut.vegetables.adapter
 * @fileName DeliveryAdapter
 * @date on  2019/3/12  10:30
 */
class DeliveryAdapter(context: Context) : BaseMultiAdapter<MultiItemEntity>(context) {
    private var mListener: OnTextViewClickListener? = null
    init {
        addItemType(Constant.ITEM_EMPTY_TYPE, R.layout.item_order_empty_layout)
        addItemType(Constant.ITEM_ORDER_TYPE, R.layout.item_delivery_layout)
        addItemType(Constant.ITEM_TITLE_TYPE, R.layout.item_wanner_layout)
        addItemType(Constant.ITEM_COMMENT_TYPE, R.layout.item_maybe_like)
    }

    override fun onBindItemView(holder: BaseViewHolder, position: Int) {
        val viewType = getData()[position].getItemType()
        when(viewType){
            Constant.ITEM_EMPTY_TYPE -> {
                bindEmptyView(holder,position)
            }
            Constant.ITEM_ORDER_TYPE -> {
                bindDeliveryView(holder,position)
            }
            Constant.ITEM_TITLE_TYPE -> {
                bindTitleView(holder,position)
            }
            Constant.ITEM_COMMENT_TYPE -> {
                bindCommentView(holder,position)
            }
        }
    }

    private fun bindCommentView(holder: BaseViewHolder, position: Int) {
        val ivIcon = holder.getView<ImageView>(R.id.iv_icon)
        val tvDes = holder.getView<TextView>(R.id.tv_des)
        val tvPrice = holder.getView<TextView>(R.id.tv_price)
        val data  = getData()[position]
        if (data is PickItem){
            ivIcon.loadFromUrl(data.icon)
            tvDes.text = data.title
            tvPrice.text = "¥ ${data.price}"
        }else {
            throw IllegalArgumentException("arg is error type")
        }
    }

    private fun bindTitleView(holder: BaseViewHolder, position: Int) {

    }

    private fun bindDeliveryView(holder: BaseViewHolder, position: Int) {
        val ivIcon = holder.getView<ImageView>(R.id.iv_icon)
        val tvTitle = holder.getView<TextView>(R.id.tv_title)
        val tvPrice = holder.getView<TextView>(R.id.tv_price)
        val tvCount = holder.getView<TextView>(R.id.tv_count)
        val tvCancel = holder.getView<TextView>(R.id.tv_cancel)
        val data = getData()[position]
        if (data is OrderItem){
            ivIcon.loadFromUrl(data.icon)
            tvTitle.text = data.title
            tvPrice.text = "¥ ${data.price}"
            tvCount.text = "x${data.count}"
        }else {
            throw IllegalArgumentException("arg is error type")
        }

        tvCancel.setOnClickListener {
            (getData() as ArrayList).removeAt(position)
            notifyDataSetChanged()
            mListener?.onTextViewClick(position)
        }
    }

    private fun bindEmptyView(holder: BaseViewHolder, position: Int) {

    }

    fun setOnTextViewClickListener(l: OnTextViewClickListener) {
        mListener = l
    }
}