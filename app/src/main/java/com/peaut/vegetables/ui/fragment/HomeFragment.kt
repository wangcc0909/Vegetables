package com.peaut.vegetables.ui.fragment

import android.Manifest
import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.google.zxing.activity.CaptureActivity
import com.google.zxing.util.Constant
import com.peaut.vegetables.R
import com.peaut.vegetables.adapter.HomeAdapter
import com.peaut.vegetables.model.HomeItem
import com.peaut.vegetables.qrhandle.QRParserImp
import com.peaut.vegetables.ui.activity.CategoryVegetablesActivity
import com.peaut.vegetables.ui.activity.MessageActivity
import com.peaut.vegetables.ui.activity.SearchActivity
import com.peaut.vegetables.util.*
import com.peaut.vegetables.view.BaseFragment
import com.youth.banner.Banner
import kotlinx.android.synthetic.main.fm_home.*

/**
 * @author peaut
 * @package  com.peaut.vegetables.ui.fragment
 * @fileName HomeFragment
 * @date on  2019/2/25  18:01
 */
class HomeFragment: BaseFragment(){
    private lateinit var mHeadBanner: View
    private lateinit var mHomeCategory1: LinearLayout
    private lateinit var mHomeCategory2: LinearLayout
    private lateinit var mHomeCategory3: LinearLayout
    private lateinit var ibMsg: ImageButton
    private lateinit var ibScan: ImageButton
    private lateinit var mTvRcSearch: TextView
    override fun getLayoutId(): Int = R.layout.fm_home

    override fun initViewModel(): ViewModel? {
        return null
    }

    override fun initView() {
        setStatusBarHeight(myStatusBar)
        val adapter = HomeAdapter(requireContext())
        val mLRecyclerViewAdapter = LRecyclerViewAdapter(adapter)
        mRecyclerView.listInit(requireContext(),mLRecyclerViewAdapter)
        val headBanner = requireContext().inflate(R.layout.home_head_banner)
        mTvRcSearch = headBanner.findViewById(R.id.tv_rc_search)
        val mBanner = headBanner.findViewById<Banner>(R.id.mBanner)
        ibMsg = headBanner.findViewById(R.id.ib_msg)
        ibScan = headBanner.findViewById(R.id.ib_scan)
        mHomeCategory1 = headBanner.findViewById(R.id.home_linearLayout1)
        mHomeCategory2 = headBanner.findViewById(R.id.home_linearLayout2)
        mHomeCategory3 = headBanner.findViewById(R.id.home_linearLayout3)
        mLRecyclerViewAdapter.addHeaderView(headBanner)
        val mData = ArrayList<HomeItem>()
        mData.add(HomeItem("xxx果蔬供应批发商","https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg",4.7f,"广州天河区","1.8km",1))
        mData.add(HomeItem("xxx果蔬供应批发商","https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg",4.7f,"广州天河区","1.8km",1))
        mData.add(HomeItem("xxx果蔬供应批发商","https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg",4.7f,"广州天河区","1.8km",1))
        mData.add(HomeItem("xxx果蔬供应批发商","https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg",4.7f,"广州天河区","1.8km",1))
        mData.add(HomeItem("xxx果蔬供应批发商","https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg",4.7f,"广州天河区","1.8km",1))
        mData.add(HomeItem("xxx果蔬供应批发商","https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg",4.7f,"广州天河区","1.8km",1))
        adapter.addAll(mData)
        mLRecyclerViewAdapter.notifyDataSetChanged()
        mStatusLayoutManager.showContent()

        val bannerImages = arrayListOf<String>()
        bannerImages.add("https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg")
        bannerImages.add("https://ws1.sinaimg.cn/large/0065oQSqgy1fwyf0wr8hhj30ie0nhq6p.jpg")
        bannerImages.add("https://ws1.sinaimg.cn/large/0065oQSqgy1fwgzx8n1syj30sg15h7ew.jpg")
        bannerImages.add("https://ws1.sinaimg.cn/large/0065oQSqly1g0ajj4h6ndj30sg11xdmj.jpg")
        mBanner.player(null,bannerImages)
    }

    override fun initListener() {
        super.initListener()
        mHomeCategory1.setOnClickListener {
            requireContext().startActivity<CategoryVegetablesActivity>()
        }
        mTvRcSearch.setOnClickListener {
//            requireContext().startActivity<SearchActivity>()
            requireActivity().startActivityWithAnimation<SearchActivity>(mTvRcSearch,getString(R.string.search_hint))
        }
        ibMsg.setOnClickListener {
            requireActivity().startActivity<MessageActivity>()
        }
        ibScan.setOnClickListener { startQrCode() }
    }

    private fun startQrCode() {
        if (ContextCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.CAMERA)) {
                Toast.makeText(requireContext(), "请至权限中心打开本应用的相机访问权限", Toast.LENGTH_LONG).show()
            }
            // 申请权限
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CAMERA), Constant.REQ_PERM_CAMERA)
            return
        }
        val intent = Intent(requireContext(), CaptureActivity::class.java)
        startActivityForResult(intent, Constant.REQ_QR_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            Constant.REQ_PERM_CAMERA -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startQrCode()
                } else {
                    Toast.makeText(requireContext(), "请至权限中心打开本应用的相机访问权限", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constant.REQ_QR_CODE && resultCode == Activity.RESULT_OK) {
            val bundle = data?.extras
            val result = bundle?.getString(Constant.INTENT_EXTRA_KEY_QR_SCAN)
            QRParserImp.getInstance(requireContext()).parse(result?:"未能扫描到数据")
        }
    }
}