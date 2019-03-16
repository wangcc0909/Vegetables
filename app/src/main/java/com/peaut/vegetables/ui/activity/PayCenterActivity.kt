package com.peaut.vegetables.ui.activity

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.peaut.vegetables.R
import com.peaut.vegetables.adapter.PayCenterAdapter
import com.peaut.vegetables.base.MultiItemEntity
import com.peaut.vegetables.model.ProductItem
import com.peaut.vegetables.model.SupplierItem
import com.peaut.vegetables.util.*
import com.peaut.vegetables.view.BaseActivity
import com.peaut.vegetables.weight.BottomDialog
import kotlinx.android.synthetic.main.activity_pay_center.*

class PayCenterActivity : BaseActivity() {
    companion object {
        val ADDRESS_REQUEST_CODE = 1000
    }
    private lateinit var adapter: PayCenterAdapter
    private lateinit var mLRecyclerViewAdapter: LRecyclerViewAdapter
    private lateinit var mCtContainer: ConstraintLayout
    private lateinit var mRlPayFunc: RelativeLayout
    private var mBottomDialog: BottomDialog? = null
    override fun getResId(): Int = R.layout.activity_pay_center

    override fun initView(savedInstanceState: Bundle?) {
        setStatusBarWithBlack()
        setStatusBarHeight(myStatusBar)
        adapter = PayCenterAdapter(this)
        mLRecyclerViewAdapter = LRecyclerViewAdapter(adapter)
        mRecyclerView.lineInit(this, mLRecyclerViewAdapter)
        mRecyclerView.setLoadMoreEnabled(false)
        val headView = inflate(R.layout.pay_center_head_layout, findViewById(android.R.id.content))
        mCtContainer = headView.findViewById(R.id.ct_address)
        mRlPayFunc = headView.findViewById(R.id.rl_pay_func)
        mLRecyclerViewAdapter.addHeaderView(headView)
        val footerView= inflate(R.layout.pay_center_footer_layout,findViewById(android.R.id.content))
        mLRecyclerViewAdapter.addFooterView(footerView)
    }

    override fun initData(savedInstanceState: Bundle?) {
        val data = arrayListOf<MultiItemEntity>()
        data.add(SupplierItem("特步专卖店",1))
        val title = "需要一个很长很长的title,搞不好就是越过了极限位置.就是写的长一点,长一点"
        val icon = "https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg"
        data.add(ProductItem(title,12.3,icon,false,5,1))
        adapter.addAll(data)
        mLRecyclerViewAdapter.notifyDataSetChanged()
    }

    override fun initListener(savedInstanceState: Bundle?) {
        super.initListener(savedInstanceState)
        ib_back.setOnClickListener { onBackPressed() }
        mCtContainer.setOnClickListener { setAddress() }
        mRlPayFunc.setOnClickListener { setPayFunction() }
        tv_commit.setOnClickListener { startActivity<PayFunctionActivity>() }
    }

    private fun setPayFunction() {
        val view = inflate(R.layout.pay_function_layout)
        val ivCancel = view.findViewById<ImageView>(R.id.iv_cancel)
        val tvComplete = view.findViewById<TextView>(R.id.tv_complete)
        mBottomDialog = BottomDialog.builder(this){
            this.view = view
            this.height = (getWindowHeight() * 0.6).toInt()
        }
        mBottomDialog?.show()

        ivCancel.setOnClickListener { mBottomDialog?.dismiss() }
        tvComplete.setOnClickListener {
            //先拿到选择的方式 然后dismiss
            mBottomDialog?.dismiss()
        }
    }

    private fun setAddress() {
        startActivityForResult(intent<AddressListActivity> {  }, ADDRESS_REQUEST_CODE)
    }

    override fun initViewModel(): ViewModel? {
        return null
    }
}
