package com.peaut.vegetables.weight

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import com.peaut.vegetables.R
import com.peaut.vegetables.adapter.PickerViewAdapter
import com.peaut.vegetables.model.PickerData
import com.peaut.vegetables.util.getWindowHeight

/**
 * @author peaut
 * @package  com.peaut.vegetables.weight
 * @fileName PickerView
 * @date on  2019/3/18  16:45
 */
class PickerView : View.OnClickListener {

    private var context: Context
    private var pickerData: PickerData
    private var currData: List<String> = arrayListOf()
    private lateinit var dialog: BottomDialog
    private lateinit var contentView: View
    private lateinit var mRgSelect: RadioGroup
    private lateinit var mTextFirst: RadioButton
    private lateinit var mTextSecond: RadioButton
    private lateinit var mTextThird: RadioButton
    private lateinit var mTextFourth: RadioButton
    private lateinit var mPickerList: RecyclerView
    private lateinit var mIbDismiss: ImageButton
    private var index = 1
    private var adapter: PickerViewAdapter? = null

    constructor(context: Context, pickerData: PickerData) {
        this.context = context
        this.pickerData = pickerData
        initPickerView()
        initView()
    }

    private fun initView() {
        mRgSelect = contentView.findViewById(R.id.rg_select)
        mTextFirst = contentView.findViewById(R.id.mTextFirst)
        mTextSecond = contentView.findViewById(R.id.mTextSecond)
        mTextThird = contentView.findViewById(R.id.mTextThird)
        mTextFourth = contentView.findViewById(R.id.mTextFourth)
        mPickerList = contentView.findViewById(R.id.pickerList)
        mIbDismiss = contentView.findViewById(R.id.ib_dismiss)
        mPickerList.layoutManager = LinearLayoutManager(context)
        mPickerList.setHasFixedSize(true)
        mTextFirst.setOnClickListener(this)
        mTextSecond.setOnClickListener(this)
        mTextThird.setOnClickListener(this)
        mTextFourth.setOnClickListener(this)
        mIbDismiss.setOnClickListener(this)
    }

    private fun initPickerView() {
        val inflate = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        contentView = inflate.inflate(R.layout.picker_view, null)
        dialog = BottomDialog.builder(context) {
            this.view = contentView
            this.height = (context.getWindowHeight() * 0.6).toInt()
            this.themeResId = R.style.PickerDialog
        }
        dialog.getDialog()?.setOnDismissListener {

        }
    }

    override fun onClick(v: View?) {
        val id = v?.id
        when (id) {
            R.id.ib_dismiss -> {
                dialog.dismiss()
            }
            R.id.mTextFirst -> {
            }
            R.id.mTextSecond -> {
            }
            R.id.mTextThird -> {
            }
            R.id.mTextFourth -> {
            }
        }
    }

    fun show() {
        initData()
        dialog.show()
    }

    private fun initData() {
        currData = pickerData.getCurrDatas(index, "")
        adapter = PickerViewAdapter(context)
        mPickerList.adapter = adapter
        Log.e("数据", currData.toString())
        adapter?.updateData(currData)

    }

    fun dismiss() {
        dialog.dismiss()
    }
}