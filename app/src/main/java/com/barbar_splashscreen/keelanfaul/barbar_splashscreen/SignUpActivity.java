package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private CheckBox apprenticeCheckBox;
    private CheckBox mentorCheckBox;
    private Spinner countySpinner;
    private EditText addressLine1;
    private EditText addressLine2;
    private EditText city;

    private Button signUpBtn;

    private EditText firstNameTxt;
    private EditText surnameTxt;
    private EditText emailTxt;
    private EditText passwordTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initViews();

        ArrayAdapter<String> countyAdapter = new ArrayAdapter<>(SignUpActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.county_arrays));
        countyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countySpinner.setAdapter(countyAdapter);

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
                    addressLine1.setVisibility(View.GONE);
                    addressLine2.setVisibility(View.GONE);
                    city.setVisibility(View.GONE);
                    countySpinner.setVisibility(View.GONE);
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
                    fieldVisibility(View.VISIBLE);
                    apprenticeCheckBox.setClickable(true);

                }
            }
        });
    }

    private void signUpSelect() {
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(apprenticeCheckBox.isChecked()) {
                    if(matchBasicSignUp()) {
                        Intent intent = new Intent(view.getContext(), TraineeMain.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Trainee made mistake", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if(matchMentorSignUp()) {
                        Toast.makeText(getApplicationContext(), "Send Barber to next activity", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Barber made mistake", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean matchRegex(EditText editText, String regex) {
        Pattern pattern = Pattern.compile(regex);
        String txt = editText.getText().toString();
        Matcher matcher = pattern.matcher(txt);

        return matcher.matches();
    }

    private void fieldVisibility(int visibility) {
        addressLine1.setVisibility(visibility);
        addressLine2.setVisibility(visibility);
        city.setVisibility(visibility);
        countySpinner.setVisibility(visibility);
    }

    private boolean matchBasicSignUp() {
        final String NAME_REGEX = getString(R.string.name_regex);
        final String EMAIL_REGEX = getString(R.string.email_regex);
        final String PASSWORD_REGEX = getString(R.string.password_regex);

        boolean regexValidity = true;
        if(!matchRegex(firstNameTxt, NAME_REGEX)) {
            firstNameTxt.setError("Invalid First Name");
            regexValidity = false;
        }

        if(!matchRegex(surnameTxt, NAME_REGEX)) {
            surnameTxt.setError("Invalid Surname");
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

    private boolean matchMentorSignUp() {

        final String ADDRESS_REGEX = getString(R.string.address_regex);
        boolean regexValidity = true;
        if(!matchRegex(addressLine1, ADDRESS_REGEX)) {
            addressLine1.setError("Invalid address");
            regexValidity = false;
        }

        if(!matchRegex(addressLine2, ADDRESS_REGEX)) {
            addressLine2.setError("Invalid address");
            regexValidity = false;
        }

        return matchBasicSignUp() && regexValidity;
    }

    private void initViews() {
        apprenticeCheckBox = findViewById(R.id.ifApprenticeCheckbox);
        mentorCheckBox = findViewById(R.id.ifMentorCheckbox);
        addressLine1 = findViewById(R.id.AddressLine1);
        addressLine2 = findViewById(R.id.AddressLine2);
        city = findViewById(R.id.City);
        countySpinner = findViewById(R.id.county_spinner);
        signUpBtn = findViewById(R.id.signup_btn1);
        firstNameTxt = findViewById(R.id.signup_firstname);
        surnameTxt = findViewById(R.id.signup_surname);
        emailTxt = findViewById(R.id.signup_Email);
        passwordTxt = findViewById(R.id.signup_Password);
    }
}
