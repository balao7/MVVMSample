package com.android.mvvm.sample.app.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

internal interface BaseRxView {

    val compositeDisposable: CompositeDisposable

    fun clearDisposables() = compositeDisposable.apply {
        if (this.size() > 0 && !this.isDisposed) {
            this.clear()
        }
    }

    fun Disposable.compose() = compositeDisposable.add(this)

}