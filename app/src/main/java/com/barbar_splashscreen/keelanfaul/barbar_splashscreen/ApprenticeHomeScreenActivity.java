package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApprenticeHomeScreenActivity extends AppCompatActivity {

    private RecyclerView haircutsRecyclerView;
    private HaircutAdapter haircutsAdapter;
    SessionManager sessionManager;
    private TextView logoutButton;

    private final String TAG = ApprenticeHomeScreenActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apprentice_home_screen);

        logoutButton = findViewById(R.id.logout_button);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetails();

        haircutsRecyclerView = findViewById(R.id.recyclerViewHaircuts);
        haircutsRecyclerView.setHasFixedSize(true);
        haircutsRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));

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
                switch (menuItem.getItemId()) {
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
                haircutsAdapter = new HaircutAdapter(haircuts, getApplicationContext());
                haircutsRecyclerView.setAdapter(haircutsAdapter);
            } catch (JSONException e) {
                Log.e(TAG, e.toString(), e);
            }
        }, error -> Log.d(TAG, error.toString()));


        requestQueue.add(jsonObjectRequest);
    }
}
