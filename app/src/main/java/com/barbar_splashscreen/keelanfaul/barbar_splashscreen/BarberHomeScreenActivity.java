package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.content.Intent;
import android.view.MenuItem;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;



public class BarberHomeScreenActivity extends AppCompatActivity {

    private RecyclerView haircutsRecyclerView;
    private HaircutAdapter2 haircutsAdapter2;
    private List<Haircuts> haircuts;
    private TextView logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber_home_srceen);
        Bundle bundle = getIntent().getExtras();

       // Log.d("BARBER BUNDLE", bundle.getString("sign_up_details"));
        logoutButton = findViewById(R.id.logout_button1);
        haircutsRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewHaircuts);
        haircutsRecyclerView.setHasFixedSize(true);
        haircutsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        haircuts = new ArrayList<>();
        addSampleHaircutsToRecyclerView();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavBar);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(BarberHomeScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.ic_haircuts:
                        Intent intent1 = new Intent(BarberHomeScreenActivity.this, MainActivity.class);
                        startActivity(intent1);
                        finish();
                        break;
                    case R.id.ic_students:
                        Intent intent2 = new Intent(BarberHomeScreenActivity.this, BarberStudentsActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });


    }

    private void addSampleHaircutsToRecyclerView() {
        Haircuts haircut1 = new Haircuts(1, "High Fade", "lorem Ipsum,Lorem Ipsum", "https://i.pinimg.com/originals/42/ec/a7/42eca7539638b6543fdc0740b628e1ea.jpg");
        Haircuts haircut2 = new Haircuts(2, "Medium Fade", "lorem Ipsum,Lorem Ipsum", "https://www.styleinterest.com/wp-content/uploads/2018/06/85110618-mid-fade-haircuts-.jpg");

        haircuts.add(haircut1);
        haircuts.add(haircut2);

        haircutsAdapter2 = new HaircutAdapter2(haircuts, getApplicationContext());
        haircutsRecyclerView.setAdapter(haircutsAdapter2);

    }
}
