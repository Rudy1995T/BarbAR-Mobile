package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class FeedBackActivity extends AppCompatActivity {
    private RatingBar mRatingBar;
    private String haircutRating;
    private String trainee_id;
    private String session_id;
    private EditText haircutComments;
    private Button sendFeedbackButton;
    SessionManager sessionManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        session_id =  getIntent().getStringExtra("session_id");
        HashMap<String,String> user = sessionManager.getUserDetails();
        trainee_id = user.get(sessionManager.USER_ID);
        mRatingBar = findViewById(R.id.ratingBar);
        haircutComments = findViewById(R.id.feedbackComments);
        sendFeedbackButton = findViewById(R.id.sendFeedbackButton);



        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        haircutRating = "1";
                        break;
                    case 2:
                        haircutRating = "2";
                        break;
                    case 3:
                        haircutRating = "3";
                        break;
                    case 4:
                        haircutRating = "4";
                        break;
                    case 5:
                        haircutRating = "5";
                        break;
                    default:
                        haircutRating = "0";
                }
            }
        });

        sendFeedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comments = haircutComments.getText().toString();
              //  Log.d("FeedbackActivity LOG: ", "Comments = " + comments +
                   //     ", Session_id = " + session_id + ",Rating = " + haircutRating + ",trainee_id = " +trainee_id);
                sendRequest(session_id,trainee_id,haircutRating,comments);

            }
        });

    }

    public void sendRequest(String session_id, String trainee_id, String rating, String comments) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sending Feedback");
        progressDialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final String URL = "https://barbarservice.azurewebsites.net/api/trainee/selfAssessment";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("trainee_id", trainee_id);
        params.put("session_id", session_id);
        params.put("self_assessment", comments);
        params.put("rating", rating);

        JsonObjectRequest req = new JsonObjectRequest(URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();

                        try {
                            if(response.getString("message").equalsIgnoreCase("Failed")){
                                Toast.makeText(FeedBackActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(FeedBackActivity.this, "Feedback Sent", Toast.LENGTH_SHORT).show();
                                Intent intent;
                                intent = new Intent(FeedBackActivity.this, ApprenticeHomeScreenActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(FeedBackActivity.this, "Error :" + e.toString(), Toast.LENGTH_SHORT).show();
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
