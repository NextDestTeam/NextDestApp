package com.nextdest.nextdest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nextdest.form.EventForm;


public class SelectedEventActivity1 extends AppCompatActivity {

    public static final String EXTRA_EVENT_CLICKED = "EXTRA_EVENT_CLICKED";

    TextView tvName;
    ImageView ivPhoto;
    TextView tvShortDescription;
    TextView tvLocation;
    TextView tvCost;
    TextView tvDate,tvtime;
    TextView tvDescription;
    Button btEdit;
    EventForm form;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_activity1);

        tvName = (TextView) findViewById(R.id.name);
        ivPhoto = (ImageView) findViewById(R.id.ivSelectedActivityPhoto);
        tvDescription = (TextView) findViewById(R.id.tvSelectedActivityDescription);
        tvLocation = (TextView) findViewById(R.id.tvSelectedActivityLocation);
        tvCost = (TextView) findViewById(R.id.tvSelectedActivityCost);
        tvDate = (TextView) findViewById(R.id.tvSelectedActivityDate);
        tvtime=(TextView)findViewById(R.id.tvSelectedActivitytime);
        btEdit = (Button) findViewById(R.id.btSelectActivityEdit);



        Intent intent = getIntent();
        int id = intent.getIntExtra("image",R.drawable.dance);
        ivPhoto.setImageResource(id);
        String detail=intent.getStringExtra("detail");
        tvDescription.setText(detail);
        String name=intent.getStringExtra("name");
        tvName.setText(name);
        String Price=intent.getStringExtra("Price");
        tvCost.setText(Price);
        String time=intent.getStringExtra("time");
        tvtime.setText(time);
        String date=intent.getStringExtra("date");
        tvDate.setText(date);
        String place=intent.getStringExtra("place");
        tvLocation.setText(place);





    }


}