package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TraineeMain extends AppCompatActivity {


    private final String TAG = "TraineeMain";



    private List<HaircutOption> options = getOptions();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trainee_main);

        initRecyclerView();
    }

    private List<HaircutOption> getOptions() {
        HaircutOption option1 = new HaircutOption("http://prettytalkblog.com/wp-content/uploads/2018/11/mens-high-fade-haircut-archives-e280a2-inspectioncompany.jpg", "High Fade");
        HaircutOption option2 = new HaircutOption("https://www.menshairstylestoday.com/wp-content/uploads/2017/07/Medium-Fade-Quiff.jpg", "Mid Fade");
        HaircutOption option3 = new HaircutOption("https://i.pinimg.com/736x/6d/26/a2/6d26a2f7d0d99cb52d41ce9b95e800fe.jpg", "Low Fade");

        List<HaircutOption> options = new ArrayList<>();
        options.add(option1);
        options.add(option2);
        options.add(option3);

        return options;
    }

    private void initRecyclerView() {
        Log.d(TAG, "Initialising Recycler View");

        RecyclerView recyclerView = findViewById(R.id.hairstyle_recycler);
        HaircutAdapter haircutAdapter = new HaircutAdapter(options, this);
        recyclerView.setAdapter(haircutAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

}
