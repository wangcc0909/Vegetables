package com.peaut.vegetables.model

/**
 * @author peaut
 * @package  com.peaut.vegetables.model
 * @fileName PickerData
 * @date on  2019/3/18  19:38
 */
class PickerData {
    var mFirstData: List<String> = arrayListOf()
    var mSecondData: Map<String,List<String>> = hashMapOf()
    var mThirdData: Map<String,List<String>> = hashMapOf()
    var mFourthData: Map<String,List<String>> = hashMapOf()
    var mFirstText = ""
    var mSecondText = ""
    var mThirdText = ""
    var mFourthText = ""
    var mPickerTitle = ""

    fun getCurrDatas(index: Int,currText: String): List<String> {
        var curr: List<String> = arrayListOf()
        when(index){
            1 -> curr = mFirstData
            2 -> curr = mSecondData.getValue(currText)
            3 -> curr = mThirdData.getValue(currText)
            4 -> curr = mFourthData.getValue(currText)
        }
        return curr
    }

    fun setInitSelectText(firstText: String){
        this.mFirstText = firstText
    }

    fun setInitSelectText(firstText: String,secondText: String){
        this.mFirstText = firstText
        this.mSecondText = secondText
    }

    fun setInitSelectText(firstText: String,secondText: String,thirdText:String){
        this.mFirstText = firstText
        this.mSecondText = secondText
        this.mThirdText = thirdText
    }

    fun setInitSelectText(firstText: String,secondText: String,thirdText:String,fourthText: String){
        this.mFirstText = firstText
        this.mSecondText = secondText
        this.mThirdText = thirdText
        this.mFourthText = fourthText
    }

    fun clearSelectText(index: Int) {
        when(index) {
            1 -> {
                mSecondText = ""
                mThirdText = ""
                mFourthText = ""
            }
            2 -> {
                mThirdText = ""
                mFourthText = ""
            }
            3 -> {
                mFourthText = ""
            }
        }
    }
}