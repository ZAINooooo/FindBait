package com.danyal.findabait;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.rahman.dialog.Activity.SmartDialog;
import com.rahman.dialog.ListenerCallBack.SmartDialogClickListener;
import com.rahman.dialog.Utilities.SmartDialogBuilder;

import java.util.Calendar;

import static com.danyal.findabait.Submit_Request.edittext;

@SuppressLint("ValidFragment")
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {


    EditText textView;
    Context context;

    Typeface face, face2;


    String hour3, min3;
    String hour2, min2;

    Calendar c;

    {
        getContext();
    }

    @SuppressLint("ValidFragment")
    public TimePickerFragment(EditText textView, Context context) {
        this.textView = textView;
        this.context = context;
    }


    String time;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

//         c = Calendar.getInstance();
//        int hour = c.get(Calendar.HOUR_OF_DAY);
//        int minute = c.get(Calendar.MINUTE);
//
//
//        int year = c.get(Calendar.YEAR);
//        int month = c.get(Calendar.MONTH);
//        int day = c.get(Calendar.DAY_OF_MONTH);
//
//
//        c.set(year,month,day);


        face = Typeface.createFromAsset(getActivity().getAssets(), "ptsanswebbold.ttf");
        face2 = Typeface.createFromAsset(getActivity().getAssets(), "ptsanswebregular.ttf");

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);


//        int year = c.get(Calendar.YEAR);
//        int month = c.get(Calendar.MONTH);
//        int day = c.get(Calendar.DAY_OF_MONTH);


        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        Calendar datetime = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
        datetime.set(Calendar.MINUTE, minute);

        if (datetime.getTimeInMillis() >= calendar.getTimeInMillis()) {
            hour2 = hourOfDay + "";
            min2 = minute + "";

            if (hourOfDay < 12) {
                hour2 = "0" + hourOfDay;
            }

//             min2 = "0"+minute;
            if (minute < 12) {
                min2 = "0" + minute;
            }
            edittext.setText(hour2 + ":" + min2);

            Log.d("Timeeeeww", hour2 + ":" + min2);

        } else {
//            Toast.makeText(DatePickerAndTimePickerActivity19.this,"Invalid Time", Toast.LENGTH_LONG).show();
//            Toast.makeText(context, "sss", Toast.LENGTH_SHORT).show();

            new SmartDialogBuilder(getActivity())
                    .setTitle("Error Message")
                    .setSubTitle("Can't Select Back Time")
                    .setCancalable(true)
                    .setTitleFont(face)
                    .setSubTitleFont(face2)
                    .setPositiveButton("OK", new SmartDialogClickListener() {
                        @Override
                        public void onClick(SmartDialog smartDialog) {
                            smartDialog.dismiss();


                        }
                    }).build().show();;


//        String hour = hourOfDay+"";
//        if (hourOfDay < 10) {
//            hour = "0"+hourOfDay;
//        }
//        String min = minute+"";
//        if (minute < 10)
//            min = "0"+minute;
//        edittext.setText(hour + ":" + min);
//
//        Log.d("Timeeee", hour + ":"+min);


        }
    }
}
