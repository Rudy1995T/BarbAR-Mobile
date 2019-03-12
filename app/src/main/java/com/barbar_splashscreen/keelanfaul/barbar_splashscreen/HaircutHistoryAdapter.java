package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HaircutHistoryAdapter extends RecyclerView.Adapter<HaircutHistoryAdapter.ViewHolder>{

    private List<History> history;
    private Context context;

    public HaircutHistoryAdapter(List<History> history, Context context) {
        this.history = history;
        this.context = context;
    }

    @Override
    public HaircutHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.haircut_history_list_item, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int i) {
        final History haircuts = history.get(i);
        holder.textView.setText(haircuts.getHaircut_name());
        Picasso.get().load(haircuts.getHaircut_image_URL()).placeholder(R.drawable.userlogo).into(holder.circleImageView);

    }

    @Override
    public int getItemCount() {
        return history.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public CircleImageView circleImageView;
        public TextView textView;
        public RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.haircutHistoryName);
            circleImageView = itemView.findViewById((R.id.haircutPic));
            parentLayout = itemView.findViewById(R.id.parent_layout_haircut_history);
        }


    }
}