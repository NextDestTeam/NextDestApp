package com.nextdest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.nextdest.adapter.GridAdabter;

public class PopluarActivity extends AppCompatActivity {


    int images []= {R.drawable.barca,R.drawable.festival,R.drawable.mou,R.drawable.food,R.drawable.tennis,R.drawable.party, R.drawable.tango ,R.drawable.marathon ,R.drawable.cars ,R.drawable.flam ,R.drawable.theater , R.drawable.galler};
    String values [] = {
            "El Classico",
            "Jazz Party",
            "Natural Trip",
            "Diner ",
            "Tines Match",
            "Classic concert",
            "Tango",
            "marathon",
            "Cars race",
            "Flamingo",
            "Theater play",
            "Art gallery"
            };

    Button btSearch;
    GridView gridView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_event);



        gridView= (GridView) findViewById(R.id.gridView);





       /* Spinner spinner = (Spinner) findViewById(R.id.spinnerPeople);
        Spinner spinnerCost = (Spinner) findViewById(R.id.spinnerCost);
        Spinner spinnerAct = (Spinner) findViewById(R.id.spinnerActivity);
         Defining ArrayAdapter
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


         */



       /* btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(),EventSearchResultActivity.class);
                startActivity(i);
            }
        });*/







        GridAdabter gridAdabter = new GridAdabter (this , images ,values);
        gridView.setAdapter(gridAdabter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),SelectedEventActivity.class);

                startActivity(intent);
            }
        });



    }










}
