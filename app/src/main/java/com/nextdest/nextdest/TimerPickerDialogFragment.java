package com.nextdest.nextdest;

import android.support.v7.app.AppCompatDialogFragment;
import android.app.TimePickerDialog;
import android.app.Dialog;
import android.widget.TimePicker;
import android.os.Bundle;
import android.text.format.DateFormat;
import java.util.Calendar;


public class TimerPickerDialogFragment extends AppCompatDialogFragment {

    public static final String TIMERPICKER_DIALOG_CALLBACK = "TIMERPICKER_DIALOG_CALLBACK";
    private TimePickerDialog.OnTimeSetListener oCallback;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        oCallback = (TimePickerDialog.OnTimeSetListener)this.getArguments().get(TIMERPICKER_DIALOG_CALLBACK);
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), oCallback, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

}

