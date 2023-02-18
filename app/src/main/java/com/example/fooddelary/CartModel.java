package com.example.fooddelary;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CartModel {
    private String name;
    private  String cost;
    private String quantity;
    private String img;
    private String order_time;
    private String order_date;
    public CartModel(String name,String cost, String quantity,String img,String order_time,String order_date) {
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.img=img;
        this.order_time=order_time;
        this.order_date=order_date;
    }

    public String getOrder_time() {
        return order_time;}
    public void setOrder_time(String order_time) {
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        String Date= sdf.format(new Date());
        this.order_time = Date;}

        public String getOrder_date() {return order_date;}
         public void setOrder_date(String order_date) {
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        String Date=sdf.format(new Date());
        this.order_date = Date;}

        public String getImg() {
        return img;
    }
        public void setImg(String img) {
        this.img = img;
    }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        public String getCost() {return cost;}
        public void setCost(String cost) {
            this.cost = cost;
        }

        public String getQuantity() {
            return quantity;
        }
        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }


    }


