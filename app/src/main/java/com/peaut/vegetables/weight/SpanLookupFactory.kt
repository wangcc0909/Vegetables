package com.peaut.vegetables.weight

import android.support.v7.widget.GridLayoutManager

/**
 * @author peaut
 * @package  com.peaut.vegetables.weight
 * @fileName SpanLookupFactory
 * @date on  2019/2/28  19:49
 */
internal object SpanLookupFactory {

    fun singleSpan(): SpanLookup {
        return object : SpanLookup {
            override val spanCount: Int
                get() = 1

            override fun getSpanIndex(itemPosition: Int): Int {
                return 0
            }

            override fun getSpanSize(itemPosition: Int): Int {
                return 1
            }
        }
    }

    fun gridLayoutSpanLookup(layoutManager: GridLayoutManager): SpanLookup {
        return object : SpanLookup {
            override val spanCount: Int
                get() = layoutManager.spanCount

            override fun getSpanIndex(itemPosition: Int): Int {
                return layoutManager.spanSizeLookup.getSpanIndex(itemPosition, spanCount)
            }

            override fun getSpanSize(itemPosition: Int): Int {
                return layoutManager.spanSizeLookup.getSpanSize(itemPosition)
            }
        }
    }

}