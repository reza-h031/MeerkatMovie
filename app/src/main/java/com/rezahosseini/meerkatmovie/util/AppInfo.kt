package com.rezahosseini.meerkatmovie.util

import android.content.Context

class AppInfo(
    private val context: Context
) {

    fun getAppName(): String {
        return context.getString(
            com.rezahosseini.meerkatmovie.R.string.app_name
        )
    }
}