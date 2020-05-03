package com.example.tasklist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tasklist.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    private ArrayList list;
    private ArrayList deadlines;
    private LayoutInflater inflater;

    public RecyclerAdapter(ArrayList list, ArrayList deadlines, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.list = list;
        this.deadlines = deadlines;
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
        holder.task_deadline.setText(deadlines.get(position).toString());
        holder.button_done.setTag(position);
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        protected TextView task_title;
        protected TextView task_deadline;
        protected Button button_done;

        public MyViewHolder(View itemView) {
            super(itemView);
            task_title = itemView.findViewById(R.id.task_id);
            task_deadline = itemView.findViewById(R.id.deadline_id);
            button_done = itemView.findViewById(R.id.done_btn);

            button_done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (int) button_done.getTag();
                    SectionsPagerAdapter.removeItem(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, list.size());
                }
            });
        }
    }
}
