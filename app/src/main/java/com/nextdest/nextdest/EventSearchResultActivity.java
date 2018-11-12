package com.nextdest.nextdest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class EventSearchResultActivity extends AppCompatActivity {

            ListView imgListView = (ListView) findViewById(R.id.imglistview);

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_search_results);

            Spinner spinner = (Spinner) findViewById(R.id.spinnerPeople);
            Spinner spinnerCost = (Spinner) findViewById(R.id.spinnerCost);
            Spinner spinnerAct = (Spinner) findViewById(R.id.spinnerActivity);
            // Defining ArrayAdapter
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
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



        }




}
