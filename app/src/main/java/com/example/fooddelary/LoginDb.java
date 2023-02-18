package com.example.fooddelary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class LoginDb  extends SQLiteOpenHelper {
    public LoginDb(Context context) {
        super(context, "Login.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table Logindetails(no TEXT Primary Key,mail TEXT ,pass TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists Logindetails");
    }
    public Boolean insertdata(String no,String mail,String pass){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("no",no);
        contentValues.put("mail",mail);
        contentValues.put("pass",pass);
        long result=DB.insert("Logindetails",null,contentValues);
        if(result==-1) {
            return false;
        }else {
            return  true;
        }
    }
    public Boolean checkusernames(String no)
    {
        SQLiteDatabase MyDB= this.getWritableDatabase();
        Cursor cursor= MyDB.rawQuery("select *from Logindetails where no=?",new String[] {no});
        return cursor.getCount() > 0;
    }
    public  void delete(){
        SQLiteDatabase database=this.getWritableDatabase();
       database.delete("Logindetails",null,null);
    }
}
