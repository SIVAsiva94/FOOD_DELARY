package com.example.fooddelary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyHolder> {
    ArrayList<Integer> datasource;
    Context context;


    public MyRvAdapter(ArrayList<Integer> datasource, Context context) {
        this.datasource = datasource;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.imageslide, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.image.setImageResource(datasource.get(position));
    }

    @Override
    public int getItemCount() {
        return datasource.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView image;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.banner);
        }
    }
}

