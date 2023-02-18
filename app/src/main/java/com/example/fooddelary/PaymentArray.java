package com.example.fooddelary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.PrimitiveIterator;

public class PaymentArray {
 private    String card_holder_name;
  private   String  card_number;
   private String  card_expiry;
   private  String  noti;

    public String getNoti() {
        return noti;
    }

    public void setNoti(String noti) {
        String a="Your Order is Conformed";
        this.noti = a;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        String Date= sdf.format(new Date());
        this.order_date = Date;
    }

    private  String order_date;

    public PaymentArray() {
    }

    public String getCard_holder_name() {
        return card_holder_name;
    }

    public void setCard_holder_name(String card_holder_name) {
        this.card_holder_name = card_holder_name;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getCard_expiry() {
        return card_expiry;
    }

    public void setCard_expiry(String card_expiry) {
        this.card_expiry = card_expiry;
    }
}
