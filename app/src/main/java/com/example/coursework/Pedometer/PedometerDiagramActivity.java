package com.example.coursework.Pedometer;

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

public class PedometerDiagramActivity extends AppCompatActivity implements View.OnClickListener
{

    private static final String TAG = "WeightDiagramActivity";

    private LineChart mChart;
    PedometerDB dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer_diagram);
        Button buttonClear = (Button) findViewById(R.id.clearSteps);

        mChart = (LineChart) findViewById(R.id.stepsLineChart);

        //mChart.setOnChartGestureListener(WeightDiagramActivity.this);
        //mChart.setOnChartValueSelectedListener(WeightDiagramActivity.this);

        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();

        dbHelper = new PedometerDB(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(PedometerDB.TABLE_PEDOMETER, null, null, null, null, null, null);

        if (cursor.moveToFirst())
        {
            int idIndex = cursor.getColumnIndex(PedometerDB.KEY_ID);
            int stepIndex = cursor.getColumnIndex(PedometerDB.KEY_STEPS);
            do {
                yValues.add(new Entry(valueOf(cursor.getInt(idIndex)), valueOf(cursor.getInt(stepIndex))));
            } while (cursor.moveToNext());
            LineDataSet set1 = new LineDataSet(yValues, "Динамика шагов");

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
            case R.id.clearSteps:
                database.delete(PedometerDB.TABLE_PEDOMETER, null, null);
                finish();
                break;
        }
    }
}
