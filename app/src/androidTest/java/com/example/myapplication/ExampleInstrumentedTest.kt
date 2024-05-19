package com.example.myapplication;

import android.content.pm.PackageManager
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.myapplication", appContext.packageName)
    }

    @Test
    fun appContextNotNull() {
        // Test to check that the application context is not null
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertNotNull(appContext)
    }

    @Test
    fun accessAppResources() {
        // Test to check that app resources can be accessed
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val appName = appContext.getString(R.string.app_name)
        assertNotNull("Resource not found", appName)
    }

    @Test
    fun checkAppVersion() {
        // Test to verify the application version
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        try {
            val packageInfo = appContext.packageManager.getPackageInfo(appContext.packageName, 0)
            val versionName = packageInfo.versionName
            assertEquals("1.0", versionName) // Replace "1.0" with your expected version
        } catch (e: PackageManager.NameNotFoundException) {
            fail("Package name not found: " + e.message)
        }
    }
}