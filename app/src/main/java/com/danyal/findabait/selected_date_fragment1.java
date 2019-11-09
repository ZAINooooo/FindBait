package com.danyal.findabait;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.danyal.findabait.Submit_Request.edittext2;


public class selected_date_fragment1 extends DialogFragment {

    DatePicker mDatePicker;
    public static String date_conversion2;

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.N)

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {

        final Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        final Calendar calendar= Calendar.getInstance();
        calendar.set(year, month,day);

         DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {



                SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                calendar.set(year,month,dayOfMonth);

                date_conversion2 = dateFormat1.format(calendar.getTime());

                edittext2.setText(date_conversion2);

                Log.d("DateIs" , date_conversion2);

            }
        };

//        DatePickerDialog dialog=new DatePickerDialog(getActivity(),dateSetListener,year,month,day);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        datePickerDialog.show();
//        dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
        return datePickerDialog;
    }

}
