package com.android.mvvm.sample.view.main.fragments.home

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.android.mvvm.sample.BR

class HomeDataModel : BaseObservable() {

    @get: Bindable
    var isAlertOneVisible: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.alertOneVisible)
        }


    @get: Bindable
    var homeDataList: MutableList<HomeListData> = mutableListOf()
        set(value) {
            field = value
            notifyPropertyChanged(BR.homeDataList)
        }
}