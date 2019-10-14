package com.android.mvvm.sample.view

import com.android.mvvm.sample.view.login.LoginActivity
import com.android.mvvm.sample.view.login.LoginModule
import com.android.mvvm.sample.view.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

// https://brightinventions.pl/blog/android-viewmodel-injections-revisited/

// https://github.com/azabost/simple-mvvm-example

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [LoginModule::class])
    internal abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    internal abstract fun contributeMainActivity(): MainActivity
}
