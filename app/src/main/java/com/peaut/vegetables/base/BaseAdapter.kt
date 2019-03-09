package com.peaut.vegetables.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author peaut
 * @package  com.peaut.vegetables.base
 * @fileName BaseAdapter
 * @date on  2019/2/26  14:34
 */
abstract class BaseAdapter<T>(private var context: Context) : RecyclerView.Adapter<BaseViewHolder>() {

    private val mData = ArrayList<T>()
    private var mListener: OnclickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val itemView = LayoutInflater.from(context).inflate(getItemLayoutId(), parent, false)
        return BaseViewHolder(itemView)
    }

    /**
     * 获取item布局id
     */
    abstract fun getItemLayoutId(): Int

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            mListener?.onClick(it, position)
        }
        onBindView(holder, position)
    }

    /**
     * 设置item的内容
     */
    abstract fun onBindView(holder: BaseViewHolder, position: Int)

    interface OnclickListener {
        fun onClick(it: View, position: Int)
    }

    fun setOnClickListener(l: OnclickListener) {
        mListener = l
    }

    /**
     * 添加数据  局部刷新
     */
    fun addAll(data: List<T>) {
        val lastIndex = mData.size
        if (mData.addAll(data)) {
            notifyItemRangeInserted(lastIndex, mData.size)
        }
    }

    /**
     * 更新数据   全部刷新
     */
    fun updateData(datas: List<T>) {
        mData.clear()
        mData.addAll(datas)
        notifyDataSetChanged()
    }

    fun getData(): List<T> {
        return mData
    }
}