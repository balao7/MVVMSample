package com.android.mvvm.sample.base;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.singaporeair.support.test.espresso.ToolbarMatchers.withToolbarTitle;

import androidx.annotation.IdRes;
import androidx.annotation.StringRes;
import androidx.test.espresso.Espresso;

public abstract class BaseFragmentRobot<T> {

  // Observation
  protected T seesTitle(@IdRes int toolbarId, @StringRes int titleResId) {
    onView(withId(toolbarId)).check(matches(withToolbarTitle(titleResId)));
    return (T) this;
  }

  protected T seesTitle(@IdRes int toolbarId, String title) {
    onView(withId(toolbarId)).check(matches(withToolbarTitle(title)));
    return (T) this;
  }

  public T doesNotSeeBackButton() {
    onView(withContentDescription("Navigate up")).check(
        doesNotExist());
    return (T) this;
  }

  public T seesBackButton() {
    onView(withContentDescription("Navigate up")).check(
        matches(isDisplayed()));
    return (T) this;
  }

  public T doesNotSeeLoadingSpinner() {
    onView(withId(getSpinnerId())).check(doesNotExist());
    return (T) this;
  }

  @IdRes
  protected abstract int getSpinnerId();

  // Interactions
  public T clicksUpButton() {
    onView(withContentDescription("Navigate up")).perform(click());
    return (T) this;
  }

  public T clicksBackButton() {
    Espresso.pressBack();
    return (T) this;
  }
}
