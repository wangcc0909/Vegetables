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
import com.peaut.vegetables.util.loadFromUrl

/**
 * @author peaut
 * @package  com.peaut.vegetables.adapter
 * @fileName AllOrderAdapter
 * @date on  2019/3/9  15:50
 */
class AllOrderAdapter(context: Context) : BaseMultiAdapter<MultiItemEntity>(context) {
    private var mListener: OnTextViewClickListener? = null
    init {
        //这里有5种布局
        addItemType(Constant.ITEM_EMPTY_TYPE, R.layout.item_order_empty_layout)
        addItemType(Constant.ITEM_COMPLETE_TYPE, R.layout.item_complete_layout)    //交易成功
        addItemType(Constant.ITEM_OBLIGATION_TYPE, R.layout.item_obi_pay_layout) //待付款
        addItemType(Constant.ITEM_DELIVERY_TYPE, R.layout.item_delivery_layout)   //待发货
        addItemType(Constant.ITEM_TAKE_GOODS_TYPE, R.layout.item_take_goods_layout) //待收货
    }

    override fun onBindItemView(holder: BaseViewHolder, position: Int) {
        val viewType = getData()[position].getItemType()
        when (viewType) {
            Constant.ITEM_EMPTY_TYPE -> {
                bindEmptyView(holder,position)
            }
            Constant.ITEM_COMPLETE_TYPE -> {
                bindCompleteView(holder,position)
            }
            Constant.ITEM_OBLIGATION_TYPE -> {
                bindObligationView(holder,position)
            }
            Constant.ITEM_DELIVERY_TYPE -> {
                bindDeliveryView(holder,position)
            }
            Constant.ITEM_TAKE_GOODS_TYPE -> {
                bindTakeGoodsView(holder,position)
            }
        }
    }

    private fun bindTakeGoodsView(holder: BaseViewHolder, position: Int) {
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

    private fun bindObligationView(holder: BaseViewHolder, position: Int) {
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

    private fun bindCompleteView(holder: BaseViewHolder, position: Int) {
        val ivIcon = holder.getView<ImageView>(R.id.iv_icon)
        val tvTitle = holder.getView<TextView>(R.id.tv_title)
        val tvPrice = holder.getView<TextView>(R.id.tv_price)
        val tvCount = holder.getView<TextView>(R.id.tv_count)
        val ivDelete = holder.getView<ImageView>(R.id.iv_delete)
        val data = getData()[position]
        if (data is OrderItem){
            ivIcon.loadFromUrl(data.icon)
            tvTitle.text = data.title
            tvPrice.text = "¥ ${data.price}"
            tvCount.text = "x${data.count}"
        }else {
            throw IllegalArgumentException("arg is error type")
        }

        ivDelete.setOnClickListener {
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