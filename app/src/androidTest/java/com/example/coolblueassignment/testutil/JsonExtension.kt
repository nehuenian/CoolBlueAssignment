package com.example.coolblueassignment.testutil

import android.content.Context
import androidx.annotation.RawRes
import com.squareup.moshi.Moshi
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Get an object from a json resource in the project.
 *
 * @param context the context used to get the resources
 * @param jsonId the id for the json resource
 * @return the object represented by the json resource
 */
inline fun <reified T> Moshi.fromJson(context: Context, @RawRes jsonId: Int, charset: String): T {
    val inputStream = context.resources.openRawResource(jsonId)
    val reader = BufferedReader(InputStreamReader(inputStream, charset)).use { it.readText() }

    return adapter(T::class.java).fromJson(reader)!!
}

const val READER_CHARSET = "UTF-8"
