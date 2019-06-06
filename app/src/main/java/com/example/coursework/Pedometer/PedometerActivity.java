package com.example.coursework.Pedometer;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coursework.R;

public class PedometerActivity extends AppCompatActivity implements SensorEventListener
{

    private SensorManager sensorManager;
    private TextView count;
    public int stepCount = 0;
    boolean activityRunning;
    PedometerDB pedometerDB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer);



        count = (TextView) findViewById(R.id.count);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        activityRunning = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null)
        {
            sensorManager.registerListener(this, countSensor,
                    SensorManager.SENSOR_DELAY_UI);
        }
        else
        {
            Toast.makeText(this, "Count sensor not available!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onPause()
    {
        super.onPause();
        activityRunning = false;
        // if you unregister the last listener, the hardware will stop detecting
        // step events
        // sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        if (activityRunning)
        {
            stepCount++;
            count.setText(stepCount);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }

    @Override
    public void onBackPressed()
    {
        pedometerDB = new PedometerDB(this);
        SQLiteDatabase database = pedometerDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PedometerDB.KEY_STEPS, stepCount);
        database.insert(PedometerDB.TABLE_PEDOMETER, null, contentValues);

        finish();
    }
}
