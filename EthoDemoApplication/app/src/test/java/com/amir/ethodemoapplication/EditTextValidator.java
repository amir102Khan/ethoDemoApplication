package com.amir.ethodemoapplication;

import com.amir.ethodemoapplication.util.Common;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EditTextValidator {
    @Test
    public void non_empty_string_return_true() {
        assertTrue(Common.validateEditText("name@email.com"));
    }

    @Test
    public void non_empty_string_return_false() {
        assertTrue(Common.validateEditText(""));
    }
}
