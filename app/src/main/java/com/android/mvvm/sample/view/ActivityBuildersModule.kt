package com.android.mvvm.sample.view

import com.android.mvvm.sample.di.scope.ActivityScope
import com.android.mvvm.sample.view.login.LoginActivity
import com.android.mvvm.sample.view.login.LoginModule
import com.android.mvvm.sample.view.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [LoginModule::class])
    internal abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    internal abstract fun contributeMainActivity(): MainActivity
}
