package com.peaut.vegetables.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.annotation.NonNull
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.github.jdsjlzx.ItemDecoration.DividerDecoration
import com.github.jdsjlzx.recyclerview.LRecyclerView
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.peaut.vegetables.App
import com.peaut.vegetables.R
import com.peaut.vegetables.weight.SpacesItemDecoration
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import java.util.*

/**
 * @author peaut
 * @package  com.peaut.vegetables.util
 * @fileName Extension
 * @date on  2019/2/22  17:02
 */

val applicationContext = App.instance

inline fun <reified T : Activity> Context?.startActivity() = this?.startActivity(Intent(this, T::class.java))

/**
 * Create an intent for [T] and apply a lambda on it
 */
inline fun <reified T : Any> Context.intent(body: Intent.() -> Unit): Intent {
    val intent = Intent(this, T::class.java)
    intent.body()
    return intent
}

fun LRecyclerView.gridInit(context: Context, adapter: LRecyclerViewAdapter, span: Int = 2){
    val layoutManager = GridLayoutManager(context, span)
    this.layoutManager = layoutManager
    this.adapter = adapter
    this.setHasFixedSize(true)
//    this.setLoadMoreEnabled(false)
    this.setPullRefreshEnabled(false)
    val spacing = resources.getDimensionPixelSize(R.dimen.dp_4)
    this.addItemDecoration(SpacesItemDecoration.newInstance(
            spacing, spacing, span, ContextCompat.getColor(context,R.color.color_f4)))
}

//不带下拉刷新
fun LRecyclerView.listInit(context: Context, adapter: LRecyclerViewAdapter) {
    val manager = LinearLayoutManager(context)
    this.setHasFixedSize(true)
    this.layoutManager = manager
    this.adapter = adapter
    this.setPullRefreshEnabled(false)
    val divider = DividerDecoration
            .Builder(context)
            .setHeight(R.dimen.dp_4)
            .setColorResource(R.color.color_normal_bg)
            .build()
    this.addItemDecoration(divider)
}

fun LRecyclerView.lineInit(context: Context, adapter: LRecyclerViewAdapter) {
    val manager = LinearLayoutManager(context)
    this.setHasFixedSize(true)
    this.layoutManager = manager
    this.adapter = adapter
    this.setPullRefreshEnabled(false)
}

var BANNER_TIME = 3 * 1000

fun Banner.player(titles: List<String>?, bannerImages: List<String>?) {
    val isNotEmptyImages = bannerImages?.isNotEmpty() ?: false
    if (isNotEmptyImages) {
        if (null != titles) {
            this.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
            this.setBannerTitles(titles)
        } else {
            this.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
        }
    }
    this.setImageLoader(GlideImageLoader())
    this.setImages(bannerImages)
    this.setDelayTime(BANNER_TIME)
    this.isAutoPlay(true)
    this.setIndicatorGravity(BannerConfig.CENTER)
    this.setBannerAnimation(Transformer.Default)
    this.start()
}

fun Int.toPx(context: Context): Int {
    val density = context.resources.displayMetrics.density
    return (this * density).toInt()
}

fun View.setWidth(value: Int) {
    val lp = layoutParams
    lp?.let {
        lp.width = value
        layoutParams = lp
    }
}

fun View.setHeight(value: Int) {
    val lp = layoutParams
    lp?.let {
        lp.height = value
        layoutParams = lp
    }
}

fun Context.inflate(layoutId: Int, root: ViewGroup? = null): View {
    return LayoutInflater.from(this).inflate(layoutId, root, false)
}

fun ImageView.loadFromUrl(imageUrl: String) {
    GlideApp.with(this).load(imageUrl).centerCrop().into(this)
}

/**
 * Extension method to startActivity with Animation for Context.
 * 单个共享动画
 */
inline fun <reified T : Activity> Activity.startActivityWithAnimation(@NonNull sharedElement: View, @NonNull sharedElementName: String) {
    val intent = Intent(this, T::class.java)
    val bundle = ActivityOptionsCompat
            .makeSceneTransitionAnimation(this, sharedElement, sharedElementName).toBundle()
    ContextCompat.startActivity(this, intent, bundle)
}

/**
 * 关闭软键盘
 */
fun Activity.hideSoftKeyboard() {
    if (currentFocus != null) {
        val inputMethodManager = getSystemService(Context
                .INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }
}

/**
 * 延时打开软键盘
 */
fun View.showKeyboard() {
    val timer = Timer()
    timer.schedule(object : TimerTask() {
        override fun run() {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(this@showKeyboard, InputMethodManager.SHOW_FORCED)
        }

    }, 300L)
}