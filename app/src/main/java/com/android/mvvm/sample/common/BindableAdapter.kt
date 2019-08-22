package com.android.mvvm.sample.common

interface BindableAdapter<T> {

    fun setData(data: List<T>)
    fun changePosition(positions: Set<Int>)

}