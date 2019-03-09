package com.peaut.vegetables.listener

/**
 * @author peaut
 * @package  com.peaut.vegetables.listener
 * @fileName OnCartEventListener
 * @date on  2019/3/5  11:49
 */
interface OnCartEventListener{
    fun doIncrease(position: Int)
    fun doCheckedProduct(position: Int)
    fun doSupplierChecked(position: Int)
}