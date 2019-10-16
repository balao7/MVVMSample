package com.android.mvvm.sample.app.base

import androidx.annotation.NonNull
import androidx.databinding.BaseObservable
import androidx.lifecycle.ViewModel
import javax.inject.Inject

open class BaseViewModel<M : BaseObservable> @Inject constructor(@NonNull private val modelClass: Class<M>) :
    ViewModel(), BaseRxView {

    protected lateinit var model: M

    fun init() {
        this.model = modelClass.newInstance()
    }

    override fun onCleared() {
        super.onCleared()
    }
}