package com.peaut.vegetables.util

import android.annotation.SuppressLint
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.util.Log



/**
 * Created by wangChongChong on 2017/11/8.
 */
object BottomNavigationViewHelper{

    @SuppressLint("RestrictedApi")
    fun disableShiftMode(view: BottomNavigationView){
        val menuView = view.getChildAt(0) as BottomNavigationMenuView
        try {
            menuView.labelVisibilityMode = 1
            for (index in 0 until  menuView.childCount){
                val item = menuView.getChildAt(index) as BottomNavigationItemView
                item.setShifting(false)
            }
        }catch (e: Exception){
            Log.e("BNVHelper", "Unable to get shift mode field", e)
        }
    }
}