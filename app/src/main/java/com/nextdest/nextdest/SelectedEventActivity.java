package com.nextdest.nextdest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nextdest.form.EventForm;
import com.nextdest.service.EventFormService;




public class SelectedEventActivity extends AppCompatActivity {

    public static final String EXTRA_EVENT_CLICKED = "EXTRA_EVENT_CLICKED";

    TextView tvName;
    ImageView ivPhoto;
    TextView tvShortDescription;
    TextView tvLocation;
    TextView tvCost;
    TextView tvDate;
    TextView tvDescription;
    Button btEdit;
    EventForm form;
    Button btloc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_activity);

//        tvName = (TextView) findViewById(R.id.tvSelectedActivityTitle);
        ivPhoto = (ImageView) findViewById(R.id.ivSelectedActivityPhoto);
//        tvShortDescription = (TextView) findViewById(R.id.tvSelectedActivityShortDescription);
        tvDescription = (TextView) findViewById(R.id.tvSelectedActivityDescription);
        tvLocation = (TextView) findViewById(R.id.tvSelectedActivityLocation);
        tvCost = (TextView) findViewById(R.id.tvSelectedActivityCost);
        tvDate = (TextView) findViewById(R.id.tvSelectedActivityDate);
        btEdit = (Button) findViewById(R.id.btSelectActivityEdit);
        btloc = (Button) findViewById(R.id.loc);



        Intent intent = getIntent();
        tvName.setText(intent.getStringExtra("tvName"));
        ivPhoto.setImageResource(intent.getIntExtra("ivPhoto",0));
        tvShortDescription.setText(intent.getStringExtra("tvShortDescription"));
        tvCost.setText(intent.getStringExtra("tvCost"));
        tvDate.setText(intent.getStringExtra("tvDate"));
        tvLocation.setText(intent.getStringExtra("tvLocation"));


        
    }

    private void loadForm(EventForm form) {
        tvName.setText(form.getName());
        tvShortDescription.setText(form.getShortDescription());
        tvDescription.setText(form.getDescription());
        try {
            tvDate.setText(DateFormat
                    .getDateFormat(getApplicationContext())
                    .format(form.getDate()));
        }catch (Exception ex){
            ex.printStackTrace();
        }
        tvCost.setText(String.valueOf(form.getCost()));
        tvLocation.setText(form.getLocation());
        Bitmap bMap = BitmapFactory.decodeByteArray(form.getPhoto(), 0, form.getPhoto().length);
        ivPhoto.setImageBitmap(bMap);
    }
}
