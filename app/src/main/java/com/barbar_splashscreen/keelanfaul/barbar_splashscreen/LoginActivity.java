package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private EditText validationCode;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();


        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    validationCode.setVisibility(View.VISIBLE);
                    ObjectAnimator animation = ObjectAnimator.ofFloat(findViewById(R.id.login_btn), "translationY", 250f);
                    animation.setDuration(100);
                    animation.start();
                } else {
                    validationCode.setVisibility(View.INVISIBLE);
                    ObjectAnimator animation = ObjectAnimator.ofFloat(findViewById(R.id.login_btn), "translationY", -50f);
                    animation.setDuration(100);
                    animation.start();
                }
            }
        });

    }

    private boolean validBasicLogin() {
        return true;
    }

    private void initViews() {
        userName = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        validationCode = findViewById(R.id.login_validation_code);
        checkBox = findViewById(R.id.ifBarberCheckbox);
    }

    private void animateCheckBox(int visibility, float transition) {
        validationCode.setVisibility(visibility);
        ObjectAnimator animation = ObjectAnimator.ofFloat(findViewById(R.id.login_btn), "translationY", transition);
        animation.setDuration(100);
        animation.start();
    }

}
