package com.peaut.vegetables.adapter

import android.content.Context
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.peaut.vegetables.R
import com.peaut.vegetables.base.BaseAdapter
import com.peaut.vegetables.base.BaseViewHolder
import com.peaut.vegetables.db.model.Address

/**
 * @author peaut
 * @package  com.peaut.vegetables.adapter
 * @fileName AddressListAdapter
 * @date on  2019/3/16  11:31
 */
class AddressListAdapter(context: Context) : BaseAdapter<Address>(context) {
    override fun getItemLayoutId(): Int = R.layout.item_address_layout

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        bindItemView(holder, position)
    }

    private fun bindItemView(holder: BaseViewHolder, position: Int) {
        val checkBox = holder.getView<ImageView>(R.id.checkbox)
        val tvUsername = holder.getView<TextView>(R.id.tv_username)
        val tvPhone = holder.getView<TextView>(R.id.tv_phone)
        val tvLabel = holder.getView<TextView>(R.id.tv_label)
        val ivEdit = holder.getView<ImageButton>(R.id.ib_edit)
        val tvAddress = holder.getView<TextView>(R.id.tv_address)
        val data = getData()[position]
        if (data.isSelect) {
            checkBox.visibility = View.VISIBLE
        }else {
            checkBox.visibility = View.GONE
        }
        tvUsername.text = data.username
        tvPhone.text = data.phone
//        tvLabel.text =
        tvAddress.text = data.addressInfo
        ivEdit.setOnClickListener { } //到地址编辑界面
        holder.itemView.setOnClickListener {
            //将选中的设置为未选中
            if (data.isSelect){
                return@setOnClickListener
            }
            for (item in getData()){
                item.isSelect = false
            }
            data.isSelect = true
            notifyDataSetChanged()
        }
    }
}