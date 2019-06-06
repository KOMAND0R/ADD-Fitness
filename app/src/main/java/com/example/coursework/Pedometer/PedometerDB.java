package com.example.coursework.Pedometer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PedometerDB  extends SQLiteOpenHelper
{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "pedometerDB";
    public static final String TABLE_PEDOMETER = "pedometer";

    public static final String KEY_ID = "_id";
    public static final String KEY_STEPS = "steps";

    public PedometerDB(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " + TABLE_PEDOMETER + "(" + KEY_ID
                + " integer primary key," + KEY_STEPS + " text" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists " + TABLE_PEDOMETER);
        onCreate(db);
    }
}
