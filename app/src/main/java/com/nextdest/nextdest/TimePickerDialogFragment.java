package com.nextdest.nextdest;

import android.support.v7.app.AppCompatDialogFragment;
import android.app.TimePickerDialog;
import android.app.Dialog;
import android.widget.TimePicker;
import android.os.Bundle;
import android.text.format.DateFormat;
import java.util.Calendar;


public class TimePickerDialogFragment extends AppCompatDialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
    }
}

