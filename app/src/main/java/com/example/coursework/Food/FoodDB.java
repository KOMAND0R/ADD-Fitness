package com.example.coursework.Food;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FoodDB  extends SQLiteOpenHelper
{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "foodDB";
    public static final String TABLE_FOOD = "food";

    public static final String KEY_ID = "_id";
    public static final String KEY_CALORIES = "calories";
    public static final String KEY_PROTEINS = "proteins";
    public static final String KEY_FAT = "fats";
    public static final String KEY_CARBOHYDRATE = "carbohydrates";
    public static final String KEY_DATE = "date";

    public FoodDB(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " + TABLE_FOOD + "(" + KEY_ID
                + " integer primary key," + KEY_CALORIES + " text," + KEY_PROTEINS
                + " text," + KEY_FAT + " text," + KEY_CARBOHYDRATE + " text," + KEY_DATE + " text" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists " + TABLE_FOOD);
        onCreate(db);
    }
}
