package com.example.fooddelary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Notifi_Adapter extends RecyclerView.Adapter<Notifi_Adapter.ViewHolder> {
    private Context context;
    List<Notificaton_Model> list;
    public Notifi_Adapter(Context context,List<Notificaton_Model> list)
    {
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public Notifi_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notificatin, parent, false);
        return new Notifi_Adapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull Notifi_Adapter.ViewHolder holder, int position) {
        Notificaton_Model model=list.get(position);
        holder.t1.setText(model.getNoti());
        holder.t2.setText(model.getOrder_date());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView t1,t2;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            t1=itemView.findViewById(R.id.notit2);
            t2=itemView.findViewById(R.id.notitime);
        }
}
}
