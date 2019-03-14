package com.peaut.vegetables.adapter

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.peaut.vegetables.R
import com.peaut.vegetables.base.BaseMultiAdapter
import com.peaut.vegetables.base.BaseViewHolder
import com.peaut.vegetables.base.MultiItemEntity
import com.peaut.vegetables.common.Constant
import com.peaut.vegetables.listener.OnSendClickListener
import com.peaut.vegetables.model.ProductItem
import com.peaut.vegetables.model.SupplierItem
import com.peaut.vegetables.util.loadFromUrl

/**
 * @author peaut
 * @package  com.peaut.vegetables.adapter
 * @fileName PayCenterAdapter
 * @date on  2019/3/13  16:16
 */
class PayCenterAdapter(context: Context): BaseMultiAdapter<MultiItemEntity>(context){
    private var mListener: OnSendClickListener?= null
    init {
        addItemType(Constant.ITEM_SUPPLIER_TYPE, R.layout.item_pay_supplier_layout)
        addItemType(Constant.ITEM_PRODUCT_TYPE,R.layout.item_pay_product_layout)
    }

    override fun onBindItemView(holder: BaseViewHolder, position: Int) {
        val viewType = getData()[position].getItemType()
        when(viewType){
            Constant.ITEM_SUPPLIER_TYPE -> {
                bindSupplier(holder,position)
            }
            Constant.ITEM_PRODUCT_TYPE -> {
                bindProductView(holder,position)
            }
        }
    }

    private fun bindProductView(holder: BaseViewHolder, position: Int) {
        val ivIcon = holder.getView<ImageView>(R.id.iv_icon)
        val tvTitle = holder.getView<TextView>(R.id.tv_title)
        val tvPrice = holder.getView<TextView>(R.id.tv_price)
        val tvCount = holder.getView<TextView>(R.id.tv_count)
        val rlContainer = holder.getView<RelativeLayout>(R.id.rl_send)  //配送的点击事件
        val tvLogistics = holder.getView<TextView>(R.id.tv_logistics)
        val tvLogisticsInfo = holder.getView<TextView>(R.id.tv_logistics_info)
        val etWords = holder.getView<EditText>(R.id.et_words)
        val data = getData()[position]
        if (data is ProductItem){
            ivIcon.loadFromUrl(data.icon)
            tvTitle.text = data.title
            tvPrice.text = "¥ ${data.price}"
            tvCount.text = "${data.count}"
        }else {
            throw java.lang.IllegalArgumentException("PayCenterAdapter argument error")
        }
        rlContainer.setOnClickListener {
            //将两个view传出去
            mListener?.onClick(tvLogistics,tvLogistics,position)
        }
        etWords.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                Log.e("输入完成",s?.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    private fun bindSupplier(holder: BaseViewHolder, position: Int) {
        val tvSupplier = holder.getView<TextView>(R.id.tv_supplier)
        val data = getData()[position]
        if (data is SupplierItem){
            tvSupplier.text = data.name
        }else {
            throw IllegalArgumentException("PayCenterAdapter param error")
        }
    }

    fun setOnSendClickListener(l: OnSendClickListener){
        mListener = l
    }
}