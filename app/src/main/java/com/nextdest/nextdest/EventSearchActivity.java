package com.nextdest.nextdest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class EventSearchActivity extends AppCompatActivity {

    Button btSearch;

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
    }



}
