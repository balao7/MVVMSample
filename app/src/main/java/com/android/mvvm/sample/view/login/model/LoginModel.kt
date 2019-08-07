package com.android.mvvm.sample.view.login.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.android.mvvm.sample.BR
import com.android.mvvm.sample.R.string.error_message
import java.util.regex.Pattern

class LoginModel : BaseObservable() {

    private data class LoginFields(
        var email: String,
        var password: String,
        var error: Any?
    )

    private val loginFields = LoginFields("", "", null)

    companion object {
        private val EMAIL_PATTERN: Pattern = Pattern
            .compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
            )
    }

    var email: String
        @Bindable get() = this.loginFields.email
        set(value) {
            this.loginFields.email = value
            notifyPropertyChanged(BR.email)
        }

    var password: String
        @Bindable get() = this.loginFields.password
        set(value) {
            this.loginFields.password = value
            notifyPropertyChanged(BR.password)
        }

    @Bindable
    fun getLoginError(): Any? {
        return this.loginFields.error
    }

    fun isValidate(): Boolean {
        val valid = this.isEmailValid() && this.isPasswordValid()
        if (valid) {
            this.loginFields.error = null
        } else {
            this.loginFields.error = error_message
        }

        notifyPropertyChanged(BR.loginError)

        return valid
    }

    private fun isEmailValid(): Boolean {
        if (loginFields.email.isNotBlank()) {
            return EMAIL_PATTERN.matcher(loginFields.email).matches()
        }

        return false
    }

    private fun isPasswordValid(): Boolean {
        if (loginFields.password.isNotEmpty()) {
            if (loginFields.password.length > 5) {
                return true
            }
        }

        return false
    }
}