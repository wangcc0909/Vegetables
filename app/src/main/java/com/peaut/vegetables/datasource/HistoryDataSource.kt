package com.peaut.vegetables.datasource

import com.peaut.vegetables.datasource.base.IHistoryDataSource
import com.peaut.vegetables.db.SearchHistoryTable
import com.peaut.vegetables.db.ext.database
import com.peaut.vegetables.net.basis.BaseRemoteDataSource
import com.peaut.vegetables.net.basis.callback.RequestCallback
import com.peaut.vegetables.net.basis.model.BaseResponseBody
import com.peaut.vegetables.util.applicationContext
import com.peaut.vegetables.viewmodel.base.BaseViewModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.db.StringParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * @author peaut
 * @package  com.peaut.vegetables.datasource
 * @fileName HistoryDataSource
 * @date on  2019/3/6  17:30
 */
class HistoryDataSource(baseViewModel: BaseViewModel) : BaseRemoteDataSource(baseViewModel), IHistoryDataSource {
    private var disposable: CompositeDisposable = CompositeDisposable()
    override fun insertHistory(key: String) {
        //这里封装出observable
        disposable.add(saveKey(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }

    override fun queryHistory(callback: RequestCallback<List<String>>) {
        execute(getKeys(),callback)
    }

    override fun deleteHistory(key: String) {

    }

    override fun clearHistory() {
        disposable.add(clearHistoryCompletable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }

    private fun saveKey(key: String): Completable {
        return Completable.fromAction {
            applicationContext.database.use {
                insert(SearchHistoryTable.TABLE_NAME, SearchHistoryTable.KEY to key)
            }
        }
    }

    private fun clearHistoryCompletable(): Completable {
        return Completable.fromAction {
            applicationContext.database.use {
                delete(SearchHistoryTable.TABLE_NAME)
            }
        }
    }

    private fun getKeys(): Observable<BaseResponseBody<List<String>>> {
        val data = applicationContext.database.use {
           select(SearchHistoryTable.TABLE_NAME,SearchHistoryTable.KEY).parseList(StringParser)
        }
//        Collections.reverse(data)
        val body = BaseResponseBody<List<String>>()
        body.code = 0
        body.msg = "success"
        body.data = data
        return Observable.just(body)
    }
}