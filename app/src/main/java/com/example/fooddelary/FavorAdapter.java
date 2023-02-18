package com.example.fooddelary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FavorAdapter extends RecyclerView.Adapter<FavorAdapter.ViewHolder> {
    private Context context;
    private ArrayList<FavorModel> favorModels;
    int count=0;
    public FavorAdapter(Context context,ArrayList<FavorModel> favorModels){
        this.context=context;
        this.favorModels=favorModels;
    }
    @Override
    public FavorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_card, parent, false);
        return new FavorAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull FavorAdapter.ViewHolder holder, int position) {
        FavorModel model=favorModels.get(position);
        ViewHolder viewHolder=(ViewHolder)holder;
        Glide.with(viewHolder.img1.getContext()).load(model.getImg()).into(viewHolder.img1);
        holder.name.setText(model.getName());
        holder.type.setText(model.getType());
        holder.cost.setText(model.getCost());
        holder.img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               count++;
               holder.quantity.setText(String.valueOf(count));
               notifyDataSetChanged();
            }
        });
        holder.img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Integer.valueOf(holder.quantity.getText().toString())==0) {
                    Toast.makeText(context, "Add the item count", Toast.LENGTH_SHORT).show();
                }
                else {
                 int count=(Integer.valueOf(holder.quantity.getText().toString()));
                 count--;
                 holder.quantity.setText(String.valueOf(count));
                 notifyDataSetChanged();
                }
            }
        });
        holder.img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBFavor dbFavor=new DBFavor(context);
                dbFavor.deletOrder(model.getName());
                favorModels.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper1 db=new DBHelper1(context);
                String p=String.valueOf(count);
                String nameTXT=model.getName();
                String costTXT=model.getCost();
                String quantityTXT= p;
                String imgTXT=model.getImg();
                Boolean checkinsertdata=db.insertcartdata(nameTXT,costTXT,quantityTXT,imgTXT);
                if(checkinsertdata==true)
                {
                    Toast.makeText(context,"Item Added to cart",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context,"  Already exists in cart",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return favorModels.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,type,cost,quantity;
        ImageView img1,img2,img3,img4,add;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.favName);
            type = (TextView) itemView.findViewById(R.id.favtype);
            cost = (TextView) itemView.findViewById(R.id.favcost);
            img1 = (ImageView) itemView.findViewById(R.id.favimg);
            add = itemView.findViewById(R.id.add);
            img2 = itemView.findViewById(R.id.heart1);
            quantity = itemView.findViewById(R.id.addnumber);
            img3 = itemView.findViewById(R.id.pluse);
            img4 = itemView.findViewById(R.id.minus);
        }
    }
}
