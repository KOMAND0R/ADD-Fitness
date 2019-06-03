package com.example.coursework.Pedometer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.coursework.Pedometer.PedometerActivity;
import com.example.coursework.Pedometer.PedometerDiagramActivity;
import com.example.coursework.R;


public class PedometerFragment extends Fragment implements View.OnClickListener
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_pedometer, container, false);

        Button buttonPedometer = (Button) rootView.findViewById(R.id.btnStartPedometer);
        Button buttonPedometerDiagram = (Button) rootView.findViewById(R.id.btnStartPedometerDiagram);

        buttonPedometer.setOnClickListener(this);
        buttonPedometerDiagram.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnStartPedometer:
                Intent intent1 = new Intent(getActivity(), PedometerActivity.class);
                getActivity().startActivity(intent1);
                break;
            case R.id.btnStartPedometerDiagram:
                Intent intent2 = new Intent(getActivity(), PedometerDiagramActivity.class);
                getActivity().startActivity(intent2);
                break;
        }
    }
}
