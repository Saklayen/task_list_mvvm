package com.example.tasklistmvvm.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tasklistmvvm.Model.Scorer;
import com.example.tasklistmvvm.R;

import java.util.ArrayList;
import java.util.Scanner;

public class LeaderAdapter extends RecyclerView.Adapter<LeaderAdapter.ViewHolder>{

    ArrayList<Scorer>dataList;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(dataList.get(position).getName());
        holder.score.setText(dataList.get(position).getScore());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public void setDataList(ArrayList<Scorer> dataList){
        this.dataList = dataList;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,score;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.title);
            score = itemView.findViewById(R.id.priority);
        }
    }
}
