package com.peaut.vegetables.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.peaut.vegetables.datasource.HistoryDataSource
import com.peaut.vegetables.repo.SearchHistoryRepo
import com.peaut.vegetables.viewmodel.base.BaseViewModel

/**
 * @author peaut
 * @package  com.peaut.vegetables.viewmodel
 * @fileName SearchHistoryViewModel
 * @date on  2019/3/6  17:22
 */
class SearchHistoryViewModel : BaseViewModel() {
    private val historyLiveData: MutableLiveData<List<String>> = MutableLiveData()

    private val historyRepo = SearchHistoryRepo(HistoryDataSource(this))

    fun queryHistory() {
        historyRepo.queryHistory().observe(lifecycleOwner!!, Observer {
            historyLiveData.value = it
        })
    }

    fun insertHistory(key: String){
        historyRepo.insertHistory(key)
        queryHistory()
    }

    fun deleteHistory(key: String){
        historyRepo.deleteHistory(key)
        queryHistory()
    }

    fun clearHistory(){
        historyRepo.clearHistory()
        queryHistory()
    }

    fun getHistoryData() = historyLiveData
}