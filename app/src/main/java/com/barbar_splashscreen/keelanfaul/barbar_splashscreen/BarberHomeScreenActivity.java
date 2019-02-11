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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class BarberHomeScreenActivity extends AppCompatActivity {

    private RecyclerView haircutsRecyclerView;
    private HaircutAdapter2 haircutsAdapter2;

    private final String TAG = BarberHomeScreenActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber_home_srceen);

        haircutsRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewHaircuts);
        haircutsRecyclerView.setHasFixedSize(true);
        haircutsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        addSampleHaircutsToRecyclerView();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavBar);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

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
        // MOVE LATER WHEN API DONE
        List<Haircut> haircuts = new ArrayList<>();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        final String URL = getString(R.string.sign_up_url_body) + "haircut/haircutInformation";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL,
                null, response -> {

            try {
                JSONArray array = response.getJSONArray("data");
                for(int i = 0; i < array.length(); i++) {
                    JSONObject haircut = array.getJSONObject(i);

                    int id = haircut.getInt("object_data_id");

                    String haircutName = haircut.getString("haircut_name");
                    String url = haircut.getString("haircut_url");
                    String description = haircut.getString("haircut_description");

                    haircuts.add(new Haircut(id, haircutName, description, url));
                }
                haircutsAdapter2 = new HaircutAdapter2(haircuts, getApplicationContext());
                haircutsRecyclerView.setAdapter(haircutsAdapter2);
            } catch (JSONException e) {
                Log.e(TAG, e.toString(), e);
            }
        }, error -> Log.d(TAG, error.toString()));

        requestQueue.add(jsonObjectRequest);
    }
}
