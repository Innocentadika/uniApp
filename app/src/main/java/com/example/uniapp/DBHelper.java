package com.example.uniapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "login.db";

    public DBHelper(Context context) {
        super(context, "login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DBNAME) {
        DBNAME.execSQL("create table users(username text, email text primary key, password text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DBNAME, int i, int i1) {
        DBNAME.execSQL("drop table if exists users");

    }

    public boolean insertdata(String username, String email, String password) {
        SQLiteDatabase DBNAME = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = DBNAME.insert("users", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public boolean checkusername(String username) {
        SQLiteDatabase DBNAME = this.getWritableDatabase();
        Cursor cursor = DBNAME.rawQuery("select * from users where username =?", new String[] {username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public boolean checkemail(String email) {
        SQLiteDatabase DBNAME = this.getWritableDatabase();
        Cursor cursor = DBNAME.rawQuery("select * from users where email =?", new String[] {email});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }

    public boolean checkusernamepassword(String username, String password){
        SQLiteDatabase DBNAME=this.getWritableDatabase();
        Cursor cursor = DBNAME.rawQuery( "select * from users where username =? and password =?", new String[] {username, password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    }

