package com.rezahosseini.meerkatmovie

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RobolectricTest {

    @Test
    fun context_shouldBeAvailable() {
        val context =
            ApplicationProvider.getApplicationContext<Context>()

        assertNotNull(context)
    }
    @Test
    fun context_shouldReadAppName() {
        val context =
            ApplicationProvider.getApplicationContext<Context>()

        val appName = context.getString(R.string.app_name)

        assertEquals("Meerkat Movie", appName)
    }
    @Test
    fun sharedPreferences_shouldSaveAndReadData() {
        val context =
            ApplicationProvider.getApplicationContext<Context>()

        val preferences =
            context.getSharedPreferences(
                "test_preferences",
                Context.MODE_PRIVATE
            )

        preferences.edit()
            .putString("movie_name", "GTA V")
            .apply()

        val movieName =
            preferences.getString("movie_name", null)

        assertEquals("GTA V", movieName)
    }
}