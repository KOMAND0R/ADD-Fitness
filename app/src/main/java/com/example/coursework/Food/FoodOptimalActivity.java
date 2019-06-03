package com.example.coursework.Food;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coursework.R;

public class FoodOptimalActivity extends AppCompatActivity
{


    int weight, height, age, sex, act;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_optimal);

        final EditText editTextOptimalWeight = (EditText) findViewById(R.id.optimalWeight);
        final EditText editTextOptimalHeight = (EditText) findViewById(R.id.optimalHeight);
        final EditText editTextOptimalAge = (EditText) findViewById(R.id.optimalAge);
        final TextView textViewHB = (TextView) findViewById(R.id.tvhb);
        final TextView textViewMG = (TextView) findViewById(R.id.tvmg);
        final Spinner spinnerSex = (Spinner) findViewById(R.id.spinnerSex);
        final Spinner spinnerAct = (Spinner) findViewById(R.id.spinnerAct);
        Button btnCountOptimal = (Button) findViewById(R.id.countOptimalButton);

        btnCountOptimal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (editTextOptimalHeight.getText().toString().equals("") || editTextOptimalWeight.getText().toString().equals("") || editTextOptimalAge.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Заполните поля!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (Integer.parseInt(editTextOptimalWeight.getText().toString()) >= 200)
                    {
                        editTextOptimalWeight.setText("200");
                        weight = 200;
                    }
                    else if (Integer.parseInt(editTextOptimalWeight.getText().toString()) <= 10)
                    {
                        editTextOptimalWeight.setText("10");
                        weight = 10;
                    }
                    else
                    {
                        weight = Integer.parseInt(editTextOptimalWeight.getText().toString());
                    }

                    if (Integer.parseInt(editTextOptimalHeight.getText().toString()) >= 300)
                    {
                        editTextOptimalHeight.setText("300");
                        height = 300;
                    }
                    else if (Integer.parseInt(editTextOptimalHeight.getText().toString()) <= 50)
                    {
                        editTextOptimalHeight.setText("50");
                        height = 50;
                    }
                    else
                    {
                        height = Integer.parseInt(editTextOptimalHeight.getText().toString());
                    }

                    if (Integer.parseInt(editTextOptimalAge.getText().toString()) <= 10)
                    {
                        editTextOptimalAge.setText("10");
                        age = 10;
                    }
                    else if (Integer.parseInt(editTextOptimalAge.getText().toString()) >= 150)
                    {
                        editTextOptimalAge.setText("150");
                        age = 150;
                    }
                    else
                    {
                        age = Integer.parseInt(editTextOptimalAge.getText().toString());
                    }

                    double [] activity = {1.2, 1.375, 1.55, 1.725, 1.9};
                    double fMG = 0, fHB = 0;
                    sex = spinnerSex.getSelectedItemPosition();
                    act = spinnerAct.getSelectedItemPosition();

                    if (sex == 0)
                    {
                        fHB = (447.593 + (9.247 * weight) + (3.098 *height) - (4.330 * age)) * activity[act];
                        fMG = ((10 * weight) + (int)(6.25 * height) - (5 * age) - 161) * activity[act];
                    }
                    else if (sex == 1)
                    {
                        fHB = (88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age)) * activity[act];
                        fMG = ((10 * weight) + (int)(6.25 * height) - (5 * age)) * activity[act];
                    }

                    textViewHB.setText("Оптимальное количество калорий по формуле Харриса-Бенедикта: " + (int)fHB);
                    textViewMG.setText("Оптимальное количество калорий по формуле Маффина-Джеора: " + (int)fMG);
                }
            }
        });
    }
}
