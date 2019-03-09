package com.peaut.vegetables.base

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View
import androidx.annotation.IdRes

/**
 * @author peaut
 * @package  com.peaut.vegetables.base
 * @fileName BaseViewHolder
 * @date on  2019/2/26  14:35
 */
class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val views: SparseArray<View> = SparseArray()

    @Suppress("UNCHECKED_CAST")
    fun <T : View> getView(@IdRes viewId: Int): T {
        var view = views.get(viewId)
        if (view == null) {
            view = itemView.findViewById(viewId)
            views.put(viewId, view)
        }
        return view as T
    }
}