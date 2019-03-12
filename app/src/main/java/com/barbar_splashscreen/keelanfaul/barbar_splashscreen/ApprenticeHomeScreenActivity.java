package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApprenticeHomeScreenActivity extends AppCompatActivity {

    private RecyclerView haircutsRecyclerView;
    private HaircutAdapter haircutsAdapter;
    private List<Haircuts> haircuts;
    SessionManager sessionManager;
    private TextView logoutButton;
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apprentice_home_screen);

        logoutButton = findViewById(R.id.logout_button);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String,String> user = sessionManager.getUserDetails();

        haircutsRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewHaircuts);
        haircutsRecyclerView.setHasFixedSize(true);
        haircutsRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        haircuts = new ArrayList<>();
        addSampleHaircutsToRecyclerView();

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavBar);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ic_haircuts:
                        Intent intent1 = new Intent(ApprenticeHomeScreenActivity.this, ApprenticeHomeScreenActivity.class);
                        startActivity(intent1);
                        finish();

                        break;
                    case R.id.ic_profile:
                        Intent intent2 = new Intent(ApprenticeHomeScreenActivity.this, ApprenticeProfileActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });
    }
    private void addSampleHaircutsToRecyclerView(){
        Haircuts haircut1 = new Haircuts(1, "High Fade", "lorem Ipsum,Lorem Ipsum", "https://i.pinimg.com/originals/42/ec/a7/42eca7539638b6543fdc0740b628e1ea.jpg");
        Haircuts haircut2 = new Haircuts(2, "Medium Fade", "lorem Ipsum,Lorem Ipsum", "https://www.styleinterest.com/wp-content/uploads/2018/06/85110618-mid-fade-haircuts-.jpg");

        haircuts.add(haircut1);
        haircuts.add(haircut2);

        haircutsAdapter = new HaircutAdapter(haircuts, getApplicationContext());
        haircutsRecyclerView.setAdapter(haircutsAdapter);

    }
}
