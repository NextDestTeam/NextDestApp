package com.nextdest.nextdest;

import android.support.v7.app.AppCompatDialogFragment;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import java.util.Calendar;

public class DatePickerDialogFragment extends AppCompatDialogFragment {

        public static final String DATEPICKER_DIALOG_CALLBACK = "DATEPICKER_DIALOG_CALLBACK";
        private DatePickerDialog.OnDateSetListener oCallback;


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            oCallback = (DatePickerDialog.OnDateSetListener)
                    this.getArguments().get(DATEPICKER_DIALOG_CALLBACK);

            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);


            return new DatePickerDialog(getActivity(), oCallback, year, month, day);
        }
}

