package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText userNameTxt;
    private EditText passwordTxt;
    private EditText validationCodeTxt;
    private CheckBox checkBox;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();


        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    validationCodeTxt.setVisibility(View.VISIBLE);
                    ObjectAnimator animation = ObjectAnimator.ofFloat(findViewById(R.id.login_btn), "translationY", 250f);
                    animation.setDuration(100);
                    animation.start();

                } else {
                    validationCodeTxt.setVisibility(View.INVISIBLE);
                    ObjectAnimator animation = ObjectAnimator.ofFloat(findViewById(R.id.login_btn), "translationY", -50f);
                    animation.setDuration(100);
                    animation.start();
                }
            }
        });


        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = userNameTxt.getText().toString();
                String password = passwordTxt.getText().toString();

                sendRequest(new Account(userName, password));

            }
        });



    }

    public void sendRequest(final Account account) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        final String URL = "https://barbarservice.azurewebsites.net/api/barber";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, account.toJSON(),
        new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    if(response == null) {
                        Toast.makeText(getApplicationContext(), "Result not found", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    String userName = response.getString("username");
                    String password = response.getString("password");

                    Account foundAccount = new Account(userName, password);

                    if(account.equals(foundAccount)) {
                        Toast.makeText(LoginActivity.this, "FOUND", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "NOT FOUND", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Volley failed", error.toString());
            }
        });


        requestQueue.add(jsonObjectRequest);
    }

    private void initViews() {
        userNameTxt = findViewById(R.id.login_username);
        passwordTxt = findViewById(R.id.login_password);
        validationCodeTxt = findViewById(R.id.login_validation_code);
        checkBox = findViewById(R.id.ifBarberCheckbox);
    }
    public void sampleLogin(){
        Intent intent;
        if (checkBox.isChecked()) {
            intent = new Intent(this, BarberHomeSrceenActivity.class);

        } else {
            intent = new Intent(this, ApprenticeHomeScreenActivity.class);
        }
        startActivity(intent);
    }
    private void animateCheckBox(int visibility, float transition) {
        validationCodeTxt.setVisibility(visibility);
        ObjectAnimator animation = ObjectAnimator.ofFloat(findViewById(R.id.login_btn), "translationY", transition);
        animation.setDuration(100);
        animation.start();
    }

}
