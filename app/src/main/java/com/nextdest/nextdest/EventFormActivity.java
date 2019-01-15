package com.nextdest.nextdest;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
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
import android.widget.Toast;

import com.nextdest.model.Event;

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
    private static String EVENT_FORM_ACTIVITY = "EVENT_FORM_ACTIVITY";
    DB db =new DB (this);
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
    private Event event = new Event();

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
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name=etName.getText().toString();
                String date = tvDate.getText().toString();
                String ShortDescription=etShortDescription.getText().toString();
                String Description=etDescription.getText().toString();
                int Cost=Integer.parseInt(etCost.getText().toString());
                String Location=etLocation.getText().toString();

               long id= db.addNew_ACTIVITY(Name,ShortDescription,Description,Location,Cost,1,date,1);
              int _id =(int) (long)id;
                Drawable bitmapDrawable =  ivPhoto.getDrawable();
                Bitmap bitmap = ((BitmapDrawable)bitmapDrawable).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] Photo=stream.toByteArray();
                long image_id=db.addNew_ACTIVITY_IMAGE(_id,Photo);
                Toast.makeText(getApplicationContext(),""+image_id,Toast.LENGTH_LONG).show();

            }
        });

        loadTypesOfEvent();
      /*  int eventId = getIntent().getIntExtra(ListEventActivity.EXTRA_EVENT_CLICKED,0);
        if(eventId!=0)
            loadEdit(EventService.getInstance().load(eventId));

    }

    private void loadEdit(Event load) {
        for(int i=0; i < spType.getAdapter().getCount();i++){
            if(spType.getAdapter().getItem(i).toString().equals(load.getType())){
                spType.setSelection(i);
                break;
            }

        }

        etName.setText(load.getName());
        tvDate.setText(DateFormat.getDateFormat(getApplicationContext()).format(load.getDate()));
        tvTime.setText(DateFormat.getTimeFormat(getApplicationContext()).format(load.getDate()));
        etCost.setText(String.valueOf(load.getCost()));
        etDescription.setText(load.getDescription());
        etShortDescription.setText(load.getShortDescription());
        etLocation.setText(load.getLocation());
        Bitmap bMap = BitmapFactory.decodeByteArray(load.getPhoto(), 0, load.getPhoto().length);
        ivPhoto.setImageBitmap(bMap);*/
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

   /* public void saveEvent(View view) {
        fillForm();
       if(validate()){

        }
        EventService.getInstance().save(event);
        Intent iSelectedEvent = new Intent(this,ListEventActivity.class);
        this.startActivity(iSelectedEvent);

    }

    private boolean validate() {
        return true;
    }

    private void fillForm() {
        event.setType((String)spType.getSelectedItem());
       String Name=etName.getText().toString();
       String date = tvDate.getText().toString();
        try {
            Calendar calendar = new GregorianCalendar();
            //Date date = DateFormat.getDateFormat(getApplicationContext()).parse(tvDate.getText().toString());
            Date time = DateFormat.getTimeFormat(getApplicationContext()).parse(tvTime.getText().toString());
           // calendar.set(date.getYear(),date.getMonth(),date.getDay(),time.getHours(),time.getMinutes());
            event.setDate(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String ShortDescription=etShortDescription.getText().toString();
        String Description=etDescription.getText().toString();
      int Cost=Integer.parseInt(etCost.getText().toString());
        String Location=etLocation.getText().toString();

        Drawable bitmapDrawable =  ivPhoto.getDrawable();
        Bitmap bitmap = ((BitmapDrawable)bitmapDrawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] Photo=stream.toByteArray();
        db.addNew_ACTIVITY(Name,ShortDescription,Description,Location,Cost,1,date);



    }*/

}
