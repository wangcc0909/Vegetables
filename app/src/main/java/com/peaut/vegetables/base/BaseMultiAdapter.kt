package com.peaut.vegetables.base

import android.content.Context
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.util.SparseIntArray
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * @author peaut
 * @package  com.peaut.vegetables.base
 * @fileName BaseMultiAdapter
 * @date on  2019/2/26  14:57
 */
abstract class BaseMultiAdapter<T: MultiItemEntity>(private var context: Context): RecyclerView.Adapter<BaseViewHolder>(){
    private val mDatas = ArrayList<T>()
    /**
     * 存储不同类型的item布局
     */
    private val mLayouts = SparseIntArray()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val itemView = LayoutInflater.from(context).inflate(getItemLayoutId(viewType),parent,false)
        return BaseViewHolder(itemView)
    }

    private fun getItemLayoutId(viewType: Int): Int {
        return mLayouts.get(viewType)
    }

    override fun getItemCount(): Int {
        return mDatas.size
    }

    override fun getItemViewType(position: Int): Int {
        return mDatas[position].getItemType()
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        onBindItemView(holder, position)
    }

    /**
     * 根据item的类型绑定对应的数据，通过entity的getItemType来获取类型
     */
    abstract fun onBindItemView(holder: BaseViewHolder, position: Int)

    /**
     * 设置item的类型以及对应的类型
     */
    protected fun addItemType(viewType: Int,layoutId: Int){
        mLayouts.put(viewType,layoutId)
    }

    /**
     * 添加数据  局部刷新
     */
    fun addAll(data: List<T>) {
        val lastIndex = mDatas.size
        if (mDatas.addAll(data)) {
            notifyItemRangeInserted(lastIndex, mDatas.size)
        }
    }

    /**
     * 更新数据   全部刷新
     */
    fun updateData(datas: List<T>) {
        mDatas.clear()
        mDatas.addAll(datas)
        notifyDataSetChanged()
    }

    fun getData(): List<T> {
        return mDatas
    }

    private val mHandler = Handler()

    fun delayNotifyItemChanged(){
        val r = Runnable {
            notifyDataSetChanged()
        }
        mHandler.post(r)
    }
}