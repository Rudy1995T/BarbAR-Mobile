package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HaircutAdapter extends RecyclerView.Adapter<HaircutAdapter.HaircutViewHolder>{

    private static final String TAG = "HaircutAdapter";
    private List<HaircutOption> options;
    private Context context;

    public HaircutAdapter(List<HaircutOption> options, Context context) {
        this.options = options;
        this.context = context;
    }

    @NonNull
    @Override
    public HaircutViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hairstyle_selection, viewGroup, false);
        return new HaircutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HaircutViewHolder haircutViewHolder, int i) {
        Log.d(TAG, "onBinderViewHolder: Call (" + i + ")");

        HaircutOption option = options.get(i);
        haircutViewHolder.haircutDescription.setText(option.getHaircutDescription());
        Picasso.get().load(option.getUrl()).fit().into(haircutViewHolder.haircutImage);

        haircutViewHolder.haircutImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    @Override
    public int getItemCount() { return options.size(); }

    public static class HaircutViewHolder extends RecyclerView.ViewHolder {
        ImageView haircutImage;
        TextView haircutDescription;
        public HaircutViewHolder(View view) {
            super(view);

            haircutImage = view.findViewById(R.id.hairstyle_image);
            haircutDescription = view.findViewById(R.id.hairstyle_description);
        }
    }
}
