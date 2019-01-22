package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class haircutAdapter2 extends RecyclerView.Adapter<haircutAdapter2.ViewHolder>{

    private List<Haircuts> haircuts;
    private Context context;

    public haircutAdapter2(List<Haircuts> haircuts, Context context) {
        this.haircuts = haircuts;
        this.context = context;
    }

    @Override
    public haircutAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.haircuts_list_item, parent,false);
        return new haircutAdapter2.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int i) {
        final Haircuts haircut = haircuts.get(i);
        holder.textView.setText(haircut.getHaircutName());
       Picasso.get().load(haircut.getHaircutImageURL()).placeholder(R.drawable.userlogo).into(holder.imageView);

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
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }


    }
}