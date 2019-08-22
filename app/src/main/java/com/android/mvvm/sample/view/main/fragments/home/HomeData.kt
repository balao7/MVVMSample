package com.android.mvvm.sample.view.main.fragments.home

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

data class HomeData(
    val validity: String,
    val description: String,
    val price: String,
    val cardImageUrl: String
) {

    object BindingAdapters {

        @JvmStatic
        @BindingAdapter("cardImageUrl")
        fun AppCompatImageView.loadCardImageUrl(imageUrl: String) {
            Glide.with(this.context)
                .load(imageUrl)
                .apply(RequestOptions().fitCenter())
                .into(this)
        }
    }
}