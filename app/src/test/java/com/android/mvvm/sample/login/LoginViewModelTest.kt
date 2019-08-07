package com.android.mvvm.sample.login

import com.android.mvvm.sample.view.login.LoginViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LoginViewModelTest {

    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setUp() {
        this.loginViewModel = LoginViewModel()
        loginViewModel.initViewModel()
    }

    @Test
    internal fun test_validateFields_correct() {
        val loginModel = this.loginViewModel.getLoginModel()
        loginModel.email = "bala@tcs.com"
        loginModel.password = "bala12345"

        Assert.assertTrue("Login fields are valid", loginModel.isValidate())
    }

    @Test
    fun test_isValidate_wrong() {
        val loginModel = this.loginViewModel.getLoginModel()
        loginModel.email = "balagovind"
        loginModel.password = "bala"

        Assert.assertTrue("Login fields are not valid", loginModel.isValidate())
    }
}