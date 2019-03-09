package com.peaut.vegetables.view

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.peaut.vegetables.common.AppManager
import com.peaut.vegetables.event.BaseActionEvent
import com.peaut.vegetables.util.StatusBarHelper
import com.peaut.vegetables.util.setHeight
import com.peaut.vegetables.viewmodel.base.IViewModelAction
import com.peaut.vegetables.weight.ProgressLoading

/**
 * @author peaut
 * @package  com.peaut.vegetables.view
 * @fileName BaseActivity
 * @date on  2019/2/25  11:42
 */
abstract class BaseActivity : AppCompatActivity() {
    private var mProgressLoading: ProgressLoading? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prepareLayout()
        setContentView(getResId())
        AppManager.instance.addActivity(this)
        initViewModelEvent()
        initView(savedInstanceState)
        initData(savedInstanceState)
        initListener(savedInstanceState)
    }

    open fun prepareLayout() {

    }

    abstract fun getResId(): Int
    abstract fun initView(savedInstanceState: Bundle?)
    abstract fun initData(savedInstanceState: Bundle?)

    open fun initListener(savedInstanceState: Bundle?) {

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
            iViewModelAction.getActionLiveData().observe(this, Observer { baseActionEvent ->
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
                        BaseActionEvent.FINISH -> {
                            finish()
                        }
                        BaseActionEvent.FINISH_WITH_RESULT_OK -> {
                            finishWithResultOK()
                        }
                        BaseActionEvent.CONNECT_ERROR -> {

                        }
                        BaseActionEvent.CONNECT_TIMEOUT -> {

                        }
                        BaseActionEvent.BAD_NETWORK -> {

                        }
                        BaseActionEvent.PARSE_ERROR -> {

                        }
                        BaseActionEvent.UNKNOWN_ERROR -> {

                        }
                    }
                }
            })
        }
    }

    open fun startLoading() {
        startLoading("")
    }

    open fun startLoading(message: String) {
        if (mProgressLoading == null) {
            mProgressLoading = ProgressLoading(this)
        }
        mProgressLoading?.showLoading()
    }

    fun dismissLoading() {
        mProgressLoading?.hideLoading()
    }

    protected fun finishWithResultOK() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * 设置状态栏透明
     */
    fun setStatusBar() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {//5.0以上
            val decorView = window.decorView
            val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            decorView.systemUiVisibility = option
            //根据上面设置是否对状态栏单独设置颜色
            window.statusBarColor = Color.TRANSPARENT
        } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            val localLayoutParam = window.attributes
            localLayoutParam.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or localLayoutParam.flags)
        }
    }

    fun setStatusBarWithBlack() {
        setStatusBar()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
        dismissLoading()
    }

    protected fun setStatusBarHeight(view: View) {
        view.setHeight(StatusBarHelper.getStatusBarHeight(this))
    }
}