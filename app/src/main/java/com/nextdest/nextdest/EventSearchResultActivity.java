package com.nextdest.nextdest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class EventSearchResultActivity extends AppCompatActivity {

    ListView imgListView;
    Button btnSearch;
    String[] eventNames = {"El Classico", "Dance", "Festival"};
    int[] eventImages = {R.drawable.barca, R.drawable.festival, R.drawable.dance};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);


        btnSearch = (Button) findViewById(R.id.btnSearch);


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


        SearchAdapter searchAdapter = new SearchAdapter(EventSearchResultActivity.this, eventNames, eventImages);
        imgListView = (ListView) findViewById(R.id.imglistview);
        imgListView.setAdapter(searchAdapter);
        imgListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mIntent = new Intent(EventSearchResultActivity.this,SelectedEventActivity.class);
                mIntent.putExtra("eventName", eventNames[i]);
                mIntent.putExtra("eventImage", eventImages[i]);
                startActivity(mIntent);
            }
        });

    }



}




