package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button Login_button;
    private Button SignUp_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Login_button = (Button) findViewById(R.id.login_button);
        Login_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });

        SignUp_button = (Button) findViewById(R.id.signup_button);
        SignUp_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openSignUpActivity();
            }
        });

    }

    public void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void openSignUpActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
