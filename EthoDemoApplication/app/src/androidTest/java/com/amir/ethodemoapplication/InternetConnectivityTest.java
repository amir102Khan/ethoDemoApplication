package com.amir.ethodemoapplication;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.amir.ethodemoapplication.util.Common;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InternetConnectivityTest {

    @Test
    public void internet_connected_return_true() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertTrue(Common.checkInternetConnection(appContext));
    }
}
