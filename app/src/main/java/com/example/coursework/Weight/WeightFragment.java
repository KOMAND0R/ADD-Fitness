package com.example.coursework.Weight;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coursework.R;
import com.example.coursework.Weight.TableActivity;
import com.example.coursework.Weight.WeightDB;
import com.example.coursework.Weight.WeightDiagramActivity;
import com.example.coursework.Weight.WeightInfoActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WeightFragment extends Fragment implements View.OnClickListener
{
    float height, weight;
    WeightDB weightDB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_weight, container, false);

        final EditText editTextWeight = rootView.findViewById(R.id.weightEditText);
        final EditText editTextHeight = rootView.findViewById(R.id.heightEditText);
        final TextView textViewIMT = rootView.findViewById(R.id.textViewIMT);
        Button countButton = rootView.findViewById(R.id.countButton);
        Button weightButton = rootView.findViewById(R.id.weightButton);
        TextView textViewInfo = rootView.findViewById(R.id.weightInfo);

        weightDB = new WeightDB(getActivity());
        final SQLiteDatabase database = weightDB.getWritableDatabase();
        final ContentValues contentValues = new ContentValues();

        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);

        countButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (editTextHeight.getText().toString().equals("") || editTextWeight.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(), "Заполните поля!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (Integer.parseInt(editTextWeight.getText().toString()) >= 200)
                    {
                        editTextWeight.setText("200");
                        weight = 200;
                        contentValues.put(WeightDB.KEY_WEIGHT, weight);
                        contentValues.put(WeightDB.KEY_DATE, weight);
                        database.insert(WeightDB.TABLE_WEIGHT, null, contentValues);
                    }
                    else if (Integer.parseInt(editTextWeight.getText().toString()) <= 10)
                    {
                        editTextWeight.setText("10");
                        weight = 10;
                        contentValues.put(WeightDB.KEY_WEIGHT, weight);
                        contentValues.put(WeightDB.KEY_DATE, weight);
                        database.insert(WeightDB.TABLE_WEIGHT, null, contentValues);
                    }
                    else
                    {
                        weight = Integer.parseInt(editTextWeight.getText().toString());
                        contentValues.put(WeightDB.KEY_WEIGHT, editTextWeight.getText().toString());
                        contentValues.put(WeightDB.KEY_DATE, editTextWeight.getText().toString());
                        database.insert(WeightDB.TABLE_WEIGHT, null, contentValues);
                    }

                    if (Integer.parseInt(editTextHeight.getText().toString()) >= 300)
                    {
                        editTextHeight.setText("300");
                        height = 300;
                    }
                    else if (Integer.parseInt(editTextHeight.getText().toString()) <= 50)
                    {
                        editTextHeight.setText("50");
                        height = 50;
                    }
                    else
                    {
                        height = Integer.parseInt(editTextHeight.getText().toString());
                    }

                    float IMT = weight / ((height / 100) * (height / 100));
                    String sIMT = String.format("%.2f", IMT);

                    if (IMT <= 16)
                    {
                        textViewIMT.setText("Ваш ИМТ: " + sIMT +". У вас выраженный дефицит массы тела.");
                    }
                    else if (IMT > 16 && IMT <= 18.5)
                    {
                        textViewIMT.setText("Ваш ИМТ: " + sIMT +". У вас недостаточная масса тела.");
                    }
                    else if (IMT > 18.5 && IMT <= 25)
                    {
                        textViewIMT.setText("Ваш ИМТ: " + sIMT +". Это норма.");
                    }
                    else if (IMT > 25 && IMT <= 30)
                    {
                        textViewIMT.setText("Ваш ИМТ: " + sIMT +". У вас избыточная масса тела.");
                    }
                    else if (IMT > 30 && IMT <= 35)
                    {
                        textViewIMT.setText("Ваш ИМТ: " + sIMT +". У вас ожирение.");
                    }
                    else if (IMT > 35 && IMT <= 40)
                    {
                        textViewIMT.setText("Ваш ИМТ: " + sIMT +". У вас резко выраженное ожирение.");
                    }
                    else if (IMT > 40)
                    {
                        textViewIMT.setText("Ваш ИМТ: " + sIMT +". У вас очень резко выраженное ожирение.");
                    }
                }
            }
        });

        weightButton.setOnClickListener(this);
        textViewInfo.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.weightButton:
                Intent intent2 = new Intent(getActivity(), WeightDiagramActivity.class);
                getActivity().startActivity(intent2);
                break;
            case R.id.weightInfo:

                Intent intent3 = new Intent(getActivity(), WeightInfoActivity.class);
                getActivity().startActivity(intent3);
                /*
                Intent intent3 = new Intent(getActivity(), TableActivity.class);
                getActivity().startActivity(intent3);
                break; */

        }

    }
}
