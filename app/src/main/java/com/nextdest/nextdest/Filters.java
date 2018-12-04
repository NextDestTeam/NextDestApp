package com.nextdest.nextdest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;


public class Filters extends Fragment {





    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_filters, container, false);


        Spinner spinner1 = view.findViewById(R.id.spinnerPeople);
        Spinner spinner2 = view.findViewById(R.id.spinnerCost);
        Spinner spinner3 = view.findViewById(R.id.spinnerActivity);


       // ArrayAdapter<CharSequence>



        return view;

    }





}
