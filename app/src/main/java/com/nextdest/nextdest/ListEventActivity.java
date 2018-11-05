package com.nextdest.nextdest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.nextdest.adapter.EventAdapter;
import com.nextdest.form.EventForm;
import com.nextdest.service.EventFormService;

public class ListEventActivity extends AppCompatActivity {

    public static final String EXTRA_EVENT_CLICKED = "EXTRA_EVENT_CLICKED";

    ListView lvEvents;
    Button btAdd;
    EventAdapter eventAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_event);

        lvEvents = (ListView) findViewById(R.id.lvListEventEvents);
        btAdd = (Button) findViewById(R.id.btListEventAdd);

        eventAdapter = new EventAdapter(getApplicationContext(),
                0, EventFormService.getInstance().getEvents());

        lvEvents.setAdapter(eventAdapter);

        lvEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EventForm eventClicked = (EventForm) parent.getItemAtPosition(position);

                Intent i = new Intent(getApplicationContext(),SelectedEventActivity.class);
                i.putExtra(EXTRA_EVENT_CLICKED,eventClicked.getId());

                startActivity(i);
            }
        });

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),EventFormActivity.class);

                startActivity(i);
            }
        });

    }

    @Override
    public void onResume(){
        super.onResume();
        eventAdapter.notifyDataSetChanged();

    }
}
