package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
public class SignUpActivityTest {

    @Rule
    public ActivityTestRule<SignUpActivity> mSignUpActivity = new ActivityTestRule<>(SignUpActivity.class);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testLaunch() {

    }

    @After
    public void tearDown() throws Exception {
    }
}