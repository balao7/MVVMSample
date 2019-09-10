package com.android.mvvm.sample.base;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import androidx.annotation.StringRes;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class ToolbarMatchers {

  public static Matcher<View> withToolbarTitle(@StringRes final int stringId) {
    return new BoundedMatcher<View, Toolbar>(Toolbar.class) {
      Matcher<View> wrappedMatcher;

      @Override
      public void describeTo(Description description) {
        description.appendText("title from id: " + stringId);
        if (wrappedMatcher != null) {
          description.appendText(" ");
          wrappedMatcher.describeTo(description);
        }
      }

      @Override
      protected boolean matchesSafely(Toolbar toolbar) {
        String actualString = toolbar.getResources().getString(stringId);
        wrappedMatcher = withToolbarTitle(actualString);
        return wrappedMatcher.matches(toolbar);
      }
    };
  }

  public static Matcher<View> withToolbarTitle(final String title) {
    return new BoundedMatcher<View, Toolbar>(Toolbar.class) {
      String actualTitle = null;

      @Override
      public void describeTo(Description description) {
        description.appendText("title with text: " + title);
        if (actualTitle != null) {
          description.appendText("\n ----> But got: " + actualTitle);
        }
      }

      @Override
      protected boolean matchesSafely(Toolbar toolbar) {
        CharSequence charTitle = toolbar.getTitle();

        return (((charTitle == null && title == null) || (charTitle.toString().equals(title)))
            && isDisplayed().matches(toolbar));
      }
    };
  }
}
