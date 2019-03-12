package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.content.Intent;
import android.view.MenuItem;
import android.support.design.widget.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;


public class BarberStudentsActivity extends AppCompatActivity {

    private RecyclerView studentsRecyclerView;
    private RecyclerView.Adapter studentsAdapter;
    private List<Students> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber_students);

        studentsRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewStudents);
        studentsRecyclerView.setHasFixedSize(true);
        studentsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        students = new ArrayList<>();
        addSampleStudentsToRecyclerView();



        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavBar);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ic_haircuts:
                        Intent intent1 = new Intent(BarberStudentsActivity.this, BarberHomeScreenActivity.class);
                        startActivity(intent1);
                        finish();

                        break;
                    case R.id.ic_students:
                        Intent intent2 = new Intent(BarberStudentsActivity.this, BarberStudentsActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });
    }
    private void addSampleStudentsToRecyclerView(){
        Students student1 = new Students(1,1,"John","Doe","https://image.tmdb.org/t/p/w185/518jdIQHCZmYqIcNCaqbZuDRheV.jpg");
        Students student2 = new Students(2,1,"James","Taylor","https://i.imgur.com/tGbaZCY.jpg");

        students.add(student1);
        students.add(student2);

        studentsAdapter = new StudentAdapter(students, getApplicationContext());
        studentsRecyclerView.setAdapter(studentsAdapter);

    }
}
