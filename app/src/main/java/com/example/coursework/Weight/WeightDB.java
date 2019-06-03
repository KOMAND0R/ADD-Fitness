package com.example.coursework.Weight;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WeightDB  extends SQLiteOpenHelper
{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "weightDB";
    public static final String TABLE_WEIGHT = "weight";

    public static final String KEY_ID = "_id";
    public static final String KEY_WEIGHT = "weight";
    public static final String KEY_DATE = "date";

    public WeightDB(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " + TABLE_WEIGHT + "(" + KEY_ID
                + " integer primary key," + KEY_WEIGHT + " text," + KEY_DATE + " text" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists " + TABLE_WEIGHT);
        onCreate(db);
    }
}
