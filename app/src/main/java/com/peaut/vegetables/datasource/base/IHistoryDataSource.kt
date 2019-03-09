package com.peaut.vegetables.datasource.base

import com.peaut.vegetables.net.basis.callback.RequestCallback

/**
 * @author peaut
 * @package  com.peaut.vegetables.datasource.base
 * @fileName IHistoryDataSource
 * @date on  2019/3/6  17:30
 */
interface IHistoryDataSource {
    fun insertHistory(key: String)

    fun queryHistory(callback: RequestCallback<List<String>>)

    fun deleteHistory(key: String)

    fun clearHistory()
}