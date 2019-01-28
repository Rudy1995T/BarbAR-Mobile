package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

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

    private Intent getIntentType() {
        Class c = apprenticeCheckBox.isChecked() ? ApprenticeHomeScreenActivity.class : BarberHomeSrceenActivity.class;
        return new Intent(this, c);
    }

    private void registerUser(final User user, String apiURL) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        final String SIGN_UP_URL = getString(R.string.sign_up_url_body) + apiURL;

        JSONObject postUser = user.toJSON();
        Log.d("regesterUser", "Invoked");
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, SIGN_UP_URL, postUser,
            response -> {
                Bundle bundle = new Bundle();
                bundle.putString("sign_up_details", response.toString());
                Intent intent = getIntentType();
                intent.putExtras(bundle);
                Log.d("USER DETAILS SIGN-UP", bundle.getString("sign_up_details"));
                startActivity(intent);
            },
            error -> Log.d("USER SIGN UP", error.toString()));
        queue.add(jsonObjectRequest);
    }

    private User getUser() {

        String firstName = firstNameTxt.getText().toString();
        String surname = surnameTxt.getText().toString();
        String username = usernameTxt.getText().toString();
        String email = emailTxt.getText().toString();
        String password = passwordTxt.getText().toString();

        return new User(firstName, surname, username, email, password, "");
    }

    private boolean matchRegex(EditText editText, String regex) {
        Pattern pattern = Pattern.compile(regex);
        String txt = editText.getText().toString();
        Matcher matcher = pattern.matcher(txt);

        return matcher.matches();
    }

    private boolean matchBasicSignUp() {
        final String NAME_REGEX = getString(R.string.name_regex);
        final String EMAIL_REGEX = getString(R.string.email_regex);
        final String PASSWORD_REGEX = getString(R.string.password_regex);
        final String USERNAME_REGEX = getString(R.string.username_regex);

        boolean regexValidity = true;
        if(!matchRegex(firstNameTxt, NAME_REGEX)) {
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
        }

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
