package com.android.mvvm.sample.base

import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra

import android.app.Activity
import android.content.Intent

import androidx.test.espresso.intent.rule.IntentsTestRule

class BaseIntentsTestRule<T : Activity>(clazz: Class<T>) : IntentsTestRule<T>(clazz, false, false) {

    fun launchActivity(): T {
        return this.launchActivity(null)
    }

    override fun launchActivity(intent: Intent?): T {
        return super.launchActivity(intent)
    }

    fun <A : Activity> checkActivityLaunched(activity: Class<A>) {
        intended(hasComponent(activity.name))
    }

    fun <V> checkIntentHasExtra(key: String, value: V): T {
        intended(hasExtra(key, value))

        return this as T
    }
}
