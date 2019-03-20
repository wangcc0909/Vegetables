package com.peaut.vegetables.listener

import com.peaut.vegetables.db.model.Address

/**
 * @author peaut
 * @package  com.peaut.vegetables.listener
 * @fileName OnAddressItemListener
 * @date on  2019/3/20  15:31
 */
interface OnAddressItemListener {
    fun onAddressEdit(address: Address)
}