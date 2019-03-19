package com.peaut.vegetables.net.basis

import com.peaut.vegetables.net.basis.callback.RequestCallback
import com.peaut.vegetables.net.basis.callback.RequestMultiplyCallback
import com.peaut.vegetables.net.basis.model.BaseResponseBody
import com.peaut.vegetables.viewmodel.base.BaseViewModel
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * @author peaut
 * @package  com.peaut.httpmodule.http.basis
 * @fileName BaseRemoteDataSource
 * @date on  2019/2/15  15:58
 */
abstract class BaseRemoteDataSource {
    private var baseViewModel: BaseViewModel
    var compositeDisposable: CompositeDisposable

    constructor(baseViewModel: BaseViewModel) {
        this.baseViewModel = baseViewModel
        compositeDisposable = CompositeDisposable()
    }

    open fun <T> getService(clz: Class<T>): T {
        return RetrofitManagement.instance.getService(clz)
    }

    open fun <T> getService(clz: Class<T>, host: String): T {
        return RetrofitManagement.instance.getService(clz, host)
    }

    open fun <T> applySchedules(): ObservableTransformer<BaseResponseBody<T>, T> {
        return RetrofitManagement.instance.applySchedulers()
    }

    open fun <T> execute(observable: Observable<BaseResponseBody<T>>, callback: RequestCallback<T>) {
        execute(observable, BaseSubscriber(baseViewModel, callback), true)
    }

    open fun <T> execute(observable: Observable<BaseResponseBody<T>>, callback: RequestMultiplyCallback<T>) {
        execute(observable, BaseSubscriber(baseViewModel, callback), true)
    }

    open fun <T> executeWithoutDismiss(observable: Observable<BaseResponseBody<T>>, observer: Observer<T>) {
        execute(observable, observer, false)
    }

    open fun <T> execute(observable: Observable<BaseResponseBody<T>>, observer: Observer<T>, isDismiss: Boolean) {
        val disposable = observable
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(applySchedules())
                .compose(if (isDismiss) loadingTransformer() else loadingTransformerWithoutDismiss<T>())
                .subscribeWith(observer) as Disposable
        addDisposable(disposable)
    }

    open fun <T> execute(observable: Flowable<T>, subscriber: FlowableSubscriber<T>){
        observable
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(subscriber)
    }

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    open fun dispose() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    open fun startLoading() {
        baseViewModel.startLoading()
    }

    open fun dismissLoading() {
        baseViewModel.dismissLoading()
    }

    private fun <T> loadingTransformer(): ObservableTransformer<T, T> {
        return ObservableTransformer{ observable ->
            observable
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { startLoading() }
                    .doFinally { this.dismissLoading() }
        }
    }

    private fun <T> loadingTransformerWithoutDismiss(): ObservableTransformer<T, T> {
        return ObservableTransformer{ observable ->
            observable
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { startLoading() }
        }
    }
}