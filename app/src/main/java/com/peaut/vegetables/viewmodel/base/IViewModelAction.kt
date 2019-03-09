package com.peaut.vegetables.viewmodel.base

import android.arch.lifecycle.MutableLiveData
import com.peaut.vegetables.event.BaseActionEvent

/**
 * @author peaut
 * @package  com.peaut.httpmodule.viewmodel.base
 * @fileName IViewModelAction
 * @date on  2019/2/14  11:06
 */
interface IViewModelAction {
    fun startLoading()

    fun startLoading(message: String)

    fun dismissLoading()

    fun showToast(message: String)

    fun finish()

    fun finishWithResultOK()

    fun getActionLiveData(): MutableLiveData<BaseActionEvent>

    fun connectError(message: String)  //网络连接失败

    fun connectTimeout(message: String) //网络连接超时

    fun badNetwork(message: String)  //网络连接异常

    fun parseError(message: String)  //解析出错

    fun unknownError(message: String)
}