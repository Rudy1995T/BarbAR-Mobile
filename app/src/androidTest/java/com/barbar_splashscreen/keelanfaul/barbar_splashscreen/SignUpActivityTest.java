package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

import android.nfc.Tag;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
public class SignUpActivityTest {

    private final String TAG = SignUpActivity.class.getSimpleName();

    @Rule
    private ActivityTestRule<SignUpActivity> mSignUpActivityRule = new ActivityTestRule<>(SignUpActivity.class, true, true);
    private SignUpActivity mSignUpActivity = null;

    @Before
    public void setUp() throws Exception {
        mSignUpActivity = mSignUpActivityRule.getActivity();
    }

    @Test
    public void testLaunch() {
        assertNotNull(mSignUpActivity);
    }

    @Test
    public void testValidUsername() throws Exception {
        try {
            runOnUiThread(() -> {
                EditText username = mSignUpActivity.findViewById(R.id.signup_username);
                username.setText("cian1995");
                String usernameRegex = mSignUpActivity.getString(R.string.username_regex);

                assertTrue(mSignUpActivity.matchRegex(username, usernameRegex));
            });
        } catch (Throwable e) {
            Log.e(TAG, "USERNAME TEST", e);
            fail("Throwable Exception");
        }
    }


    @Test
    public void testValidFirstName() throws Exception{
        try {
            runOnUiThread(() -> {
                EditText firstName = mSignUpActivity.findViewById(R.id.signup_firstname);
                firstName.setText("Cian");
                String firstNameRegex = mSignUpActivity.getString(R.string.name_regex);

                assertTrue(mSignUpActivity.matchRegex(firstName, firstNameRegex));
            });
        } catch (Throwable e) {
            Log.e(TAG, "FIRST NAME TEST", e);
            fail("Throwable Exception");
        }
    }

    @Test
    public void testValidSurname() throws Exception {
        try {
            runOnUiThread(() -> {
                EditText surname = mSignUpActivity.findViewById(R.id.signup_surname);
                surname.setText("McAteer");
                String surnameRegex = mSignUpActivity.getString(R.string.name_regex);

                assertTrue(mSignUpActivity.matchRegex(surname, surnameRegex));
            });
        } catch (Throwable e) {
            Log.e(TAG, "SURNAME TEST", e);
            fail("Throwable Exception");
        }
    }

    @Test
    public void testValidEmail() throws Exception {
        try {
            runOnUiThread(() -> {
                EditText email = mSignUpActivity.findViewById(R.id.signup_Email);
                email.setText("cianmcateer@gmail.com");
                String emailRegex = mSignUpActivity.getString(R.string.email_regex);

                assertTrue(mSignUpActivity.matchRegex(email, emailRegex));
            });
        } catch (Throwable e) {
            Log.e(TAG, "EMAIL TEST", e);
            fail("Throwable Exception");
        }
    }


    @Test
    public void testValidPassword() throws Exception {
        try {
            runOnUiThread(() -> {
                EditText password = mSignUpActivity.findViewById(R.id.signup_Password);
                password.setText("Pa$$W0rd");
                String passwordRegex = mSignUpActivity.getString(R.string.password_regex);
                Log.d(TAG, password.getText().toString());
                Log.d(TAG, passwordRegex);
                assertTrue(mSignUpActivity.matchRegex(password, passwordRegex));
            });
        } catch (Throwable e) {
            Log.e(TAG, "EMAIL TEST", e);
            fail("Throwable Exception");
        }
    }

    @Test
    public void testShortUsername() {
        try {
            runOnUiThread(() -> {
                EditText firstName = mSignUpActivity.findViewById(R.id.signup_firstname);
                firstName.setText("Cian13"); // Too Short
                String usernameRegex = mSignUpActivity.getString(R.string.name_regex);

                assertFalse(mSignUpActivity.matchRegex(firstName, usernameRegex));
            });
        } catch (Throwable e) {
            Log.e(TAG, "INVALID FIRST NAME TEST", e);
            fail("Throwable Exception");
        }
    }

    @Test
    public void testSpaceUsername() {
        try {
            runOnUiThread(() -> {
                EditText firstName = mSignUpActivity.findViewById(R.id.signup_firstname);
                firstName.setText("Cian mcateer"); // Space in
                String nameRegex = mSignUpActivity.getString(R.string.name_regex);

                assertFalse(mSignUpActivity.matchRegex(firstName, nameRegex));
            });
        } catch (Throwable e) {
            Log.e(TAG, "INVALID FIRST NAME TEST", e);
            fail("Throwable Exception");
        }
    }

    @Test
    public void testFirstNameWithNumbers() {
        try {
            runOnUiThread(() -> {
                EditText firstName = mSignUpActivity.findViewById(R.id.signup_firstname);
                firstName.setText("Cian123"); // Numbers in name
                String nameRegex = mSignUpActivity.getString(R.string.name_regex);

                assertFalse(mSignUpActivity.matchRegex(firstName, nameRegex));
            });
        } catch (Throwable e) {
            Log.e(TAG, "INVALID FIRST NAME TEST", e);
            fail("Throwable Exception");
        }
    }

    @Test
    public void testFirstNameWithSpace() {
        try {
            runOnUiThread(() -> {
                EditText firstName = mSignUpActivity.findViewById(R.id.signup_firstname);
                firstName.setText("Ci an"); // Space in name
                String nameRegex = mSignUpActivity.getString(R.string.name_regex);

                assertFalse(mSignUpActivity.matchRegex(firstName, nameRegex));
            });
        } catch (Throwable e) {
            Log.e(TAG, "INVALID FIRST NAME TEST", e);
            fail("Throwable Exception");
        }
    }

    @Test
    public void testFirstNameWithSpecialChars() {
        try {
            runOnUiThread(() -> {
                EditText firstName = mSignUpActivity.findViewById(R.id.signup_firstname);
                firstName.setText("$ammy"); // Special Characters in name
                String nameRegex = mSignUpActivity.getString(R.string.name_regex);

                assertFalse(mSignUpActivity.matchRegex(firstName, nameRegex));
            });
        } catch (Throwable e) {
            Log.e(TAG, "INVALID FIRST NAME TEST", e);
            fail("Throwable Exception");
        }
    }

    @Test
    public void testSurnameWithNumbers() {
        try {
            runOnUiThread(() -> {
                EditText surname = mSignUpActivity.findViewById(R.id.signup_surname);
                surname.setText("McAteer123"); // Numbers in name
                String nameRegex = mSignUpActivity.getString(R.string.name_regex);

                assertFalse(mSignUpActivity.matchRegex(surname, nameRegex));
            });
        } catch (Throwable e) {
            Log.e(TAG, "INVALID SURNAME TEST", e);
            fail("Throwable Exception");
        }
    }

    @Test
    public void testSurnameWithSpecialChars() {
        try {
            runOnUiThread(() -> {
                EditText surname = mSignUpActivity.findViewById(R.id.signup_surname);
                surname.setText("$ammy"); // Special Characters in name
                String nameRegex = mSignUpActivity.getString(R.string.name_regex);

                assertFalse(mSignUpActivity.matchRegex(surname, nameRegex));
            });
        } catch (Throwable e) {
            Log.e(TAG, "INVALID SURNAME TEST", e);
            fail("Throwable Exception");
        }
    }

    @Test
    public void testSurnameWithSpace() {
        try {
            runOnUiThread(() -> {
                EditText surname = mSignUpActivity.findViewById(R.id.signup_surname);
                surname.setText("Ci an"); // Space in name
                String nameRegex = mSignUpActivity.getString(R.string.name_regex);

                assertFalse(mSignUpActivity.matchRegex(surname, nameRegex));
            });
        } catch (Throwable e) {
            Log.e(TAG, "INVALID SURNAME TEST", e);
            fail("Throwable Exception");
        }
    }

    @After
    public void tearDown() throws Exception {
    }
}