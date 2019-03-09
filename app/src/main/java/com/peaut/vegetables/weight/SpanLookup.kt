package com.peaut.vegetables.weight

/**
 * @author peaut
 * @package  com.peaut.vegetables.weight
 * @fileName SpanLookup
 * @date on  2019/2/28  19:48
 */
interface SpanLookup {

    /**
     * @return number of spans in a row
     */
    val spanCount: Int

    /**
     * @param itemPosition
     * @return start span for the item at the given adapter position
     */
    fun getSpanIndex(itemPosition: Int): Int

    /**
     * @param itemPosition
     * @return number of spans the item at the given adapter position occupies
     */
    fun getSpanSize(itemPosition: Int): Int

}