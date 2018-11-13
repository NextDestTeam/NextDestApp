package com.nextdest.nextdest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.nextdest.adapter.GridAdabter;

public class PopluarActivity extends AppCompatActivity {

    GridView gridView;

    int images []= {R.drawable.barca,R.drawable.festival,R.drawable.mou,R.drawable.food,R.drawable.dance,R.drawable.party };
    String values [] = {
            "El Classico",
            "Jazz Party",
            "Natural Trip",
            "Diner ",
            "Tines Match",
            "Classic Party"};

    Button btSearch;
    ImageView btnimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_event);

        btSearch = (Button) findViewById(R.id.btPopularActivitySearch);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerPeople);
        Spinner spinnerCost = (Spinner) findViewById(R.id.spinnerCost);
        Spinner spinnerAct = (Spinner) findViewById(R.id.spinnerActivity);
        // Defining ArrayAdapter
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.people_array, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapterCost = ArrayAdapter.createFromResource(this,
                R.array.cost_array, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapterAct = ArrayAdapter.createFromResource(this,
                R.array.activity_array, android.R.layout.simple_spinner_item);
// Specify the layout
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Applying adapter to spinner
        spinner.setAdapter(adapter);
        spinnerCost.setAdapter(adapterCost);
        spinnerAct.setAdapter(adapterAct);

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(),EventSearchResultActivity.class);
                startActivity(i);
            }
        });




        gridView = (GridView)findViewById(R.id.gridView);


        GridAdabter gridAdabter = new GridAdabter (this , images ,values);
        gridView.setAdapter(gridAdabter);
    }





}
