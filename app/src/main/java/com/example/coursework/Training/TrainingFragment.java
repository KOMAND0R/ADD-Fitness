package com.example.coursework.Training;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.coursework.R;

public class TrainingFragment extends Fragment implements View.OnClickListener
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_training, container, false);

        Button buttonStartTimer = rootView.findViewById(R.id.startTimer);

        buttonStartTimer.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {

        }
    }
}
