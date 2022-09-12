package com.example.coolblueassignment.espressoutils

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.coolblueassignment.testutil.withViewAtPosition
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher

fun Matcher<View>.verifyItemAtPositionWithValue(item: Matcher<View>, position: Int, value: String) {
    Espresso.onView(this).check(
        ViewAssertions.matches(
            withViewAtPosition(
                position,
                ViewMatchers.hasDescendant(
                    CoreMatchers.allOf(
                        item,
                        ViewMatchers.withText(value),
                        ViewMatchers.isDisplayed()
                    )
                )
            )
        )
    )
}

fun Matcher<View>.verifyItemIsVisibleAtPosition(item: Matcher<View>, position: Int) {
    Espresso.onView(this).check(
        ViewAssertions.matches(
            withViewAtPosition(
                position,
                ViewMatchers.hasDescendant(
                    CoreMatchers.allOf(
                        item,
                        ViewMatchers.isDisplayed()
                    )
                )
            )
        )
    )
}