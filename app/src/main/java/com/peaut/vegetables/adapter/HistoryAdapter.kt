package com.peaut.vegetables.adapter

import android.content.Context
import android.content.Intent
import android.widget.TextView
import com.peaut.vegetables.R
import com.peaut.vegetables.base.BaseAdapter
import com.peaut.vegetables.base.BaseViewHolder
import com.peaut.vegetables.ui.activity.SearchActivity
import com.peaut.vegetables.ui.activity.SearchResultActivity

/**
 * @author peaut
 * @package  com.peaut.vegetables.adapter
 * @fileName HistoryAdapter
 * @date on  2019/3/7  15:19
 */
class HistoryAdapter(var context: Context): BaseAdapter<String>(context){
    override fun getItemLayoutId(): Int = R.layout.item_search_history_layout

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        bindItemView(context,holder,position)
    }

    private fun bindItemView(context: Context,holder: BaseViewHolder, position: Int) {
        val tvHistory = holder.getView<TextView>(R.id.tv_history)
        val result = getData()[position]
        tvHistory.text = result
        tvHistory.setOnClickListener {
            val intent = Intent(context,SearchResultActivity::class.java)
            intent.putExtra("key",result)
            context.startActivity(intent)
            //需要将activity finish掉
            (context as SearchActivity).finish()
        }
    }

}