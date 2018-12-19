package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class SignUpActivity extends AppCompatActivity {

    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private Spinner countySpinner;
    private EditText address1;
    private EditText address2;
    private EditText city;
    private Button sign_up_btn1;
    private Button sign_up_btn2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        checkBox1 = (CheckBox)findViewById(R.id.ifApprenticeCheckbox);
        checkBox2 = (CheckBox)findViewById(R.id.ifMentorCheckbox);
        address1 = (EditText)findViewById(R.id.AddressLine1);
        address2 = (EditText)findViewById(R.id.AddressLine2);
        city = (EditText)findViewById(R.id.City);
        countySpinner = (Spinner)findViewById(R.id.county_spinner);
        sign_up_btn1 = (Button)findViewById(R.id.signup_btn1);
        sign_up_btn2 = (Button)findViewById(R.id.signup_btn2);




        ArrayAdapter<String> countyAdapter = new ArrayAdapter<String>(SignUpActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.county_arrays));
        countyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countySpinner.setAdapter(countyAdapter);


        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox1.isChecked()) {
                    checkBox2.setChecked(false);
                    address1.setVisibility(View.INVISIBLE);
                    address2.setVisibility(View.INVISIBLE);
                    city.setVisibility(View.INVISIBLE);
                    countySpinner.setVisibility(View.INVISIBLE);
                    sign_up_btn1.setVisibility(View.VISIBLE);
                    sign_up_btn2.setVisibility(View.INVISIBLE);


                }
            }
        });
        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox2.isChecked()) {
                    checkBox1.setChecked(false);
                    address1.setVisibility(View.VISIBLE);
                    address2.setVisibility(View.VISIBLE);
                    city.setVisibility(View.VISIBLE);
                    countySpinner.setVisibility(View.VISIBLE);
                    sign_up_btn1.setVisibility(View.INVISIBLE);
                    sign_up_btn2.setVisibility(View.VISIBLE);




                } else if(!checkBox2.isChecked()){
                    address1.setVisibility(View.INVISIBLE);
                    address2.setVisibility(View.INVISIBLE);
                    city.setVisibility(View.INVISIBLE);
                    countySpinner.setVisibility(View.INVISIBLE);
                    sign_up_btn1.setVisibility(View.VISIBLE);
                    sign_up_btn2.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
