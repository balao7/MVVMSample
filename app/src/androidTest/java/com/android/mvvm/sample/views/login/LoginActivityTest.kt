package com.android.mvvm.sample.views.login

import androidx.test.rule.ActivityTestRule
import com.android.mvvm.sample.base.BaseAcceptanceActivityTest
import com.android.mvvm.sample.view.login.LoginActivity

class LoginActivityTest : BaseAcceptanceActivityTest<LoginActivity>(LoginActivity::class.java) {

    override fun buildRule(clazz: Class<LoginActivity>):
            ActivityTestRule<LoginActivity> = ActivityTestRule(clazz)
}