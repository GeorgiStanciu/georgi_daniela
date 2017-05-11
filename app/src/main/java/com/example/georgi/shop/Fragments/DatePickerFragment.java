package com.example.georgi.shop.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.georgi.shop.Helpers.GlobalBus;
import com.example.georgi.shop.Helpers.OnSetBirthDate;

import java.util.Calendar;

/**
 * Created by Georgi on 11-May-17.
 */

public class DatePickerFragment  extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private int currentDay;
    private int currentMonth;
    private int currentYear;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar calendar = Calendar.getInstance();
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        currentMonth= calendar.get(Calendar.MONTH);
        currentYear = calendar.get(Calendar.YEAR);

        return new DatePickerDialog(getActivity(), this, currentYear, currentMonth, currentDay);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        if(currentYear < year || currentYear == year && currentMonth < month || currentYear == year && currentMonth == month && currentDay <= day) {
            Toast.makeText(getActivity(), "Incorrect date", Toast.LENGTH_SHORT).show();
        }
        else
            GlobalBus.getBus().post(new OnSetBirthDate(day + "/" + (month + 1)+ "/" + year));
    }


}