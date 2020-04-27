package com.example.tasklist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    private ArrayList list;
    private LayoutInflater inflater;

    public RecyclerAdapter(ArrayList list, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.task_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.task_title.setText(list.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        protected TextView task_title;

        public MyViewHolder(View itemView) {
            super(itemView);
            task_title = itemView.findViewById(R.id.task_id);
        }
    }
}
