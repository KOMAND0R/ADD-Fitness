package com.example.coursework.Profile;

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

public class ProfileFragment extends Fragment
{
    int age;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        Button buttonCount = rootView.findViewById(R.id.countPulseButton);
        final EditText editTextAge = rootView.findViewById(R.id.ageEditText);
        final TextView textViewMax = rootView.findViewById(R.id.textViewMax);
        final TextView textViewCardio = rootView.findViewById(R.id.textViewCardio);
        final TextView textViewFat = rootView.findViewById(R.id.textViewFat);

        buttonCount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (editTextAge.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(), "Заполните поля!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (Integer.parseInt(editTextAge.getText().toString()) <= 10)
                    {
                        editTextAge.setText("10");
                        age = 10;
                    }
                    else if (Integer.parseInt(editTextAge.getText().toString()) >= 150)
                    {
                        editTextAge.setText("150");
                        age = 150;
                    }
                    else
                    {
                        age = Integer.parseInt(editTextAge.getText().toString());
                    }

                    int maxPulse = 220 - age;
                    int fatPulse = (int)(maxPulse * 0.75);
                    int cardioPulse = (int)(maxPulse * 0.6);

                    textViewMax.setText("Максимальный пульс: " + maxPulse);
                    textViewCardio.setText("Пульс для кардиотренировок: " + cardioPulse);
                    textViewFat.setText("Пульс для сжигания жира: " + fatPulse);
                }
            }
        });

        return rootView;
    }
}
