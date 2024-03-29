package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class HaircutAdapter extends RecyclerView.Adapter<HaircutAdapter.ViewHolder>{

    private List<Haircut> haircuts;
    private Context context;
    Dialog myDialog;
    boolean isUp;


    public HaircutAdapter(List<Haircut> haircuts, Context context) {
        this.haircuts = haircuts;
        this.context = context;
    }

    @Override
    public HaircutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, final int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.haircuts_columnlist_item, parent,false);
        final HaircutAdapter.ViewHolder vHolder = new HaircutAdapter.ViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int i) {
        final Haircut haircut = haircuts.get(i);
        holder.textView.setText(haircut.getHaircutName());
        Picasso.get().load(haircut.getHaircutImageURL()).placeholder(R.drawable.userlogo).into(holder.imageView);
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StartHaircutActivity.class);
                intent.putExtra("haircut_id", haircut.getHaircut_id());
                intent.putExtra("haircut_name", haircut.getHaircutName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return haircuts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView textView;
        public RelativeLayout parentLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.haircutName);
            imageView = (ImageView) itemView.findViewById((R.id.haircutImage));
            parentLayout = itemView.findViewById(R.id.parent_layout1);

        }


    }



}