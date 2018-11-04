package com.nextdest.nextdest;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ArrayAdapter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Bitmap;


import com.nextdest.form.EventForm;
import com.nextdest.service.EventFormService;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.ByteArrayOutputStream;


public class EventFormActivity extends AppCompatActivity
        implements DatePickerDialog.OnDateSetListener,
        Serializable,
        TimePickerDialog.OnTimeSetListener{

    private static int RESULT_LOAD_IMAGE = 1;

    TextView tvFormTitle;
    Spinner spType;
    EditText etName;
    TextView tvDate;
    TextView tvTime;
    EditText etCost;
    EditText etDescription;
    EditText etShortDescription;
    EditText etLocation;
    ImageButton ibAddPhoto;
    ImageView ivPhoto;
    Button btSubmit;
    private EventForm eventForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_form);
//        tvFormTitle = (TextView) findViewById(R.id.tvTitleAddActivity);
        spType = (Spinner) findViewById(R.id.spEventFormType);
        etName = (EditText) findViewById(R.id.etEventFormActivityName);
        tvDate = (TextView) findViewById(R.id.tvEventFormDate);
        tvTime = (TextView) findViewById(R.id.tvEventFormTime);
        etCost = (EditText) findViewById(R.id.etEventFormCost);
        etDescription = (EditText) findViewById(R.id.etEventFormDescription);
        etShortDescription = (EditText) findViewById(R.id.etEventFormShortDescription);
        etLocation = (EditText) findViewById(R.id.etEventFormLocation);
        ibAddPhoto = (ImageButton) findViewById(R.id.ibEventFormAddPhoto);
        ivPhoto = (ImageView) findViewById(R.id.ivEventFormPhoto);
        btSubmit = (Button) findViewById(R.id.btEventFormSubmit);

        loadTypesOfEvent();
    }

    private void loadTypesOfEvent() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.events_types, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spType.setAdapter(adapter);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            try {
                InputStream is = getContentResolver().openInputStream(selectedImage);
                ivPhoto.setImageBitmap(BitmapFactory.decodeStream(is));
            } catch (FileNotFoundException e) {
                e.printStackTrace();

            }

        }


    }

    public void openImage(View view) {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    public void saveEvent(View view) {
        fillForm();
        if(validate()){

        }
        EventFormService eventService = new EventFormService();
        eventService.save(eventForm);

    }

    private boolean validate() {
        return true;
    }

    private void fillForm() {
        eventForm.setType(spType.getSelectedItem().toString());
        eventForm.setName(etName.getText().toString());
        try {
            Date date = DateFormat.getDateFormat(getApplicationContext()).parse(tvDate.getText().toString());
            Date time = DateFormat.getTimeFormat(getApplicationContext()).parse(tvTime.getText().toString());
            date.setTime(time.getTime());
            eventForm.setDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        eventForm.setShortDescription(etShortDescription.getText().toString());
        eventForm.setDescription(etDescription.getText().toString());
        eventForm.setCost(Double.parseDouble(etCost.getText().toString()));
        eventForm.setLocation(etLocation.getText().toString());

        BitmapDrawable bitmapDrawable = ((BitmapDrawable) ivPhoto.getDrawable());
        Bitmap bitmap = bitmapDrawable .getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        eventForm.setPhoto(stream.toByteArray());

    }

}
