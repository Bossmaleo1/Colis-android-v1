package com.colis.android.colis.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TimePickerFragment extends AppCompatDialogFragment implements TimePickerDialog.OnTimeSetListener {

    private static final String TAG = "TimePickerFragment";
    final Calendar c = Calendar.getInstance();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    /*public void onDateSet(DatePicker view, int year, int month, int day) {
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        String selectedDate = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH).format(c.getTime());

        Log.d(TAG, "onDateSet: " + selectedDate);
        getTargetFragment().onActivityResult(
                getTargetRequestCode(),
                Activity.RESULT_OK,
                new Intent().putExtra("selectedDate", selectedDate)
        );
    }*/

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        c.set(Calendar.HOUR, hourOfDay);
        c.set(Calendar.MINUTE, minute);

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        String selectedDate = format.format(c.getTime());
        getTargetFragment().onActivityResult(
                getTargetRequestCode(),
                Activity.RESULT_OK,
                new Intent().putExtra("selectedTime", selectedDate)
        );

    }
}
