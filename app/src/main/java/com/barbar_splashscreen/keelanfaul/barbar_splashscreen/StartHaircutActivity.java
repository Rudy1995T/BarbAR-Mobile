package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Random;

public class StartHaircutActivity extends AppCompatActivity {

    SessionManager sessionManager;
    private TextView startHaircutButton;
    private TextView cancelHaircutButton;
    private String RandomCode;
    private String userName;
    private String haircut_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_haircut);

        startHaircutButton = findViewById(R.id.startHaircutTextView6);
        cancelHaircutButton = findViewById(R.id.startHaircutTextView5);


        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String,String> user = sessionManager.getUserDetails();


        userName = user.get(sessionManager.USERNAME);
        int haircut_id = getIntent().getIntExtra("haircut_id", 0);
        haircut_name = getIntent().getStringExtra("haircut_name");

        TextView haircutNameView = findViewById(R.id.startHaircutTextView3);
        haircutNameView.setText("Name - " + haircut_name);
        Random rand = new Random();
        RandomCode = String.format("%04d", rand.nextInt(10000));;


        startHaircutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest(userName,haircut_name,RandomCode);

            }
        });

        cancelHaircutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartHaircutActivity.this, ApprenticeHomeScreenActivity.class);
                startActivity(intent);
                finish();

            }
        });


    }
    public void sendRequest(String username,String haircut_name, String code) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        //haircut name not retreiving on API
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final String URL = "https://barbarservice.azurewebsites.net/api/user/startHaircutSession";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("haircut_name", haircut_name);
        params.put("code", code);

        JsonObjectRequest req = new JsonObjectRequest(URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();

                        try {
                            if(response.getString("message").equalsIgnoreCase("Failed")){

                            }else {
                                Intent intent;
                                intent = new Intent(StartHaircutActivity.this, ShowHaircutCodeActivity.class);
                                intent.putExtra("code", RandomCode);
                                intent.putExtra("username", userName);
                                startActivity(intent);
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(StartHaircutActivity.this, "Error :" + e.toString(), Toast.LENGTH_SHORT).show();
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
