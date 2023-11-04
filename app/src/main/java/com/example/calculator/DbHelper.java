package com.example.calculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "login.db";
    public DbHelper(Context context){
        super(context, "login.db",null,1);

    }
    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create Table users(id INTEGER primary key AUTOINCREMENT,username TEXT ,useremail TEXT,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        myDB.execSQL("drop table if exists users");
    }


    public boolean insertData(String username,String useremail,String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username",username);
        contentValues.put("useremail",useremail);
        contentValues.put("password",password);
        long result = myDB.insert("users",null, contentValues);
        if (result==-1) return false;
        else return true;
    }

    public boolean checkUser(String useremail){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where useremail = ? ", new String[]{useremail});
        if(cursor.getCount()>0) return true;
        else return false;
    }

    public boolean checkUsernamePasswordEmail(String useremail,String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where useremail = ? and password = ?"
                , new String[]{useremail,password});
        if(cursor.getCount()>0){

            return true;
        }
        else return false;
    }
}
