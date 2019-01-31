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
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText userNameTxt;
    private EditText passwordTxt;
    private EditText validationCodeTxt;
    private CheckBox checkBox;
    private Button loginBtn;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);
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
                sampleLogin(userName,password);

            }
        });



    }

    public void sendRequest(String username,String password) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final String URL = "https://barbarservice.azurewebsites.net/api/trainee/logIn";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("password", password);

        JsonObjectRequest req = new JsonObjectRequest(URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                                JSONObject user = response.getJSONObject("data");
                                        String userID = user.getString("id");
                                        String userName = user.getString("username");
                                        String uPassword = user.getString("password");
                                        String uFirstname = user.getString("firstname");
                                        String uSurname = user.getString("surname");
                                        String uEmail = user.getString("email");
                                        String avatarLink = user.getString("avatar");

                                        sessionManager.createSession(uFirstname,uSurname,uEmail);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "Error :" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(req);
    }

    private void initViews() {
        userNameTxt = findViewById(R.id.login_username);
        passwordTxt = findViewById(R.id.login_password);
        validationCodeTxt = findViewById(R.id.login_validation_code);
        checkBox = findViewById(R.id.ifBarberCheckbox);
    }
    public void sampleLogin(String userName, String password){

        Intent intent;
        if (checkBox.isChecked()) {
          intent = new Intent(this, BarberHomeSrceenActivity.class);

        } else {
            sendRequest(userName,password);
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
