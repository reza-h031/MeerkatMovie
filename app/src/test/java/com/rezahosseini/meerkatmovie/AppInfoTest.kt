package com.rezahosseini.meerkatmovie

import androidx.test.core.app.ApplicationProvider
import com.rezahosseini.meerkatmovie.util.AppInfo
import android.content.Context
import org.junit.Assert.assertEquals
import org.junit.Test

class AppInfoTest {

    @Test
    fun getAppName_shouldReturnCorrectName() {
        val context =
            ApplicationProvider.getApplicationContext<Context>()

        val appInfo = AppInfo(context)

        val result = appInfo.getAppName()

        assertEquals("Meerkat Movie", result)
    }
}