package com.peaut.vegetables.viewmodel.base

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.peaut.vegetables.event.BaseActionEvent

/**
 * @author peaut
 * @package  com.peaut.httpmodule.viewmodel.base
 * @fileName BaseViewModel
 * @date on  2019/2/14  11:10
 */
open class BaseViewModel : ViewModel(), IViewModelAction {
    private var actionLiveData: MutableLiveData<BaseActionEvent> = MutableLiveData()
    var lifecycleOwner: LifecycleOwner? = null

    override fun startLoading() {
        startLoading("")
    }

    override fun startLoading(message: String) {
        val baseActionEvent = BaseActionEvent(BaseActionEvent.SHOW_LOADING_DIALOG)
        baseActionEvent.setMessage(message)
        actionLiveData.value = baseActionEvent
    }

    override fun dismissLoading() {
        actionLiveData.value = BaseActionEvent(BaseActionEvent.DISMISS_LOADING_DIALOG)
    }

    override fun showToast(message: String) {
        val baseActionEvent = BaseActionEvent(BaseActionEvent.SHOW_TOAST)
        baseActionEvent.setMessage(message)
        actionLiveData.value = baseActionEvent
    }

    override fun finish() {
        actionLiveData.value = BaseActionEvent(BaseActionEvent.FINISH)
    }

    override fun finishWithResultOK() {
        actionLiveData.value = BaseActionEvent(BaseActionEvent.FINISH_WITH_RESULT_OK)
    }

    override fun getActionLiveData(): MutableLiveData<BaseActionEvent> = actionLiveData


    fun setLifecyclerOwner(lifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner
    }

    override fun connectError(message: String) {
        val baseActionEvent = BaseActionEvent(BaseActionEvent.CONNECT_ERROR)
        baseActionEvent.setMessage(message)
        actionLiveData.value = baseActionEvent
    }

    override fun connectTimeout(message: String) {
        val baseActionEvent = BaseActionEvent(BaseActionEvent.CONNECT_TIMEOUT)
        baseActionEvent.setMessage(message)
        actionLiveData.value = baseActionEvent
    }

    override fun badNetwork(message: String) {
        val baseActionEvent = BaseActionEvent(BaseActionEvent.BAD_NETWORK)
        baseActionEvent.setMessage(message)
        actionLiveData.value = baseActionEvent
    }

    override fun parseError(message: String) {
        val baseActionEvent = BaseActionEvent(BaseActionEvent.PARSE_ERROR)
        baseActionEvent.setMessage(message)
        actionLiveData.value = baseActionEvent
    }

    override fun unknownError(message: String) {
        val baseActionEvent = BaseActionEvent(BaseActionEvent.UNKNOWN_ERROR)
        baseActionEvent.setMessage(message)
        actionLiveData.value = baseActionEvent
    }
}