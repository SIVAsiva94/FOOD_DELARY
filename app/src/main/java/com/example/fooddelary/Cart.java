package com.example.fooddelary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {
    private DBHelper1 DB1;
    Button order;
    int total1=0;
int total=0;
    int c=20;
    int d=50;
    String Time,Date;
    DatabaseReference databaseReference;
    public static final String Database_path="Myorder";
    TextView name,d_fee,tip,totalfull;
    private ArrayList<CartModel> cartholder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
         name=findViewById(R.id.cart_sum_amount);
        totalfull=findViewById(R.id.cart_total);
        order = findViewById(R.id.order);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference= FirebaseDatabase.getInstance().getReference(Database_path);
               for(CartModel cartModel:cartholder){
                  cartModel.setOrder_time(Time);
                   cartModel.setOrder_date(Date);
                   databaseReference.push().setValue(cartModel);
               }
                Toast.makeText(Cart.this, "DATA Inserted", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Cart.this, paymet_metod.class);
                startActivity(i);
            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.cart);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.wishlist:
                        startActivity(new Intent(getApplicationContext(), Favorites.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), homepage.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.cart:
                        startActivity(new Intent(getApplicationContext(), Cart.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), Account.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
        cartholder = new ArrayList<>();
        DB1 = new DBHelper1(Cart.this);
        cartholder = DB1.getdata1();
        RecyclerView recyclerView1 = findViewById(R.id.cart_rv);
        CartAdapter1 adapter1 = new CartAdapter1(this, cartholder);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView1.setLayoutManager(linearLayoutManager);
        recyclerView1.setAdapter(adapter1);
        d_fee=findViewById(R.id.cart_delivery_fee);
        tip=findViewById(R.id.cart_tips);
        totalfull=findViewById(R.id.cart_total);
    }
    private class CartAdapter1 extends RecyclerView.Adapter<CartAdapter1.ViewHolder>{
        private Context context;
        private ArrayList<CartModel> cartholder;
        public CartAdapter1(Context context, ArrayList<CartModel> cartholder) {
            this.context = context;
            this.cartholder = cartholder;
        }
        @Override
        public CartAdapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_card, parent, false);
            return new CartAdapter1.ViewHolder(view);
        }
        @Override
        public void onBindViewHolder(CartAdapter1.ViewHolder holder, int position) {
            CartModel model = cartholder.get(position);
            ViewHolder viewHolder=(ViewHolder)holder;
            Glide.with(viewHolder.img1.getContext()).load(model.getImg()).into(viewHolder.img1);
            holder.textView.setText(model.getName());
            holder.textView1.setText(model.getCost());
            holder.textView3.setText(model.getQuantity());
          int  oneitem=Integer.valueOf(model.getCost())*Integer.valueOf(model.getQuantity());
            total= total+oneitem;
            total1=total+c+d;
            totalfull.setText(String.valueOf(total1));
            name.setText(String.valueOf(total));
            holder.minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int a=Integer.valueOf(model.getQuantity());
                    total=0;
                    total1=0;
                    if(a==1){

                        DBHelper1 helper = new DBHelper1(context);
                        helper.deletOrder(model.getName());
                        notifyDataSetChanged();

                    }
                    else {
                        a--;
                        model.setQuantity(String.valueOf(a));
                        DBHelper1 helper1 = new DBHelper1(Cart.this);
                        helper1.update(String.valueOf(model.getName()), String.valueOf(a));
                        notifyDataSetChanged();
                    }
                   }
            });
            holder.maxi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 int a=Integer.valueOf(model.getQuantity());
                 a++;
                 total1=0;
                 total=0;
                 model.setQuantity(String.valueOf(a));
                    DBHelper1 helper1=new DBHelper1(Cart.this);
                    helper1.update(String.valueOf(model.getName()),String.valueOf(a));
                    notifyDataSetChanged();

                   }
            });
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(cartholder.size()==1) {
                        DBHelper1 helper = new DBHelper1(context);
                        helper.deletOrder(model.getName());
                        total = 0;
                        total1 = 0;
                        cartholder.remove(holder.getAdapterPosition());
                        notifyDataSetChanged();
                        totalfull.setText(String.valueOf(total1));
                        name.setText(String.valueOf(total));
                    }
                    if(cartholder.size()>1){
                        DBHelper1 helper=new DBHelper1(context);
                        helper.deletOrder(model.getName());
                        total=0;
                        total1=0;
                        cartholder.remove(holder.getAdapterPosition());
                        notifyDataSetChanged();
                    }
                }
            });
        }
        @Override
        public int getItemCount() { return cartholder.size(); }
        public  class ViewHolder extends RecyclerView.ViewHolder {
            private TextView textView;
            private TextView textView1;
            private TextView textView3;
            private Button bn;
          private   View minus,maxi;
          private   View delete;
          Button btn;
   ImageView img1;
            public ViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.cart_name);
                textView1 = (TextView) itemView.findViewById(R.id.cart_cost);
                textView3 = (TextView) itemView.findViewById(R.id.cart_quantity);
                delete=(ImageView) itemView.findViewById(R.id.cart_delet);
                minus=itemView.findViewById(R.id.cart_minus);
                maxi=itemView.findViewById(R.id.cart_pluse);
                bn=itemView.findViewById(R.id.order);
                img1=itemView.findViewById(R.id.mycart_image);
            }
        }
    }
}