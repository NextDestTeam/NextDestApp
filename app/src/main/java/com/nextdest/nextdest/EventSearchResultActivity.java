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

import java.util.ArrayList;
import java.util.List;

public class EventSearchResultActivity extends AppCompatActivity {

    ListView imgListView;
    Button btnSearch;
    String[] eventNames;
    int[] eventImages = {R.drawable.barca, R.drawable.festival, R.drawable.food, R.drawable.music2};
    List rowItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);


        rowItems = new ArrayList();
        //eventImages = getResources().getIntArray(R.array.event_imagesArr);
        eventNames = getResources().getStringArray(R.array.event_namesArr);


        SearchAdapter searchAdapter = new SearchAdapter(EventSearchResultActivity.this, eventNames, eventImages);
        imgListView = (ListView) findViewById(R.id.imglistview);
        imgListView.setAdapter(searchAdapter);
        imgListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mIntent = new Intent(EventSearchResultActivity.this,SelectedEventActivity.class);
                mIntent.putExtra("eventImage", eventImages[i]);
                mIntent.putExtra("eventName", eventNames[i]);
                startActivity(mIntent);
            }
        });

    }



}

