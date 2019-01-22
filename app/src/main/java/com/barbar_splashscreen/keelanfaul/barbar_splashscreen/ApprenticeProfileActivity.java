package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;



public class ApprenticeProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apprentice_profile);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavBar);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ic_haircuts:
                        Intent intent1 = new Intent(ApprenticeProfileActivity.this, ApprenticeHomeScreenActivity.class);
                        startActivity(intent1);
                        finish();

                        break;
                    case R.id.ic_profile:
                        Intent intent2 = new Intent(ApprenticeProfileActivity.this, ApprenticeProfileActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });
    }
}
