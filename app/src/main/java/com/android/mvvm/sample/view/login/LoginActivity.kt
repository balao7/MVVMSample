package com.android.mvvm.sample.view.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.android.mvvm.sample.R
import com.android.mvvm.sample.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val loginViewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginActivityLoginBinding =
            DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)

        // This will call at once while creating activity.
        if(savedInstanceState == null) {
            loginViewModel.initViewModel()
        }

        loginActivityLoginBinding.viewModel = loginViewModel
    }
}