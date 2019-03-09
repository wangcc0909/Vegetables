package com.peaut.vegetables.ui.activity

import android.arch.lifecycle.ViewModel
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import com.peaut.vegetables.R
import com.peaut.vegetables.ui.fragment.GoodsAnswerFragment
import com.peaut.vegetables.ui.fragment.GoodsDesFragment
import com.peaut.vegetables.util.GlideImageLoader
import com.peaut.vegetables.view.BaseActivity
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.goods_detail_bottom_layout.*
import kotlinx.android.synthetic.main.goods_detail_title_layout.*
import kotlinx.android.synthetic.main.goods_detail_top_layout.*

class ProductDetailActivity : BaseActivity() {
    var currentFragment: Fragment? = null
    private lateinit var mGoodsDesFragment: GoodsDesFragment
    private lateinit var mGoodsAnswerFragment: GoodsAnswerFragment
    override fun getResId(): Int = R.layout.activity_product_detail

    override fun initView(savedInstanceState: Bundle?) {
        setStatusBarWithBlack()
        setStatusBarHeight(myStatusBar)
        setStatusBarHeight(bottom_line_one)
        initFragments()
    }

    private fun initFragments() {
        tv_product_des.isSelected = true
        mGoodsDesFragment = GoodsDesFragment()
        mGoodsAnswerFragment = GoodsAnswerFragment()
        if (!mGoodsDesFragment.isAdded) {
            currentFragment = mGoodsDesFragment
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.fl_container, mGoodsDesFragment)
            transaction.commitAllowingStateLoss()
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        val bannerImages = arrayListOf<String>()
        bannerImages.add("https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg")
        bannerImages.add("https://ws1.sinaimg.cn/large/0065oQSqgy1fwyf0wr8hhj30ie0nhq6p.jpg")
        bannerImages.add("https://ws1.sinaimg.cn/large/0065oQSqgy1fwgzx8n1syj30sg15h7ew.jpg")
        bannerImages.add("https://ws1.sinaimg.cn/large/0065oQSqly1g0ajj4h6ndj30sg11xdmj.jpg")
        mBanner.setImageLoader(GlideImageLoader())
        mBanner.setImages(bannerImages)
        mBanner.setIndicatorGravity(BannerConfig.CENTER)
        mBanner.setBannerAnimation(Transformer.Default)
        mBanner.start()

    }

    override fun initListener(savedInstanceState: Bundle?) {
        super.initListener(savedInstanceState)
        goods_title_root.setBackgroundColor(Color.argb(0, 255, 255, 255))
        scrollView.setOnScrollViewListener { scrollX, scrollY, oldx, oldY ->
            var distanceScrollY = mBanner.height - goods_title_root.height
            if (scrollY <= 0) { //未滑动  设置全透明
                goods_title_root.setBackgroundColor(Color.argb(0, 255, 255, 255))
                iv_back.setImageResource(R.drawable.ic_back_arrow_white)
                iv_share.setImageResource(R.drawable.ic_share_white)
            } else if (scrollY in 1..distanceScrollY) {
                val scale = scrollY.toFloat() / distanceScrollY
                val alpha = 255 * scale
                goods_title_root.setBackgroundColor(Color.argb(alpha.toInt(), 255, 255, 255))
                if (scrollY >= goods_title_root.height) {  //这里没有才用比例值
                    iv_back.setImageResource(R.drawable.ic_back_arrow_black)
                    iv_share.setImageResource(R.drawable.ic_share_black)
                } else {
                    iv_back.setImageResource(R.drawable.ic_back_arrow_white)
                    iv_share.setImageResource(R.drawable.ic_share_white)
                }
            } else { //超过高度
                goods_title_root.setBackgroundColor(Color.argb(255, 255, 255, 255))
                iv_back.setImageResource(R.drawable.ic_back_arrow_black)
                iv_share.setImageResource(R.drawable.ic_share_black)
            }
        }
        iv_back.setOnClickListener {
            onBackPressed()
        }
        tv_product_des.setOnClickListener { checkGoodsDes() }
        tv_answer.setOnClickListener { checkGoodsAnswer() }
    }

    private fun checkGoodsAnswer() {
        tv_answer.isSelected = true
        tv_product_des.isSelected = false
        bottom_line_two.text = "小考拉答疑"
        switchFragment(currentFragment!!, mGoodsAnswerFragment)
    }

    private fun checkGoodsDes() {
        tv_product_des.isSelected = true
        tv_answer.isSelected = false
        bottom_line_two.text = "图文详情"
        switchFragment(currentFragment!!, mGoodsDesFragment)
    }

    private fun switchFragment(from: Fragment, to: Fragment) {
        if (currentFragment != to) {
            currentFragment = to
            val transaction = supportFragmentManager.beginTransaction()
            if (!to.isAdded) {
                transaction.hide(from).add(R.id.fl_container, to).commitAllowingStateLoss()
            } else {
                transaction.hide(from).show(to).commitAllowingStateLoss()
            }
        }
    }

    override fun initViewModel(): ViewModel? {
        return null
    }
}
