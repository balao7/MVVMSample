package com.android.mvvm.sample.view.login

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class LoginModule {

    @Provides
    fun providesLoginRepository(retrofit: Retrofit): LoginRepository =
        LoginRepository(retrofit = retrofit)

}

