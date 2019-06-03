package com.example.coursework.Food;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.coursework.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

import static java.lang.Float.valueOf;

public class FoodDiagramActivity extends AppCompatActivity implements View.OnClickListener
{

    private static final String TAG = "WeightDiagramActivity";

    private LineChart mChart;
    FoodDB dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_diagram);
        Button buttonClear = (Button) findViewById(R.id.clearCalories);

        mChart = (LineChart) findViewById(R.id.caloriesLineChart);

        //mChart.setOnChartGestureListener(WeightDiagramActivity.this);
        //mChart.setOnChartValueSelectedListener(WeightDiagramActivity.this);

        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();

        dbHelper = new FoodDB(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(FoodDB.TABLE_FOOD, null, null, null, null, null, null);

        if (cursor.moveToFirst())
        {
            int dateIndex = cursor.getColumnIndex(FoodDB.KEY_ID);
            int caloriesIndex = cursor.getColumnIndex(FoodDB.KEY_CALORIES);
            do {
                yValues.add(new Entry(valueOf(cursor.getInt(dateIndex)), valueOf(cursor.getFloat(caloriesIndex))));
            } while (cursor.moveToNext());
            LineDataSet set1 = new LineDataSet(yValues, "Динамика калорий");

            set1.setFillAlpha(110);
            set1.setColor(Color.RED);
            set1.setLineWidth(3f);
            set1.setValueTextSize(10f);

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            LineData data = new LineData(dataSets);

            mChart.setData(data);
        }
        cursor.close();

        buttonClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        switch (v.getId())
        {
            case R.id.clearCalories:
                database.delete(FoodDB.TABLE_FOOD, null, null);
                finish();
                break;
        }
    }
}
