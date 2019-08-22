package com.android.mvvm.sample.util

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.mvvm.sample.common.BindableAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("data")
fun <T: Any> setRecyclerViewListData(recyclerView: RecyclerView, list: List<T>?) {
    if (recyclerView.adapter is BindableAdapter<*>) {
        (recyclerView.adapter as BindableAdapter<T>)
            .setListData(list)
    }
}

@BindingAdapter("changedPositions")
fun <T> setDataChanged(recyclerView: RecyclerView, positions: Set<Int>) {
    if (recyclerView.adapter is BindableAdapter<*>) {
        (recyclerView.adapter as BindableAdapter<T>)
            .changePositions(positions)
    }
}

@BindingAdapter("cardImageUrl")
fun AppCompatImageView.loadCardImageUrl(imageUrl: String?) {
    if (!imageUrl.isNullOrBlank()) {
        Glide.with(this.context)
            .load(imageUrl)
            .apply(RequestOptions().fitCenter())
            .into(this)
    }
}