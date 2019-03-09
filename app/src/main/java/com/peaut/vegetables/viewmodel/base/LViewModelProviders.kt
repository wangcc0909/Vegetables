package com.peaut.vegetables.viewmodel.base

import android.arch.lifecycle.ViewModelProviders
import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity


/**
 * @author peaut
 * @package  com.peaut.httpmodule.viewmodel.base
 * @fileName LViewModelProviders
 * @date on  2019/2/15  17:53
 */
object LViewModelProviders {

    fun <T : BaseViewModel> of(@NonNull activity: FragmentActivity, modelClass: Class<T>): T {
        val t = ViewModelProviders.of(activity).get(modelClass)
        t.lifecycleOwner = activity
        return t
    }

    fun <T : BaseViewModel> of(@NonNull fragment: Fragment, modelClass: Class<T>): T {
        val t = ViewModelProviders.of(fragment).get(modelClass)
        t.lifecycleOwner = fragment
        return t
    }
}