package com.example.coursework.Weight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.coursework.R;

public class WeightInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_info);
    }

    void returnToWeight(View v)
    {
        finish();
    }
}
