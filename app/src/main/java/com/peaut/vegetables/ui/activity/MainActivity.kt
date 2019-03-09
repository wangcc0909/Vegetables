package com.peaut.vegetables.ui.activity

import android.arch.lifecycle.ViewModel
import android.os.Build
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast
import com.peaut.vegetables.R
import com.peaut.vegetables.ui.fragment.CarFragment
import com.peaut.vegetables.ui.fragment.HomeFragment
import com.peaut.vegetables.ui.fragment.MeFragment
import com.peaut.vegetables.util.BottomNavigationViewHelper
import com.peaut.vegetables.view.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity() {
    private var clickTime: Long = 0
    private var mFragments: Stack<Fragment> = Stack()

    private val STATUSBAR_POSITION = 2

    override fun getResId(): Int = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        setStatusBar()
        initFragment()
        BottomNavigationViewHelper.disableShiftMode(navigationView)
        navigationView.setOnNavigationItemSelectedListener(onNavigationViewListener)
        navigationView.selectedItemId = R.id.navigation_home
        //权限请求
    }

    private fun initFragment() {
        mFragments.add(HomeFragment())
        mFragments.add(CarFragment())
        mFragments.add(MeFragment())
        val bt = supportFragmentManager.beginTransaction()
        mFragments.forEach {
            bt.add(R.id.rootPlay,it)
        }
        bt.commit()
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun initListener(savedInstanceState: Bundle?) {
        super.initListener(savedInstanceState)

    }

    private val onNavigationViewListener = BottomNavigationView.OnNavigationItemSelectedListener {
        val index = getIndexById(it.itemId)
        if (index != -1){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                window.decorView.systemUiVisibility = if (index == STATUSBAR_POSITION){
                    //设置状态栏未白色字体
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                }else {
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
            }
            val bt = supportFragmentManager.beginTransaction()
            mFragments.forEach { fragment ->
                bt.hide(fragment)
            }
            bt.show(mFragments[index])
            bt.commit()
//            supportFragmentManager.beginTransaction().replace(R.id.rootPlay,mFragments[index]).commit()
            return@OnNavigationItemSelectedListener true
        }
        false
    }

    override fun initViewModel(): ViewModel? {
        return null
    }

    private fun getIndexById(itemId: Int): Int {
        return when (itemId) {
            R.id.navigation_home -> 0
            R.id.navigation_cart -> 1
            R.id.navigation_me -> 2
            else -> -1
        }
    }

    override fun onBackPressed() {
        exit()
    }

    private fun exit(){
        if ((System.currentTimeMillis() - clickTime) > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show()
            clickTime = System.currentTimeMillis()
        } else {
            this.finish()
        }
    }
}
