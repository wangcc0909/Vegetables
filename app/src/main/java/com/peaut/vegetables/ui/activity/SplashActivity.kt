package com.peaut.vegetables.ui.activity

import android.arch.lifecycle.ViewModel
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.support.annotation.RequiresApi
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.peaut.vegetables.R
import com.peaut.vegetables.util.startActivity
import com.peaut.vegetables.view.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {
    private val TIME_COUNTDOEN: Long = 4 * 1000
    private val DURATION: Long = 3000

    override fun getResId(): Int = R.layout.activity_splash
    /*override fun getResId(): Int {
        hideBottomUIMenu()
        return -1
    }*/

    override fun prepareLayout() {
        super.prepareLayout()
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        hideBottomUIMenu()
    }

    override fun initView(savedInstanceState: Bundle?) {
//        initAnimation()
    }

    private fun initAnimation() {
        val alphaAnim = AlphaAnimation(0.5f, 1.0f)
        alphaAnim.duration = DURATION
        iv_splash.startAnimation(alphaAnim)
        alphaAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                startActivity<MainActivity>()
            }

            override fun onAnimationStart(animation: Animation?) {

            }
        })
    }

    override fun initData(savedInstanceState: Bundle?) {
        object : CountDownTimer(1000, TIME_COUNTDOEN) {
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onFinish() {
                startActivity<MainActivity>()
                finish()
            }

            override fun onTick(p0: Long) {
            }

        }.start()
    }

    override fun initViewModel(): ViewModel? {
        return null
    }

    /**
     * 隐藏底部虚拟键并全屏
     */
    private fun hideBottomUIMenu() {
        if (Build.VERSION.SDK_INT in 12..18) {
            val view = this.window.decorView
            view.systemUiVisibility = View.GONE
        } else if (Build.VERSION.SDK_INT >= 19) {
            val decorView = window.decorView
            val uiOptions = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

            decorView.systemUiVisibility = uiOptions
        }
    }
}
