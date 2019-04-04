package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class ShowFeedbackActivity extends AppCompatActivity {
    private RatingBar mRatingBar;
    private TextView haircutName;
    private TextView barberComments;
    private TextView apprenticeComments;
    private String haircutRating;
    private EditText haircutComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_feedback);
        haircutRating = "5";
        mRatingBar = findViewById(R.id.ratingBar);
        haircutName = findViewById(R.id.feedbackHaircutName);
        barberComments = findViewById(R.id.barberComments);
        apprenticeComments = findViewById(R.id.apprenticeComments);
        mRatingBar.setRating(Integer.parseInt(haircutRating));
        apprenticeComments.setText("I feel this haircut went really, i noticed that i am improving the actual blending of the fade. I seem to be getting faster which is good to");
        barberComments.setText(" ");
        haircutName.setText(getIntent().getStringExtra("haircut_name"));
    }
}
