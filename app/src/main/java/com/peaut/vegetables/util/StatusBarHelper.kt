package com.peaut.vegetables.util

import android.content.Context

/**
 * @author peaut
 * @package  com.peaut.vegetables.util
 * @fileName StatusBarHelper
 * @date on  2019/3/1  11:29
 */
object StatusBarHelper{
    private var sStatusBarHeight = -1
    private val STATUS_BAR_DEFAULT_HEIGHT_DP = 25

    fun getStatusBarHeight(context: Context): Int{
        val resourcesId = context.resources.getIdentifier("status_bar_height","dimen","android")
        sStatusBarHeight = if (resourcesId > 0){
            context.resources.getDimensionPixelSize(resourcesId)
        }else {
            STATUS_BAR_DEFAULT_HEIGHT_DP.toPx(context)
        }
        return sStatusBarHeight
    }
}