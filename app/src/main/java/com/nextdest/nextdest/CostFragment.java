package com.nextdest.nextdest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class CostFragment extends Fragment {
    Context context;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
    ArrayList<Row> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cost, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.my_recycler_view);
        Spinner spinner = (Spinner) view.findViewById(R.id.spinnerPeople);
        Spinner spinnerCost = (Spinner) view.findViewById(R.id.spinnerCost);
        Spinner spinnerAct = (Spinner) view.findViewById(R.id.spinnerActivity);

        /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.people_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterCost = ArrayAdapter.createFromResource(context,
                R.array.cost_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterAct = ArrayAdapter.createFromResource(context,
                R.array.activity_array, android.R.layout.simple_spinner_item);
// Specify the layout
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Applying adapter to spinner
        spinner.setAdapter(adapter);
        spinnerCost.setAdapter(adapterCost);
        spinnerAct.setAdapter(adapterAct);*/
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        list.add(new Row("music","23 diciembre - 19h","10$","llieda","Christmas.catCor de l’Orfeó Lleidatà","12:00pm",R.drawable.music));
        list.add(new Row("music","23 diciembre - 19h","10$","llieda","Christmas.catCor de l’Orfeó Lleidatà","12:00pm",R.drawable.party));
        list.add(new Row("music","23 diciembre - 19h","10$","llieda","Christmas.catCor de l’Orfeó Lleidatà","12:00pm",R.drawable.festival));
        list.add(new Row("music","23 diciembre - 19h","10$","llieda","Christmas.catCor de l’Orfeó Lleidatà","12:00pm",R.drawable.food));
        list.add(new Row("music","23 diciembre - 19h","10$","llieda","Christmas.catCor de l’Orfeó Lleidatà","12:00pm",R.drawable.festival));
        list.add(new Row("music","23 diciembre - 19h","10$","llieda","Christmas.catCor de l’Orfeó Lleidatà","12:00pm",R.drawable.music2));
        list.add(new Row("music","23 diciembre - 19h","10$","llieda","Christmas.catCor de l’Orfeó Lleidatà","12:00pm",R.drawable.festival));
        list.add(new Row("music","23 diciembre - 19h","10$","llieda","Christmas.catCor de l’Orfeó Lleidatà","12:00pm",R.drawable.snow2));
        list.add(new Row("music","23 diciembre - 19h","10$","llieda","Christmas.catCor de l’Orfeó Lleidatà","12:00pm",R.drawable.festival));
        recyclerView.setLayoutManager(layoutManager);
        myAdapter=new MyAdapter(list, context);
        recyclerView.setAdapter(myAdapter);
        return view;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Select Your Preferences");
    }
}