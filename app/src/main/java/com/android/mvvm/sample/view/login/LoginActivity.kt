package com.android.mvvm.sample.view.login

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.android.mvvm.sample.R
import com.android.mvvm.sample.app.ViewModelFactory
import com.android.mvvm.sample.databinding.ActivityLoginBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<LoginViewModel>

    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginActivityLoginBinding =
            DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)

        // This will call at once while creating activity.
        if (savedInstanceState == null) {
            loginViewModel.initViewModel()
        }

        // Set LoginViewModel to DataBinding.
        loginActivityLoginBinding.viewModel = loginViewModel
    }
}