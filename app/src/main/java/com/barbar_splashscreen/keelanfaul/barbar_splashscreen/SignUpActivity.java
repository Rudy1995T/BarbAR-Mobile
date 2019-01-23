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
        apprenticeCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (apprenticeCheckBox.isChecked()) {
                    mentorCheckBox.setChecked(false);
                    mentorCheckBox.setClickable(true);
                    apprenticeCheckBox.setClickable(false);
                }
            }
        });
    }

    private void mentorBox() {
        mentorCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mentorCheckBox.isChecked()) {
                    mentorCheckBox.setClickable(false);
                    apprenticeCheckBox.setChecked(false);
                    apprenticeCheckBox.setClickable(true);
                }
            }
        });
    }

    private void signUpSelect() {
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(matchBasicSignUp()) {
                    final String apiURL = apprenticeCheckBox.isChecked() ? "trainee/traineeSignUp" : "barber/barberSignUp";
                    registerUser(getUser(), apiURL);
                    Intent intent = getIntentType();
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Trainee made mistake", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Intent getIntentType() {
        if(apprenticeCheckBox.isChecked()) {
            return new Intent(this, ApprenticeHomeScreenActivity.class);
        } else {
            return new Intent(this, BarberHomeSrceenActivity.class);
        }
    }

    private void registerUser(final User user, String apiURL) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        String SIGN_UP_URL = getString(R.string.sign_up_url_body);


        SIGN_UP_URL += apiURL;

        JSONObject postUser = user.toJSON();

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, SIGN_UP_URL, postUser,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if(response.getString("message").equals("success")) {


                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("USER SIGN UP", error.toString());
                }
            });

        queue.add(jsonObjectRequest);
    }

    private User parseJSON(JSONObject json) {
        User user = null;
        try {
            String foundFirstName = json.getString("firstname");
            String foundSurname = json.getString("surname");
            String foundUsername = json.getString("username");
            String foundEmail = json.getString("email");
            String foundPassword = json.getString("password");
            String foundAvatar = json.getString("avatar");

            user = new User(foundFirstName, foundSurname, foundUsername, foundEmail, foundPassword, foundAvatar);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
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
