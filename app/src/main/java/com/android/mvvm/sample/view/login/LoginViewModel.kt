package com.android.mvvm.sample.view.login

import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import com.android.mvvm.sample.R
import com.android.mvvm.sample.view.login.model.LoginModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository?
) : ViewModel() {

    private lateinit var loginModel: LoginModel

    object BindingAdapters {

        @BindingAdapter("error")
        @JvmStatic
        fun TextView.bindLoginErrorText(stringOrResId: Any?) {
            if (stringOrResId != null) {
                if (stringOrResId is String)
                    this.text = stringOrResId
                else
                    this.text = this.context.getString(stringOrResId as Int)

                this.visibility = View.VISIBLE
            } else {
                this.visibility = View.GONE
            }
        }

    }

    fun getLoginModel(): LoginModel {
        return this.loginModel
    }

    fun initViewModel() {
        this.loginModel = LoginModel()
    }

    fun onLoginButtonClick() {
        if (this.loginRepository != null) {
            Log.d("LoginViewModel", "LoginRepository has injected.")
        }
        if (loginModel.isValidate()) {
            // TODO: Call Webservice here.
            Log.d(
                "LoginViewModel",
                "User: " + loginModel.email + ", Password:- " + loginModel.password
            )
        }
    }

    fun onShowHideTextCheckedChanged(appCompatEditText: AppCompatEditText, checked: Boolean) {
        Log.d("ViewModel", "Username checked: $checked")

        appCompatEditText.text?.let {
            if (it.isNotEmpty()) {
                if (checked) {
                    when {
                        appCompatEditText.id == R.id.username -> appCompatEditText.inputType =
                            InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                        appCompatEditText.id == R.id.password -> appCompatEditText.inputType =
                            InputType.TYPE_CLASS_TEXT
                    }

                    appCompatEditText.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                } else {
                    appCompatEditText.transformationMethod =
                        PasswordTransformationMethod.getInstance()
                }

                // Set EditText Selection.
                appCompatEditText.setSelection(it.length)
            }
        }
    }
}