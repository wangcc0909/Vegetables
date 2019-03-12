package com.peaut.vegetables.adapter

import android.content.Context
import android.widget.TextView
import com.peaut.vegetables.R
import com.peaut.vegetables.base.BaseAdapter
import com.peaut.vegetables.base.BaseViewHolder
import com.peaut.vegetables.model.Message
import com.peaut.vegetables.util.loadFromUrl
import de.hdodenhof.circleimageview.CircleImageView

/**
 * @author peaut
 * @package  com.peaut.vegetables.adapter
 * @fileName MessageAdapter
 * @date on  2019/3/12  19:13
 */
class MessageAdapter(context: Context) : BaseAdapter<Message>(context) {
    override fun getItemLayoutId(): Int = R.layout.item_message

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        bindItemView(holder, position)
    }

    private fun bindItemView(holder: BaseViewHolder, position: Int) {
        val icon = holder.getView<CircleImageView>(R.id.icon)
        val tvTitle = holder.getView<TextView>(R.id.tv_title)
        val tvTime = holder.getView<TextView>(R.id.tv_time)
        val tvDes = holder.getView<TextView>(R.id.tv_des)
        val data = getData()[position]
        icon.loadFromUrl(data.icon)
        tvTitle.text = data.title
        tvTime.text = data.time
        tvDes.text = data.des
    }
}