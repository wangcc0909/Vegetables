package com.peaut.vegetables.view.status

import android.view.View

/**
 * @author peaut
 * @package  com.peaut.vegetables.view.status
 * @fileName OnStatusLayoutClickListener
 * @date on  2019/2/22  15:53
 */
interface OnStatusLayoutClickListener {

    /**
     * 空布局点击
     * @param view View
     */
    fun onEmptyViewClick(view: View)

    /**
     * 错误布局点击
     * @param view View
     */
    fun onErrorViewClick(view: View)
}