package com.nextdest.nextdest;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.Button;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;

public class EventFormActivity extends AppCompatActivity
        implements DatePickerDialog.OnDateSetListener,
        Serializable,
        TimePickerDialog.OnTimeSetListener{

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
        AppCompatDialogFragment datepicker = new DatePickerDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable(DatePickerDialogFragment.DATEPICKER_DIALOG_CALLBACK,this);
        datepicker.setArguments(args);
        datepicker.show(getSupportFragmentManager(), "datePicker");
    }

    public void chooseTime(View view) {
        AppCompatDialogFragment timepicker = new TimerPickerDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable(TimerPickerDialogFragment.TIMERPICKER_DIALOG_CALLBACK,this);
        timepicker.setArguments(args);
        timepicker.show(getSupportFragmentManager(), "timePicker");

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(year+"-"+(month+1)+"-"+dayOfMonth);
            tvDate.setText(DateFormat.getDateFormat(getApplicationContext()).format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            Date date = sdf.parse(hourOfDay+":"+minute);
            tvTime.setText(DateFormat.getTimeFormat(getApplicationContext()).format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
