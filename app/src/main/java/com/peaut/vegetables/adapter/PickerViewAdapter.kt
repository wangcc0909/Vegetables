package com.peaut.vegetables.adapter

import android.content.Context
import android.widget.TextView
import com.peaut.vegetables.R
import com.peaut.vegetables.base.BaseAdapter
import com.peaut.vegetables.base.BaseViewHolder

/**
 * @author peaut
 * @package  com.peaut.vegetables.adapter
 * @fileName PickerViewAdapter
 * @date on  2019/3/18  20:08
 */
class PickerViewAdapter(context: Context): BaseAdapter<String>(context){
    override fun getItemLayoutId(): Int = R.layout.text_view

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        val dataText = holder.getView<TextView>(R.id.data_text)
        val data = getData()[position]
        dataText.text = data
    }
}