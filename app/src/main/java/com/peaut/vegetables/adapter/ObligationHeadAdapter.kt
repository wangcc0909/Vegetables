package com.peaut.vegetables.adapter

import android.content.Context
import android.util.Log
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
class ObligationHeadAdapter(context: Context) : BaseAdapter<ProductItem>(context) {
    private var mListener: OnTextViewClickListener? = null
    override fun getItemLayoutId(): Int = R.layout.item_obi_pay_layout

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        val ivIcon = holder.getView<ImageView>(R.id.iv_icon)
        val tvTitle = holder.getView<TextView>(R.id.tv_title)
        val tvPay = holder.getView<TextView>(R.id.tv_payment)
        val tvCancel = holder.getView<TextView>(R.id.tv_cancel)
        val data = getData()[position]
        ivIcon.loadFromUrl(data.icon)
        tvTitle.text = data.title
        tvCancel.setOnClickListener {
            Log.e("listSize","${getData().size}  position = ${holder.adapterPosition}")
            getData().removeAt(holder.adapterPosition)
//            notifyItemRemoved(position)
            notifyDataSetChanged()
            mListener?.onTextViewClick(holder.adapterPosition)
        }
    }

    interface OnTextViewClickListener {
        fun onTextViewClick(position: Int)
    }

    fun setOnTextViewClickListener(l: OnTextViewClickListener) {
        mListener = l
    }
}