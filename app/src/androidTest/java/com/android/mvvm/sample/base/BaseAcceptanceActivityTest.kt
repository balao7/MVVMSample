package com.android.mvvm.sample.base

import android.app.Activity
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
abstract class BaseAcceptanceActivityTest<T : Activity>(clazz: Class<T>) {

    @Rule
    @JvmField
    val activityRule: ActivityTestRule<T> = this.buildRule(clazz)

    private var activity: T? = null
    protected val check: Matchers = Matchers()
    protected val event: Events = Events()

    abstract fun buildRule(clazz: Class<T>): ActivityTestRule<T>

    @Before
    fun setActivity() {
        this.activity = this.activityRule.activity
    }
}