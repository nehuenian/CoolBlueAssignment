package com.example.coolblueassignment.presentation.util

import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.StringRes

fun Double.toFiveStarsOverFloat() = (toFloat() / 2F)

fun <T> LinearLayout.loadListWithTextViews(listToLoad: List<T>, @StringRes stringResId: Int) {
    listToLoad.forEach { value ->
        addView(
            TextView(context).apply {
                text = context.getString(stringResId, value)
            }
        )
    }
}