package com.example.fooddelary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(name TEXT Primary Key,type TEXT,cost TEXT,location TEXT,rating TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
    }
    public Boolean insertuserdata(String name,String type,String cost,String location,String rating) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("type", type);
        contentValues.put("cost", cost);
        contentValues.put("location", location);
        contentValues.put("rating", rating);
        long result = DB.insert("Userdetails", null, contentValues);
        if (result == -1) {
            return false;

        } else {
            return true;
        }
    }
    public Cursor getdata()
    {
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor= DB.rawQuery("Select*from Userdetails",null);
        return cursor;
    }

}
