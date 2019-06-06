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

        Button buttonStartFirst = rootView.findViewById(R.id.startFirstCycle);
        Button buttonStartSecond = rootView.findViewById(R.id.startSecondCycle);
        Button buttonStartThird = rootView.findViewById(R.id.startThirdCycle);

        buttonStartFirst.setOnClickListener(this);
        buttonStartSecond.setOnClickListener(this);
        buttonStartThird.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.startFirstCycle:
                Intent intent1 = new Intent(getActivity(), FirstCycleActivity.class);
                getActivity().startActivity(intent1);
                break;
            case R.id.startSecondCycle:
                Intent intent2 = new Intent(getActivity(), SecondCycleActivity.class);
                getActivity().startActivity(intent2);
                break;
            case R.id.startThirdCycle:
                Intent intent3 = new Intent(getActivity(), ThirdCycleActivity.class);
                getActivity().startActivity(intent3);
                break;
        }
    }
}
