package com.example.fooddelary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyorderAdapter extends RecyclerView.Adapter<MyorderAdapter.ViewHolder> {
private Context context;
private List<MyorderDataModel> myorderDataModelList;
public MyorderAdapter(Context context,List<MyorderDataModel>myorderDataModelList)
{
    this.context=context;
    this.myorderDataModelList=myorderDataModelList;
}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myorder_card, parent, false);
        return new MyorderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    MyorderDataModel model=myorderDataModelList.get(position);
    ViewHolder viewHolder=(ViewHolder)holder;
        Glide.with(viewHolder.i1.getContext()).load(model.getImg()).into(viewHolder.i1);
        holder.t1.setText(model.getName());
        holder.t2.setText(model.getQuantity());
        holder.t3.setText(model.getCost());

    }

    @Override
    public int getItemCount() {
       return myorderDataModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
    TextView t1,t2,t3;
    ImageView i1;
        public ViewHolder(@NonNull View itemView){
            super((itemView));
            t1=itemView.findViewById(R.id.order_Name);
            t2=itemView.findViewById(R.id.addnumber);
            t3=itemView.findViewById(R.id.order_cost);
            i1=itemView.findViewById(R.id.orderimg);
        }

    }
}