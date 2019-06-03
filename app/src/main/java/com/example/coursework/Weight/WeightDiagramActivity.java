package com.example.coursework.Weight;

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

public class WeightDiagramActivity extends AppCompatActivity implements View.OnClickListener
{

    private static final String TAG = "WeightDiagramActivity";

    private LineChart mChart;
    WeightDB dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_diagram);
        Button buttonClear = (Button) findViewById(R.id.clearWeight);

        mChart = (LineChart) findViewById(R.id.weightLineChart);

        //mChart.setOnChartGestureListener(WeightDiagramActivity.this);
        //mChart.setOnChartValueSelectedListener(WeightDiagramActivity.this);

        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();

        dbHelper = new WeightDB(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(WeightDB.TABLE_WEIGHT, null, null, null, null, null, null);

        if (cursor.moveToFirst())
        {
            int idIndex = cursor.getColumnIndex(WeightDB.KEY_ID);
            int weightIndex = cursor.getColumnIndex(WeightDB.KEY_WEIGHT);
            do {
                yValues.add(new Entry(valueOf(cursor.getInt(idIndex)), valueOf(cursor.getInt(weightIndex))));
            } while (cursor.moveToNext());
            LineDataSet set1 = new LineDataSet(yValues, "Динамика веса");

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
            case R.id.clearWeight:
                database.delete(WeightDB.TABLE_WEIGHT, null, null);
                finish();
                break;
        }
    }
}
