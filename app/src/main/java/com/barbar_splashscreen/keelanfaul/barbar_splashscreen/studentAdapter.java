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

public class studentAdapter extends RecyclerView.Adapter<studentAdapter.ViewHolder>{

    private List<Students> students;
    private Context context;

    public studentAdapter(List<Students> students, Context context) {
        this.students = students;
        this.context = context;
    }

    @Override
    public studentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.students_list_item, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int i) {
        final Students student = students.get(i);
        holder.textView.setText(student.getFirst_name() + " " + student.getSurname());
       Picasso.get().load(student.getProfilePicImgURL()).placeholder(R.drawable.userlogo).into(holder.circleImageView);

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public CircleImageView circleImageView;
        public TextView textView;
        public RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.studentName);
            circleImageView = (CircleImageView) itemView.findViewById((R.id.studentProfilePic));
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }


    }
}