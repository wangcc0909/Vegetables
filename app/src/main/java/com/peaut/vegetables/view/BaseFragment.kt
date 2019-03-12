package com.peaut.vegetables.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.peaut.vegetables.R
import com.peaut.vegetables.event.BaseActionEvent
import com.peaut.vegetables.util.StatusBarHelper
import com.peaut.vegetables.util.setHeight
import com.peaut.vegetables.view.status.OnStatusLayoutClickListener
import com.peaut.vegetables.view.status.StatusLayoutManager
import com.peaut.vegetables.viewmodel.base.IViewModelAction
import com.peaut.vegetables.weight.ProgressLoading

/**
 * @author peaut
 * @package  com.peaut.vegetables.view
 * @fileName BaseFragment
 * @date on  2019/2/25  11:42
 */
abstract class BaseFragment: Fragment(), OnStatusLayoutClickListener {

    private var isFirstVisible: Boolean = true
    private var isFirstInvisible: Boolean = true
    //第一次onResume中的调用onUserVisible避免操作与onFirstUserVisible操作重复
    private var isFirstResume: Boolean = true
    private lateinit var rootView: View
    protected lateinit var mStatusLayoutManager: StatusLayoutManager
    private var mProgressLoading: ProgressLoading? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initStatusLayout()
        rootView = mStatusLayoutManager.getRootLayout()
        initOperate()
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModelEvent()
        initView()
        initData()
        initListener()
    }

    override fun onResume() {
        super.onResume()
        applyPermission()
        if (isFirstResume){
            isFirstResume = false
            return
        }
        if (userVisibleHint){
            onUserVisible()
        }
    }

    override fun onPause() {
        super.onPause()
        if (userVisibleHint){
            onUserInvisible()
        }
    }

    abstract fun getLayoutId(): Int

    private fun initStatusLayout() {
        mStatusLayoutManager = StatusLayoutManager.Builder(requireContext())
                .setContentLayout(getLayoutId())
                .setEmptyLayout(R.layout.layout_status_layout_manager_empty)
                .setErrorLayout(R.layout.layout_status_layout_manager_error)
                .setLoadingLayout(R.layout.layout_status_layout_manager_loading)
                .setErrorLayoutClickId(R.id.error_click)
                .setEmptyLayoutClickId(R.id.bt_status_empty_click)
                .newBuilder()
        mStatusLayoutManager.showContent()
        mStatusLayoutManager.setOnStatusLayoutClickListener(this)
    }

    /**
     * 初始化操作  在onCreateView中执行
     */
    open fun initOperate() {

    }

    abstract fun initViewModel(): ViewModel?

    open fun initViewModelList(): List<ViewModel>? {
        return null
    }

    private fun initViewModelEvent() {
        val viewModelList = initViewModelList()
        if (viewModelList != null && viewModelList.count() > 0) {
            observeEvent(viewModelList)
        } else {
            val viewModel = initViewModel()
            if (viewModel != null) {
                val modelList = ArrayList<ViewModel>()
                modelList.add(viewModel)
                observeEvent(modelList)
            }
        }
    }

    private fun observeEvent(viewModelList: List<ViewModel>) {
        for (viewModel in viewModelList) {
            val iViewModelAction = viewModel as IViewModelAction
            iViewModelAction.getActionLiveData().observe(this, Observer {
                baseActionEvent ->
                if (baseActionEvent != null) {
                    when (baseActionEvent.action) {
                        BaseActionEvent.SHOW_LOADING_DIALOG -> {
                            startLoading(baseActionEvent.getMessage())
                        }
                        BaseActionEvent.DISMISS_LOADING_DIALOG -> {
                            dismissLoading()
                        }
                        BaseActionEvent.SHOW_TOAST -> {
                            showToast(baseActionEvent.getMessage())
                        }
                        BaseActionEvent.CONNECT_ERROR -> {
                            connectError()
                        }
                        BaseActionEvent.CONNECT_TIMEOUT -> {
                            connectError()
                        }
                        BaseActionEvent.BAD_NETWORK -> {
                            connectError()
                        }
                        BaseActionEvent.PARSE_ERROR -> {
                            //解析错误
                        }
                        BaseActionEvent.UNKNOWN_ERROR -> {
                            //未知错误
                        }
                    }
                }
            })
        }
    }

    private fun connectError() {
        mStatusLayoutManager.showErrorLayout()
    }

    open fun showEmptyLayout(){
        mStatusLayoutManager.showEmptyLayout()
    }

    open fun startLoading(){
        startLoading("")
    }

    fun startLoading(message: String){
        if (mProgressLoading == null) {
            mProgressLoading = ProgressLoading(requireContext())
        }
        mProgressLoading?.showLoading()
    }

    fun dismissLoading(){
        mProgressLoading?.hideLoading()
    }

    protected fun showToast(message: String){
        Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT).show()
    }

    protected fun setStatusBarHeight(view: View){
        view.setHeight(StatusBarHelper.getStatusBarHeight(requireContext()))
    }

    open fun applyPermission() {

    }

    abstract fun initView()

    open fun initData() {

    }

    open fun initListener() {

    }

    //判断用户是否可见 在第一次onResume时是不会调用该方法的
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser){  //用户可见
            if (isFirstVisible){
                isFirstVisible = false
                onFirstUserVisible()
            }else {
                onUserVisible()
            }
        }else {  //用户不可见
            if (isFirstInvisible) {
                isFirstInvisible = false
                onFirstUserInvisible()
            }else {
                onUserInvisible()
            }
        }
    }

    /**
     * 用户不可见
     */
    open fun onUserInvisible() {
        Log.e("fragment","onUserInvisible 走了")
    }

    /**
     * 用户第一次不可见
     */
    open fun onFirstUserInvisible() {
        Log.e("fragment","onFirstUserInvisible 走了")
    }

    /**
     * 用户可见
     */
    open fun onUserVisible() {
        Log.e("fragment","onUserVisible 走了")
    }

    /**
     * 用户第一次可见
     */
    open fun onFirstUserVisible() {
        Log.e("fragment","onFirstUserVisible 走了")
    }

    override fun onEmptyViewClick(view: View) {
        emptyViewClick(view)
    }

    open fun emptyViewClick(view: View) {

    }

    override fun onErrorViewClick(view: View) {
        errorViewClick(view)
    }

    open fun errorViewClick(view: View) {

    }
}