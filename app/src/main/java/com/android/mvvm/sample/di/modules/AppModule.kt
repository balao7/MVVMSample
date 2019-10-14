package com.android.mvvm.sample.di.modules

import android.app.Application
import android.content.Context
import androidx.annotation.NonNull
import com.android.mvvm.sample.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class AppModule {

    @ApplicationScope
    @Provides
    fun provideApplicationContext(@NonNull application: Application): Context =
        application.applicationContext

}