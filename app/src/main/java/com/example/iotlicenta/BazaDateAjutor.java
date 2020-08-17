package com.example.iotlicenta;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BazaDateAjutor extends SQLiteOpenHelper {
    public static final String REGISTERUSER = "registeruser";
    // public static final String DATABASE_NAME ="register.db";
    public static final String TABLE_NAME = REGISTERUSER;
    public static final String ID = "ID";
    public static final String COL_1 = ID;
    public static final String USERNAME = "username";
    public static final String COL_2 = USERNAME;
    public static final String PASSWORD = "password";
    public static final String COL_3 = PASSWORD;

    public BazaDateAjutor(@Nullable Context context) {
        super(context,"NUME BAZA DATE", null,  1);

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + REGISTERUSER + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USERNAME + " TEXT, " + PASSWORD + " TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);


    }

    public long addUser(String user,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, user);
        contentValues.put(PASSWORD, password);
        long res =db.insert(REGISTERUSER, null, contentValues);
        db.close();
        return res;

    }

    public boolean checkUser(String username, String password){
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = {username,password };
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return true;
        else
            return false;
    }

//



}