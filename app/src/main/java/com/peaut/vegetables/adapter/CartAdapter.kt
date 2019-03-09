package com.peaut.vegetables.adapter

import android.content.Context
import android.util.Log
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.peaut.vegetables.R
import com.peaut.vegetables.base.BaseMultiAdapter
import com.peaut.vegetables.base.BaseViewHolder
import com.peaut.vegetables.base.MultiItemEntity
import com.peaut.vegetables.common.Constant
import com.peaut.vegetables.listener.OnCartEventListener
import com.peaut.vegetables.model.ProductItem
import com.peaut.vegetables.model.SupplierItem
import com.peaut.vegetables.util.loadFromUrl
import com.peaut.vegetables.weight.AmountView

/**
 * @author peaut
 * @package  com.peaut.vegetables.adapter
 * @fileName CartAdapter
 * @date on  2019/3/4  16:20
 */
class CartAdapter(context: Context) : BaseMultiAdapter<MultiItemEntity>(context) {
    private var mCartEventListener: OnCartEventListener? = null

    init {
        addItemType(Constant.ITEM_SUPPLIER_TYPE, R.layout.item_car_supplier_layout)
        addItemType(Constant.ITEM_PRODUCT_TYPE, R.layout.item_car_product_layout)
    }

    fun setOnCartEventListener(l: OnCartEventListener) {
        this.mCartEventListener = l
    }

    override fun onBindItemView(holder: BaseViewHolder, position: Int) {
        val itemType = getData()[position].getItemType()
        if (itemType == Constant.ITEM_SUPPLIER_TYPE) {
            bindSupplierItem(holder, position)
        } else {
            bindProductItem(holder, position)
        }
    }

    private fun bindProductItem(holder: BaseViewHolder, position: Int) {
        val cbProduct = holder.getView<CheckBox>(R.id.cb_product) //默认未选中
        val icIcon = holder.getView<ImageView>(R.id.iv_icon)
        val tvExtent = holder.getView<TextView>(R.id.tv_extent)
        val tvTitle = holder.getView<TextView>(R.id.tv_title)
        val tvPrice = holder.getView<TextView>(R.id.tv_price)
        val avCount = holder.getView<AmountView>(R.id.av_count)
        val data = getData()[position] as ProductItem
        cbProduct.isChecked = data.isSelected
        icIcon.loadFromUrl(data.icon)
        if (data.isExtent) {
            tvExtent.text = "特价"
        } else {
            tvExtent.text = "饥饿营销"
        }
        tvTitle.text = data.title
        tvPrice.text = data.price.toString()
        avCount.setAmountNum(data.count, data.count + 20, 1)
        cbProduct.setOnClickListener {
            data.isSelected = cbProduct.isChecked
            //判断是否是所有该supplierid下的product都已经选择了  有两种情况
            //如果是true 才需要判断 false  直接设置为false
            if (cbProduct.isChecked) {
                //根据 data下的supplierid 找到对应的supplier
                if (isCheckedSupplier(data.supplierId)) {
                    forDoAction(data.supplierId) {
                        it.isChecked = cbProduct.isChecked
                    }
                } else {
                    forDoAction(data.supplierId) {
                        it.isChecked = !cbProduct.isChecked
                    }
                }
            } else {
                forDoAction(data.supplierId) {
                    it.isChecked = cbProduct.isChecked
                }
            }
            delayNotifyItemChanged()
            mCartEventListener?.doCheckedProduct(position)
        }
        //这里要先做checkbox才会生效
        avCount.setOnAmountChangeListener { isAdd, isMaxOrMin, amount ->
            if (isMaxOrMin) {
                return@setOnAmountChangeListener
            }
            data.count = amount
            notifyItemChanged(position)
            mCartEventListener?.doIncrease(position)
        }
    }

    private fun bindSupplierItem(holder: BaseViewHolder, position: Int) {
        val tvSupplier = holder.getView<TextView>(R.id.tv_supplier)
        val cbSupplier = holder.getView<CheckBox>(R.id.cb_supplier)
        val data = getData()[position] as SupplierItem
        cbSupplier.isChecked = data.isChecked
        tvSupplier.text = data.name
        cbSupplier.setOnClickListener {
            data.isChecked = cbSupplier.isChecked
            //该店铺下所有的商品全选
            for (item in getData()) {
                if (item is ProductItem && item.supplierId == data.supplierId) {
                    item.isSelected = cbSupplier.isChecked
                }
            }
            delayNotifyItemChanged()
            mCartEventListener?.doSupplierChecked(position)
        }
    }

    fun isCheckedAll(): Boolean {
        var isCheckedAll = true
        if (getData().isEmpty()){
            isCheckedAll = false
            return isCheckedAll
        }
        //只判断所有店铺就能知道是否全选
        for (item in getData()) {
            if (item is SupplierItem) {
                if (!item.isChecked) {
                    isCheckedAll = false
                    break
                }
            }
        }
        return isCheckedAll
    }

    private fun isCheckedSupplier(supplierId: Int): Boolean {
        var isSupplierChecked = true
        //先判断是否都是全选了 根据supplierId
        for (item in getData()) {
            if (item is ProductItem && item.supplierId == supplierId) {
                Log.e("e",item.isSelected.toString())
                if (!item.isSelected) {
                    isSupplierChecked = false
                    break
                }
            }
        }
        return isSupplierChecked
    }

    private fun forDoAction(supplierId: Int, action: (item: SupplierItem) -> Unit) {
        for (item in getData()) {
            if (item is SupplierItem && item.supplierId == supplierId) {
                action(item)
                break
            }
        }
    }
}