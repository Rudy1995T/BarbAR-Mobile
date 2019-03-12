package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Random;

public class ShowHaircutCodeActivity extends AppCompatActivity {
    private TextView haircutSessionCode;
    Handler handler = new Handler();
    private String RandomCode;
    private String sessionID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_haircut_code);
        RandomCode = getIntent().getExtras().getString("code");


        haircutSessionCode = findViewById(R.id.hCodeTextView1);
        haircutSessionCode.setText(RandomCode);
        handler.post(runnableCode);
       // haircutSessionCode.setText(num1);

    }


    // Define the code block to be executed
    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            getHaircutSessionID();
            checkHaircutSessionStatus(RandomCode);
            handler.postDelayed(runnableCode, 6000);
        }
    };

    public void checkHaircutSessionStatus(String code) {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final String URL = "https://barbarservice.azurewebsites.net/api/haircut/session/checkStatus";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("code", code);

        JsonObjectRequest req = new JsonObjectRequest( URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if(response.getString("data").equalsIgnoreCase("1")){
                                Intent intent;
                                intent = new Intent(ShowHaircutCodeActivity.this, FeedBackActivity.class);
                                intent.putExtra("session_id", sessionID);
                                handler.removeCallbacks(runnableCode);
                                startActivity(intent);
                                finish();
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
    public void getHaircutSessionID() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final String URL = "https://barbarservice.azurewebsites.net/api/haircut/getSessionID";

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            sessionID = response.getString("data");
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
