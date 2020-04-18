package com.amir.ethodemoapplication;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.amir.ethodemoapplication.util.Common;
import com.amir.ethodemoapplication.util.SharedPref;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SharedPreferenceTest {
    @Test
    public void shared_preference_return_equal_string_with_correct_key() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SharedPref sharedPref = new SharedPref(appContext);
        sharedPref.setString("key","string");
        assertEquals("string",sharedPref.getString("key"));
    }

    @Test
    public void shared_preference_return_equal_boolean_value_with_correct_key() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SharedPref sharedPref = new SharedPref(appContext);
        sharedPref.setBoolean("key",true);
        assertTrue(sharedPref.getBoolean("key"));
    }
}
