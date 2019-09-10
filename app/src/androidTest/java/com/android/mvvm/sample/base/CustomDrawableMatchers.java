package com.android.mvvm.sample.base;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.VectorDrawable;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.core.content.ContextCompat;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import org.hamcrest.CustomMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class CustomDrawableMatchers {

  public static Matcher<View> withAppCompatDrawable(@DrawableRes final int drawableId) {
    return new CustomMatcher<View>("with drawable " + drawableId) {
      @Override
      public boolean matches(Object item) {
        Drawable drawable = getDrawable(item);

        if (null == drawable) {
          return false;
        }

        Drawable expectedDrawable = AppCompatResources
            .getDrawable(((View) item).getContext(), drawableId);
        return matchDrawable(drawable, expectedDrawable);
      }
    };
  }

  public static Matcher<View> withDrawable(@DrawableRes final int drawableId) {
    return new CustomMatcher<View>("with drawable " + drawableId) {
      @Override
      public boolean matches(Object item) {
        Drawable drawable = getDrawable(item);

        Drawable expectedCompatDrawable = AppCompatResources
            .getDrawable(((View) item).getContext(), drawableId);
        Drawable expectedDrawable = ((View) item).getContext().getDrawable(drawableId);
        return matchDrawable(drawable, expectedCompatDrawable) || matchDrawable(drawable,
            expectedDrawable);
      }
    };
  }

  public static Matcher<View> withActionIconDrawable(@DrawableRes final int resourceId) {
    return new BoundedMatcher<View, ActionMenuItemView>(ActionMenuItemView.class) {
      @Override
      public void describeTo(final Description description) {
        description.appendText("has image drawable resource " + resourceId);
      }

      @Override
      public boolean matchesSafely(final ActionMenuItemView actionMenuItemView) {
        Drawable expectedDrawable = AppCompatResources
            .getDrawable(actionMenuItemView.getContext(), resourceId);
        @SuppressLint("RestrictedApi")
        Drawable itemDrawable = actionMenuItemView.getItemData().getIcon();
        return matchDrawable(expectedDrawable, itemDrawable);
      }
    };
  }

  public static Matcher<View> withCompoundDrawable(
          @DrawableRes final int drawableId,
          final int position) {
    return new BoundedMatcher<View, TextView>(TextView.class) {
      @Override
      public void describeTo(Description description) {
        description.appendText("with drawable" + drawableId);
      }

      @Override
      protected boolean matchesSafely(TextView item) {
        // Order is as follows: left, top, right, and bottom
        Drawable drawable = item.getCompoundDrawables()[position];

        Drawable expectedCompatDrawable = AppCompatResources
            .getDrawable(item.getContext(), drawableId);
        Drawable expectedDrawable = item.getContext().getDrawable(drawableId);
        return matchDrawable(drawable, expectedCompatDrawable) || matchDrawable(drawable,
            expectedDrawable);
      }
    };
  }

  private static Matcher<View> withDrawable(final Drawable expectedDrawable) {
    return new CustomMatcher<View>("with drawable " + expectedDrawable) {
      @Override
      public boolean matches(Object item) {
        return viewMatchesDrawable(item, expectedDrawable);
      }
    };
  }

  public static Matcher<View> withDrawableWithParentTag(final String tag, @IdRes final int drawableId,
                                                        @DrawableRes final int drawableRes) {
    return new CustomMatcher<View>(
        "with drawable " + drawableId + " with parent tag " + tag) {

      @Override
      public boolean matches(Object item) {
        Drawable expectedDrawable = AppCompatResources
            .getDrawable(((View) item).getContext(), drawableRes);

        ViewGroup drawableParent = ((View) item).findViewWithTag(tag);
        View drawable = drawableParent.findViewById(drawableId);
        return viewMatchesDrawable(drawable, expectedDrawable);
      }
    };
  }

  public static Matcher<View> withTextColor(@ColorRes final int colorRes) {
    return new CustomMatcher<View>("with color drawable " + colorRes) {
      @Override
      public boolean matches(Object item) {
        if (item instanceof TextView) {
          TextView textView = ((TextView) item);
          int textColor = textView.getCurrentTextColor();
          int inputTextColor = ContextCompat.getColor(textView.getContext(), colorRes);
          return textColor == inputTextColor;
        } else {
          return false;
        }
      }
    };
  }

  public static Matcher<View> withColor(@ColorRes final int colorRes) {
    return new CustomMatcher<View>("with color drawable " + colorRes) {
      @Override
      public boolean matches(Object item) {
        Drawable drawable;
        if (item instanceof ViewGroup) {
          drawable = ((ViewGroup) item).getBackground();
        } else {
          return false;
        }
        ColorDrawable expectedDrawable = new ColorDrawable(ContextCompat.getColor(
            InstrumentationRegistry.getTargetContext(), colorRes));
        return matchDrawable(drawable, expectedDrawable);
      }
    };
  }

  public static Matcher<View> withTintedDrawable(@DrawableRes final int drawableId,
      @ColorRes final int colorId) {
    Drawable drawable = AppCompatResources
        .getDrawable(InstrumentationRegistry.getTargetContext(), drawableId);
    drawable.setTint(ContextCompat.getColor(InstrumentationRegistry.getTargetContext(), colorId));

    return withDrawable(drawable);
  }

  private static boolean matchDrawable(Drawable drawable, Drawable otherDrawable) {
    if (drawable instanceof BitmapDrawable && otherDrawable instanceof BitmapDrawable) {
      Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
      Bitmap otherBitmap = ((BitmapDrawable) otherDrawable).getBitmap();
      return bitmap.sameAs(otherBitmap);
    }
    if (drawable instanceof GradientDrawable && otherDrawable instanceof GradientDrawable) {
      ConstantState constantState = drawable.getConstantState();
      ConstantState otherConstantState = otherDrawable.getConstantState();
      return constantState.equals(otherConstantState);
    }
    if (drawable instanceof ColorDrawable && otherDrawable instanceof ColorDrawable) {
      @ColorInt int color = ((ColorDrawable) drawable).getColor();
      @ColorInt int otherColor = ((ColorDrawable) otherDrawable).getColor();
      return color == otherColor;
    }
    if ((drawable instanceof VectorDrawableCompat || drawable instanceof VectorDrawable)
        && (otherDrawable instanceof VectorDrawableCompat
        || otherDrawable instanceof VectorDrawable)) {
      Bitmap vectorBitmap = getBitmapFromVectorDrawable(drawable);
      Bitmap otherVectorBitmap = getBitmapFromVectorDrawable(otherDrawable);
      return vectorBitmap.sameAs(otherVectorBitmap);
    }

    if ((drawable instanceof StateListDrawable)
        && (otherDrawable instanceof StateListDrawable)) {
      Bitmap vectorBitmap = getBitmapFromVectorDrawable(drawable);
      Bitmap otherVectorBitmap = getBitmapFromVectorDrawable(otherDrawable);
      return vectorBitmap.sameAs(otherVectorBitmap);
    }
    return false;
  }

  private static boolean viewMatchesDrawable(Object item, Drawable expectedDrawable) {
    Drawable drawable;
    if (item instanceof AppCompatImageView) {
      drawable = ((AppCompatImageView) item).getDrawable();
    } else if (item instanceof ImageView) {
      drawable = ((ImageView) item).getDrawable().getCurrent();
    } else if (item instanceof ViewGroup) {
      drawable = ((ViewGroup) item).getBackground();
    } else {
      return false;
    }

    return matchDrawable(drawable, expectedDrawable);
  }

  private static Bitmap getBitmapFromVectorDrawable(Drawable drawable) {
    Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
        drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
    drawable.draw(canvas);

    return bitmap;
  }

  private static Drawable getDrawable(Object item) {
    if (item instanceof AppCompatImageView) {
      return ((AppCompatImageView) item).getDrawable();
    } else if (item instanceof ImageView) {
      return ((ImageView) item).getDrawable().getCurrent();
    } else if (item instanceof ViewGroup) {
      return ((ViewGroup) item).getBackground();
    } else if (item instanceof AppCompatButton) {
      return ((AppCompatButton) item).getBackground();
    }
    return null;
  }
}
