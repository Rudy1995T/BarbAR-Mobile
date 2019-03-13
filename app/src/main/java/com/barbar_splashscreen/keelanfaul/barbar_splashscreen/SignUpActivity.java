package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private CheckBox apprenticeCheckBox;
    private CheckBox mentorCheckBox;

    private Button signUpBtn;

    private EditText firstNameTxt;
    private EditText surnameTxt;
    private EditText usernameTxt;
    private EditText emailTxt;
    private EditText passwordTxt;

    private final String TAG = SignUpActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initViews();
        signUpSelect();
        mentorBox();
        apprenticeBox();
    }

    private void apprenticeBox() {
        apprenticeCheckBox.setOnClickListener(view -> {
            if (apprenticeCheckBox.isChecked()) {
                mentorCheckBox.setChecked(false);
                mentorCheckBox.setClickable(true);
                apprenticeCheckBox.setClickable(false);
            }
        });
    }

    private void mentorBox() {
        mentorCheckBox.setOnClickListener(view -> {
            if(mentorCheckBox.isChecked()) {
                mentorCheckBox.setClickable(false);
                apprenticeCheckBox.setChecked(false);
                apprenticeCheckBox.setClickable(true);
            }
        });
    }

    private void signUpSelect() {
        signUpBtn.setOnClickListener(view -> {
            if(matchBasicSignUp()) {
                final String apiURL = apprenticeCheckBox.isChecked() ? "trainee/signUp" : "barber/signUp";
                registerUser(getUser(), apiURL);

            } else {
                Toast.makeText(getApplicationContext(), "Trainee made mistake", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     *
     * @param user
     * @param apiURL
     */
    private void registerUser(final User user, String apiURL) {
        final String SIGN_UP_URL = getString(R.string.sign_up_url_body) + apiURL;

        String postUser = user.jsonFormat();

        ProgressDialog progressDialog = new ProgressDialog(this);


        progressDialog.setMessage("Loading... Please Wait");
        progressDialog.show();

        new PostUser(getApplicationContext(), apprenticeCheckBox.isChecked()).execute(SIGN_UP_URL, postUser);

        Handler handler = new Handler();
        handler.postDelayed(() -> progressDialog.dismiss(), 3000);

    }

    private User getUser() {

        String firstName = firstNameTxt.getText().toString();
        String surname = surnameTxt.getText().toString();
        String username = usernameTxt.getText().toString();
        String email = emailTxt.getText().toString();
        String password = passwordTxt.getText().toString();

        return new User(firstName, surname, username, email, password, "");
    }

    protected boolean matchRegex(EditText editText, String regex) {
        Pattern pattern = Pattern.compile(regex);
        String txt = editText.getText().toString();
        Matcher matcher = pattern.matcher(txt);

        return matcher.matches() && !TextUtils.isEmpty(editText.getText().toString());
    }

    protected boolean matchBasicSignUp() {
        final String NAME_REGEX = getString(R.string.name_regex);
        final String EMAIL_REGEX = getString(R.string.email_regex);
        final String PASSWORD_REGEX = getString(R.string.password_regex);
        final String USERNAME_REGEX = getString(R.string.username_regex);

        boolean regexValidity = true;

        // COMMENT OUT FOR DEBUGGING
        /*if(!matchRegex(firstNameTxt, NAME_REGEX)) {
            firstNameTxt.setError("Invalid First Name");
            regexValidity = false;
        }

        if(!matchRegex(surnameTxt, NAME_REGEX)) {
            surnameTxt.setError("Invalid Surname");
            regexValidity = false;
        }

        if(!matchRegex(usernameTxt, USERNAME_REGEX)) {
            usernameTxt.setError("Invalid Username");
            regexValidity = false;
        }

        if(!matchRegex(emailTxt, EMAIL_REGEX)) {
            emailTxt.setError("Invalid Email");
            regexValidity = false;
        }

        if(!matchRegex(passwordTxt, PASSWORD_REGEX)) {
            passwordTxt.setError("Invalid Password");
            regexValidity = false;
        }*/

        return regexValidity;
    }

    private void initViews() {
        apprenticeCheckBox = findViewById(R.id.ifApprenticeCheckbox);
        mentorCheckBox = findViewById(R.id.ifMentorCheckbox);
        signUpBtn = findViewById(R.id.signup_btn1);
        firstNameTxt = findViewById(R.id.signup_firstname);
        surnameTxt = findViewById(R.id.signup_surname);
        usernameTxt = findViewById(R.id.signup_username);
        emailTxt = findViewById(R.id.signup_Email);
        passwordTxt = findViewById(R.id.signup_Password);
    }


}