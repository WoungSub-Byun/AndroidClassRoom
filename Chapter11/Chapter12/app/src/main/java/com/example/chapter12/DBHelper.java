package com.example.chapter12;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, "testdb", null, DATABASE_VERSION);
    }

    //앱이 처음 실행될때 한번만 호출되어 테이블 생성
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE test_table("+
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "title TEXT, "+
                "contents TEXT)";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
