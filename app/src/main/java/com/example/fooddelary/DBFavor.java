package com.example.fooddelary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBFavor extends SQLiteOpenHelper {
    public DBFavor(Context context) {
        super(context, "Favor.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase DB1) {
        DB1.execSQL("create Table Favordetails(name TEXT Primary Key,type TEXT,cost TEXT,img TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB1, int i, int ii) {
        DB1.execSQL("drop Table if exists Favordetails");
    }
    public Boolean insertcartdata(String name, String type, String cost,String img) {
        SQLiteDatabase DB1 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("type", type);
        contentValues.put("cost", cost);
        contentValues.put("img",img);
        long result = DB1.insert("Favordetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public ArrayList<FavorModel> getdata1(){
        SQLiteDatabase DB1=this.getReadableDatabase();
        Cursor cursor=DB1.rawQuery("Select*from Favordetails",null);
        ArrayList<FavorModel> favorholder=new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                favorholder.add(new FavorModel(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)));

            }while (cursor.moveToNext());
        }cursor.close();
        return favorholder;

    }

    public void deletOrder(String name) {
        SQLiteDatabase database=this.getWritableDatabase();
        database.delete("Favordetails","name=?",new String[]{name});
        database.close();
    }
}
