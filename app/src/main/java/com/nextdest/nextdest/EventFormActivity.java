package com.nextdest.nextdest;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.Button;
import android.support.v7.app.AppCompatDialogFragment;

public class EventFormActivity extends Activity {

    Spinner spType;
    EditText etName;
    TextView tvDate;
    TextView tvTime;
    EditText etCost;
    EditText etDescription;
    EditText etShortDescription;
    EditText etLocation;
    ImageButton ibAddPhoto;
    Button btSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_form);
        spType = (Spinner) findViewById(R.id.spEventFormType);
        etName = (EditText) findViewById(R.id.etEventFormActivityName);
        tvDate = (TextView) findViewById(R.id.tvEventFormDate);
        tvTime = (TextView) findViewById(R.id.tvEventFormTime);
        etCost = (EditText) findViewById(R.id.etEventFormCost);
        etDescription = (EditText) findViewById(R.id.etEventFormDescription);
        etShortDescription = (EditText) findViewById(R.id.etEventFormShortDescription);
        etLocation = (EditText) findViewById(R.id.etEventFormLocation);
        ibAddPhoto = (ImageButton) findViewById(R.id.ibEventFormAddPhoto);
        btSubmit = (Button) findViewById(R.id.btEventFormSubmit);
    }

    public void chooseDate(View view) {

    }

    public void chooseTime(View view) {
        AppCompatDialogFragment timepicker = new TimePickerDialogFragment();
        //timepicker.show((), "timePicker");

    }
}
