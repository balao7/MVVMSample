package com.android.mvvm.sample.base

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.android.mvvm.sample.base.CustomDrawableMatchers.withAppCompatDrawable
import com.android.mvvm.sample.base.ToolbarMatchers.withToolbarTitle
import org.hamcrest.Matchers.allOf

import android.content.Intent
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.intent.rule.IntentsTestRule

abstract class BaseActivityRobot<T> {

    @get:IdRes
    protected abstract val spinnerId: Int

    fun launchesActivity(rule: IntentsTestRule<*>): T {
        rule.launchActivity(null)
        return this as T
    }

    fun launchesActivityWithIntent(rule: IntentsTestRule<*>, intent: Intent): T {
        rule.launchActivity(intent)
        return this as T
    }

    // Observation
    protected fun seesTitle(@IdRes toolbarId: Int, @StringRes titleResId: Int): T {
        onView(withId(toolbarId)).check(matches(withToolbarTitle(titleResId)))
        return this as T
    }

    protected fun seesTitle(@IdRes toolbarId: Int, title: String): T {
        onView(withId(toolbarId)).check(matches(withToolbarTitle(title)))
        return this as T
    }

    fun seesBackButton(): T {
        onView(withContentDescription("Navigate up")).check(
            matches(isDisplayed())
        )
        return this as T
    }

    protected fun seesCloseButton(@DrawableRes closeDrawableId: Int): T {
        onView(
            allOf<View>(
                withAppCompatDrawable(closeDrawableId),
                withContentDescription("Navigate up")
            )
        )
            .check(matches(isDisplayed()))
        return this as T
    }

    // Interactions
    fun clicksUpButton(): T {
        onView(withContentDescription("Navigate up")).perform(click())
        return this as T
    }

    fun clicksBackButton(): T {
        pressBack()
        return this as T
    }

    protected fun clicksCloseButton(@DrawableRes closeDrawableId: Int): T {
        onView(
            allOf<View>(
                withAppCompatDrawable(closeDrawableId),
                withContentDescription("Navigate up")
            )
        )
            .perform(click())
        return this as T
    }

    fun doesNotSeeLoadingSpinner(): T {
        onView(withId(spinnerId)).check(doesNotExist())
        return this as T
    }
}
