package com.peaut.vegetables.ui.fragment

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import android.view.View
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.peaut.vegetables.R
import com.peaut.vegetables.adapter.CartAdapter
import com.peaut.vegetables.base.MultiItemEntity
import com.peaut.vegetables.listener.OnCartEventListener
import com.peaut.vegetables.model.ProductItem
import com.peaut.vegetables.model.SupplierItem
import com.peaut.vegetables.ui.activity.MessageActivity
import com.peaut.vegetables.ui.activity.PayCenterActivity
import com.peaut.vegetables.util.lineInit
import com.peaut.vegetables.util.startActivity
import com.peaut.vegetables.view.BaseFragment
import kotlinx.android.synthetic.main.fm_car.*

/**
 * @author peaut
 * @package  com.peaut.vegetables.ui.fragment
 * @fileName CarFragment
 * @date on  2019/2/28  10:13
 */
class CarFragment : BaseFragment(), OnCartEventListener {
    //这个界面需要当数据改变时更新数据
    private lateinit var adapter: CartAdapter
    private lateinit var mLRecyclerViewAdapter: LRecyclerViewAdapter
    override fun getLayoutId(): Int = R.layout.fm_car

    override fun initViewModel(): ViewModel? {
        return null
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        setStatusBarHeight(myStatusBar)
        adapter = CartAdapter(requireContext())
        adapter.setOnCartEventListener(this)
        mLRecyclerViewAdapter = LRecyclerViewAdapter(adapter)
        mRecyclerView.lineInit(requireContext(), mLRecyclerViewAdapter)
        val data = mutableListOf<MultiItemEntity>()
        data.add(SupplierItem("张三的包子铺", 1))
        data.add(ProductItem("肉包", 1.0, "https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg", true, 20, 1))
        data.add(ProductItem("肉包", 1.0, "https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg", true, 20, 1))
        data.add(SupplierItem("李四的羊肉涮", 2))
        data.add(ProductItem("肥羊肉", 38.0, "https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg", false, 8, 2))
        adapter.addAll(data)
        mLRecyclerViewAdapter.notifyDataSetChanged()
        mStatusLayoutManager.showContent()
        title.text = "购物车(${getProductCount()})"
    }

    var count = 0
    override fun initListener() {
        super.initListener()
        tv_edit.setOnClickListener {
            if (tv_commit.visibility == View.VISIBLE) {
                tv_commit.visibility = View.GONE
                tv_delete.visibility = View.VISIBLE
                ll_middle_view.visibility = View.GONE
                tv_edit.text = "完成"
            } else {
                tv_commit.visibility = View.VISIBLE
                tv_delete.visibility = View.GONE
                ll_middle_view.visibility = View.VISIBLE
                tv_edit.text = "编辑"
            }
        }

        checkbox.setOnClickListener {
            val data = adapter.getData()
            for (item in data) {
                if (item is SupplierItem) {
                    item.isChecked = checkbox.isChecked
                }
                if (item is ProductItem) {
                    item.isSelected = checkbox.isChecked
                }
            }
            adapter.notifyDataSetChanged()
            mLRecyclerViewAdapter.notifyDataSetChanged()
            //还要计算价格
            calculate()
        }

        tv_delete.setOnClickListener {
            //移除所有选中的item
            removeCheckedItem()
            calculate()
            checkbox.isChecked = adapter.isCheckedAll()
            title.text = "购物车(${getProductCount()})"
//            rl_bottom_container.visibility = View.GONE
//            mStatusLayoutManager.showEmptyLayout()
            //这里用个空布局代替recyclerView
        }
        ib_msg.setOnClickListener {
            requireContext().startActivity<MessageActivity>()
        }
        tv_commit.setOnClickListener {
            //需要携带参数
            requireContext().startActivity<PayCenterActivity>()
        }
    }

    private fun removeCheckedItem() {
        val newData = mutableListOf<MultiItemEntity>()
        for (item in adapter.getData()) {
            if (item is SupplierItem && !item.isChecked) {
                newData.add(item)
            }
            if (item is ProductItem && !item.isSelected) {
                newData.add(item)
            }
        }
        adapter.updateData(newData)
        adapter.notifyDataSetChanged()
        //这里还需要发送网络请求
    }

    private fun calculate() {
        var sum = 0.00
        val data = adapter.getData()
        for (item in data) {
            if (item is ProductItem && item.isSelected) {
                sum += item.count * item.price
            }
        }
        tv_total_money.text = "¥ $sum"
    }

    private fun getProductCount(): Int {
        var count = 0
        for (item in adapter.getData()) {
            if (item is ProductItem) {
                count++
            }
        }
        return count
    }

    //增加 减少商品
    override fun doIncrease(position: Int) {
        //这里需要发送网络请求
        calculate()
    }

    //选择商品
    override fun doCheckedProduct(position: Int) {
        checkbox.isChecked = adapter.isCheckedAll()
        calculate()
    }

    override fun doSupplierChecked(position: Int) {
        checkbox.isChecked = adapter.isCheckedAll()
        calculate()
    }
}