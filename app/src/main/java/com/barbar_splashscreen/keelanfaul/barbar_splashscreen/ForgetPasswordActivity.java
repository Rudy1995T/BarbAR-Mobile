package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ForgetPasswordActivity extends AppCompatActivity {

    private EditText userNameTxt;
    private EditText emailTxt;
    private Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);


        sendBtn = findViewById(R.id.sendEmail_btn);
        userNameTxt = findViewById(R.id.forgotPassUsername);
        emailTxt = findViewById(R.id.forgotPassEmail);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest(userNameTxt.getText().toString(),emailTxt.getText().toString());
            }
        });



    }

    public void sendRequest(String username, String email) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sending Email...");
        progressDialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final String URL = "https://barbarservice.azurewebsites.net/api/user/sendEmail";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("userName", username);
        params.put("email", email);

        JsonObjectRequest req = new JsonObjectRequest(URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();

                        try {
                            if(response.getString("message") == "failed"){

                            }else {
                                Intent intent;
                                intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
                                Toast.makeText(ForgetPasswordActivity.this, "Email sent - Don't forget to check spam folder",Toast.LENGTH_SHORT).show();
                                startActivity(intent);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(req);
    }
}
