package com.example.fooddelary;

import android.content.Context;
import android.provider.ContactsContract;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Review_Adapter  extends RecyclerView.Adapter<Review_Adapter.ViewHolder> {
    Context context;
    List<Reviewmodel> reviewmodels;
    public Review_Adapter(Context context,List<Reviewmodel> reviewmodels){
        this.reviewmodels=reviewmodels;
        this.context=context;
    }
    @NonNull
    @Override
    public Review_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.reviews,parent,false);
      ViewHolder viewHolder=new ViewHolder(view);
      return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Reviewmodel model=reviewmodels.get(position);
        holder.review.setText(model.getReview());
        holder.time.setText(model.getReviewtime());

    }

    @Override
    public int getItemCount() {
        return  reviewmodels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView review,time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            review=itemView.findViewById(R.id.description_review);
            time=itemView.findViewById(R.id.review_time);
        }
    }
}
