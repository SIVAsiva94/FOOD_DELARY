package com.example.fooddelary;

import static android.media.CamcorderProfile.get;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class personAdapter extends RecyclerView.Adapter<personAdapter.personsViewholder> {
    private Context context;
    List<person> dathoder;
    public personAdapter(Context context,List<person> TempList)
    {
       this.dathoder=TempList;
        this.context = context;
    }


    @Override
  public void onBindViewHolder(personsViewholder holder,int position )
    {
       person model=dathoder.get(position);
        personsViewholder personsViewholder=(personsViewholder)holder;
        Glide.with(personsViewholder.image.getContext()).load(model.getImg()).into(personsViewholder.image);
        holder.firstname.setText(model.getFoodname());
        holder.lastname.setText(model.getFoodtype());
        holder.age.setText(model.getPlace());
        holder.textView1.setText(model.getCost());
        holder.textView2.setText(model.getRating());
        holder.favor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBFavor db=new DBFavor(context);
              String nameTXT=model.getFoodname();
              String typeTXT=model.getFoodtype();
              String costTXT=model.getCost();
              String imgTXT= model.getImg();

                Boolean checkinsertdata=db.insertcartdata(nameTXT,typeTXT,costTXT,imgTXT);
                if(checkinsertdata==false )
                {
                    Toast.makeText(context,"Item Added to Favorets",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context,"  Item Not Added",Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,Detail.class);
                i.putExtra("Name",dathoder.get(holder.getAdapterPosition()).getFoodname());
                i.putExtra("Descrip",dathoder.get(holder.getAdapterPosition()).getDescription());
                i.putExtra("Img",dathoder.get(holder.getAdapterPosition()).getImg());
                i.putExtra("Cost",dathoder.get(holder.getAdapterPosition()).getCost());
                i.putExtra("Rating",dathoder.get(holder.getAdapterPosition()).getRating());
                context.startActivity(i);
            }
        });
    }
    @Override
    public int getItemCount(){
        return dathoder.size();
    }


    @Override
    public personsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.allfoodcard, parent, false);
        return new personAdapter.personsViewholder(view);
    }
    public class personsViewholder extends RecyclerView.ViewHolder {
        TextView firstname, lastname, age,textView1,textView2;
        ImageView image;
        CardView mCardView;
        ImageButton favor;
        public personsViewholder(@NonNull View itemView)
        {
            super(itemView);
            textView1=itemView.findViewById(R.id.cardcost);
            textView2=itemView.findViewById(R.id.cardrating);
            firstname = itemView.findViewById(R.id.cardname);
            lastname = itemView.findViewById(R.id.cardtype);
            age = itemView.findViewById(R.id.cardlocation);
            image=itemView.findViewById(R.id.cardimage);
            mCardView=itemView.findViewById(R.id.cardview);
            favor=itemView.findViewById(R.id.heart1);
        }
    }
}

