package com.peaut.vegetables.repo

import android.arch.lifecycle.MutableLiveData
import com.peaut.vegetables.datasource.base.IHistoryDataSource
import com.peaut.vegetables.net.basis.BaseRepo
import com.peaut.vegetables.net.basis.callback.RequestCallback

/**
 * @author peaut
 * @package  com.peaut.vegetables.repo
 * @fileName SearchHistoryRepo
 * @date on  2019/3/6  17:27
 */
class SearchHistoryRepo(localDataSource: IHistoryDataSource) : BaseRepo<IHistoryDataSource>(localDataSource) {
    fun queryHistory(): MutableLiveData<List<String>> {
        val historyLiveData = MutableLiveData<List<String>>()
        remoteDataSource.queryHistory(object : RequestCallback<List<String>> {
            override fun onSuccess(t: List<String>) {
                historyLiveData.value = t
            }
        })
        return historyLiveData
    }

    fun deleteHistory(key: String) {
        remoteDataSource.deleteHistory(key)
    }

    fun insertHistory(key: String){
        remoteDataSource.insertHistory(key)
    }

    fun clearHistory(){
        remoteDataSource.clearHistory()
    }
}