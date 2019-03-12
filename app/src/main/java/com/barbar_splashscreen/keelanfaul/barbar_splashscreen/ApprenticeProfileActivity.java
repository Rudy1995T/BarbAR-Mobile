package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class ApprenticeProfileActivity extends AppCompatActivity {

    private TextView name;
    private TextView email;
    private CircleImageView avatar;
    SessionManager sessionManager;
    private String userID;

    private RecyclerView historyRecyclerView;
    private RecyclerView.Adapter historyAdapter;
    private List<History> haircutHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apprentice_profile);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        avatar = findViewById(R.id.avatarPic);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String,String> user = sessionManager.getUserDetails();



        userID = user.get(sessionManager.USER_ID);
        historyRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewHistory);
        historyRecyclerView.setHasFixedSize(true);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        haircutHistory = new ArrayList<>();
        addHaircutHistoryToRecyclerView(userID);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavBar);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        String fName = user.get(sessionManager.FIRSTNAME);
        String sName = user.get(sessionManager.SURNAME);
        String uEmail = user.get(sessionManager.EMAIL);

        Log.d("USER_ID", "trainee_ID " + userID);

        name.setText(fName + " " + sName);
        email.setText(uEmail);
        Picasso.get().load("https://www.google.ie/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwjn7rfcx7zgAhUmShUIHUm7CnoQjRx6BAgBEAU&url=https%3A%2F%2Fpngtree.com%2Ffree-icon%2Fandroid-contact_1095402&psig=AOvVaw28unfxFRtQFVJLyGlLKsSL&ust=1550279570835472").placeholder(R.drawable.userlogo).into(avatar);




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
    private void addHaircutHistoryToRecyclerView(String id){
       // getHistory(id);
        History haircut = new History(5, "High Fade", "https://www.menshairstyletrends.com/wp-content/uploads/2017/12/ambarberia-high-fade-haircuts-textured-quiff-cool-mens-hair-e1515083287963.jpg", "00:30:00");
        History haircut2 = new History(5, "Medium Fade", "https://www.menshairstylesnow.com/wp-content/uploads/2017/05/Low-Bald-Fade-with-Slicked-Back-Hair-and-Beard.jpg", "00:45:00");

        haircutHistory.add(haircut);
        haircutHistory.add(haircut2);
        historyAdapter = new HaircutHistoryAdapter(haircutHistory, getApplicationContext());
        historyRecyclerView.setAdapter(historyAdapter);

    }

//    public void getHistory(String id) {
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        final String URL = "https://barbarservice.azurewebsites.net/api/Trainee/HaircutInfo";
//        HashMap<String, String> params = new HashMap<String, String>();
//        params.put("id", id);
//
//        JsonObjectRequest req = new JsonObjectRequest(URL, new JSONObject(params),
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//
//                                JSONObject res = response.getJSONObject("data");
////                                String sessionID = res.getString("session_id");
////                                String time_taken = res.getString("time_taken");
////                                History haircut = new History(Integer.parseInt(sessionID), "High Fade", "https://www.menshairstyletrends.com/wp-content/uploads/2017/12/ambarberia-high-fade-haircuts-textured-quiff-cool-mens-hair-e1515083287963.jpg", time_taken);
////                                haircutHistory.add(haircut);
//
//                            Log.d("Response:", response.toString());
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        requestQueue.add(req);
//    }
}
