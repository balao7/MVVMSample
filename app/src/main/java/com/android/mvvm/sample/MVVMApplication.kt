package com.android.mvvm.sample

import com.android.mvvm.sample.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class MVVMApplication : DaggerApplication() {

    private val appComponent: AndroidInjector<MVVMApplication> by lazy {
        DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = this.appComponent

    override fun onCreate() {
        super.onCreate()
    }
}