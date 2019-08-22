package com.android.mvvm.sample.common

interface BindableAdapter<T> {

    fun setListData(list: List<T>?)
    fun changePositions(positions: Set<Int>)
}