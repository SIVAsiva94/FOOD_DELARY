package com.example.fooddelary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper1 extends SQLiteOpenHelper {
    public DBHelper1(Context context) {
        super(context, "Cart.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase DB1) {
        DB1.execSQL("create Table Cartdetails(name TEXT Primary Key,cost TEXT,quantity TEXT,img TXT,order_time TXT,order_date TXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB1, int i, int ii) {
        DB1.execSQL("drop Table if exists Cartdetails");
    }
    public Boolean insertcartdata(String name, String cost, String quantity,String img) {
        SQLiteDatabase DB1 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("cost", cost);
        contentValues.put("quantity", quantity);
        contentValues.put("img",img);
        long result = DB1.insert("Cartdetails", null, contentValues);
        if (result == -1) {
            return false;

        } else {
            return true;
        }
    }
    public ArrayList<CartModel> getdata1(){
        SQLiteDatabase DB1=this.getReadableDatabase();
        Cursor cursor=DB1.rawQuery("Select*from Cartdetails",null);
        ArrayList<CartModel> cartholder=new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                cartholder.add(new CartModel(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                                cursor.getString(4),
                                cursor.getString(5)));
            }while (cursor.moveToNext());
        }cursor.close();
        return cartholder;
    }
    public void deletOrder(String name) {
        SQLiteDatabase database=this.getWritableDatabase();
        database.delete("Cartdetails","name=?",new String[]{name});
        database.close();
    }
    public void update(String name,String quantity) {
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("quantity", quantity);
        database.update("Cartdetails",contentValues,"name=?",new String[]{name});
        database.close();

    }
}