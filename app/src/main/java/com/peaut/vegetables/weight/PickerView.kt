package com.peaut.vegetables.weight

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import com.peaut.vegetables.R
import com.peaut.vegetables.adapter.PickerViewAdapter
import com.peaut.vegetables.listener.OnTextViewClickListener
import com.peaut.vegetables.model.PickerData
import com.peaut.vegetables.util.getWindowHeight
import com.peaut.vegetables.util.getWindowWidth

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
    private lateinit var dialog: SmartDialog
    private lateinit var mRgSelect: RadioGroup
    private lateinit var mTextFirst: RadioButton
    private lateinit var mTextSecond: RadioButton
    private lateinit var mTextThird: RadioButton
    private lateinit var mTextFourth: RadioButton
    private lateinit var mPickerList: RecyclerView
    private lateinit var mIbDismiss: ImageButton
    private var adapter: PickerViewAdapter? = null
    private var listener: OnPickerClicKListener? = null

    constructor(context: Context, pickerData: PickerData) {
        this.context = context
        this.pickerData = pickerData
        initPickerView()
        initView()
    }

    interface OnPickerClicKListener {
        fun onClick(pickerData: String)
    }

    fun setOnPickerClick(l: OnPickerClicKListener) {
        listener = l
    }

    private fun initView() {
        mRgSelect = dialog.getView(R.id.rg_select)
        mTextFirst = dialog.getView(R.id.mTextFirst)
        mTextSecond = dialog.getView(R.id.mTextSecond)
        mTextThird = dialog.getView(R.id.mTextThird)
        mTextFourth = dialog.getView(R.id.mTextFourth)
        mPickerList = dialog.getView(R.id.pickerList)
        mIbDismiss = dialog.getView(R.id.ib_dismiss)
        mPickerList.layoutManager = LinearLayoutManager(context)
        mPickerList.setHasFixedSize(true)
        mTextFirst.setOnClickListener(this)
        mTextSecond.setOnClickListener(this)
        mTextThird.setOnClickListener(this)
        mTextFourth.setOnClickListener(this)
        mIbDismiss.setOnClickListener(this)
    }

    private fun initPickerView() {
        dialog = SmartDialog.builder(context){
            this.resId = R.layout.picker_view
            this.width = context.getWindowWidth()
            this.height = (context.getWindowHeight() * 0.6).toInt()
            this.gravity = Gravity.BOTTOM
            this.animation = SmartDialog.BottomToUpAnimation
            this.themeResId = R.style.PickerDialog
        }
    }

    override fun onClick(v: View?) {
        val id = v?.id
        when (id) {
            R.id.ib_dismiss -> {
                dialog.dismiss()
            }
            R.id.mTextFirst -> {
                pickerData.currIndex = 1
                currData = pickerData.getCurrDatas()
                adapter?.updateData(currData)
            }
            R.id.mTextSecond -> {
                pickerData.currIndex = 2
                pickerData.currText = pickerData.mFirstText
                currData = pickerData.getCurrDatas()
                adapter?.updateData(currData)
            }
            R.id.mTextThird -> {
                pickerData.currIndex = 3
                pickerData.currText = pickerData.mSecondText
                currData = pickerData.getCurrDatas()
                adapter?.updateData(currData)
            }
            R.id.mTextFourth -> {
                pickerData.currIndex = 4
                pickerData.currText = pickerData.mThirdText
                currData = pickerData.getCurrDatas()
                adapter?.updateData(currData)
            }
        }
    }

    fun show() {
        initData()
        dialog.show()
    }

    private fun initData() {
        currData = pickerData.getCurrDatas()
        adapter = PickerViewAdapter(context)
        mPickerList.adapter = adapter
        adapter?.updateData(currData)
        if (pickerData.mFirstText.isNotEmpty()) {
            mTextFirst.text = pickerData.mFirstText
            mRgSelect.check(mTextFirst.id)
            if (pickerData.mSecondText.isNotEmpty()) {
                mTextSecond.text = pickerData.mSecondText
                mRgSelect.check(mTextSecond.id)
                if (pickerData.mThirdText.isNotEmpty()) {
                    mTextThird.text = pickerData.mThirdText
                    mRgSelect.check(mTextThird.id)
                    if (pickerData.mFourthText.isNotEmpty()) {
                        mTextFourth.text = pickerData.mFourthText
                        mRgSelect.check(mTextFourth.id)
                    }
                } else {
                    mTextThird.text = "请选择"
                }
            } else {
                mTextSecond.text = "请选择"
            }
        } else {
            mTextFirst.text = "请选择"
            mRgSelect.check(mTextFirst.id)
        }
        adapter?.setOnItemClickListener(object : OnTextViewClickListener {
            override fun onTextViewClick(position: Int) {
                val currText = currData[position]
                pickerData.clearSelectText(pickerData.currIndex)
                mTextFirst.text = pickerData.mFirstText
                mTextSecond.text = pickerData.mSecondText
                mTextThird.text = pickerData.mThirdText
                when (pickerData.currIndex) {
                    1 -> {
                        pickerData.currIndex = 2
                        pickerData.mFirstText = currText
                        pickerData.currText = currText
                        mRgSelect.check(mTextSecond.id)
                        mTextFirst.text = currText
                        mTextSecond.text = "请选择"
                        //这里处理是否继续点击
                        updateAdapter(pickerData.mSecondData)
                    }
                    2 -> {
                        pickerData.currIndex = 3
                        pickerData.mSecondText = currText
                        pickerData.currText = currText
                        mRgSelect.check(mTextThird.id)
                        mTextSecond.text = currText
                        mTextThird.text = "请选择"
                        updateAdapter(pickerData.mThirdData)
                    }
                    3 -> {
                        pickerData.currIndex = 4
                        pickerData.mThirdText = currText
                        pickerData.currText = currText
                        mRgSelect.check(mTextThird.id)
                        mTextThird.text = currText
//                        mTextFourth.text = "请选择"
                        updateAdapter(pickerData.mFourthData)
                    }
                    4 -> {
                        pickerData.mFourthText = currText
                        pickerData.currText = currText
                        mRgSelect.check(mTextFourth.id)
                        mTextFourth.text = currText
                        //回调
                        listener?.onClick(pickerData.getSelectText())
                    }
                }
            }
        })
    }

    private fun updateAdapter(data: Map<String, List<String>>) {
        if (!data.isEmpty()) {
            val currData = pickerData.getCurrDatas()
            if (currData.isNotEmpty()) {
                this.currData = currData
                adapter?.updateData(currData)
            } else {
                listener?.onClick(pickerData.getSelectText())
                pickerData.currIndex = pickerData.currIndex - 1
                dismiss()
            }
        } else {
            //通过监听器将数据返回
            listener?.onClick(pickerData.getSelectText())
            pickerData.currIndex = pickerData.currIndex - 1
            pickerData.currText = pickerData.mSecondText
            dismiss()
        }
    }

    fun dismiss() {
        dialog.dismiss()
    }
}