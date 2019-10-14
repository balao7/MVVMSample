package com.android.mvvm.sample.app.base

import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.a3i.android.eventzapp.features.base.BaseActivity
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseViewModelActivity<VB : ViewDataBinding, out VM : ViewModel>(
    @LayoutRes private val layoutResID: Int,
    @NonNull private val viewModelClass: KClass<VM>
) : BaseActivity<VB>(layoutResID) {

    @Inject
    protected lateinit var provideFactory: ViewModelProvideFactory

    protected val viewModel: VM by lazy {
        ViewModelProviders.of(this, provideFactory).get(viewModelClass.java)
    }
}
