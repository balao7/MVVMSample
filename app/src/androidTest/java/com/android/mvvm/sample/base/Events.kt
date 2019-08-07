package com.android.mvvm.sample.base

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matchers.allOf

class Events {

    fun eventOnView(@IdRes viewId: Int, vararg actions: ViewAction): ViewInteraction? {
        return onView(allOf(isDisplayed(), withId(viewId))).perform(*actions)
    }
}