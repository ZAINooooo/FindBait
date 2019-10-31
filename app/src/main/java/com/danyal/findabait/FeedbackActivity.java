package com.danyal.findabait;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class FeedbackActivity extends AppCompatActivity {
    String  result;
    Date parsed;
    TextView date_value;
    Button btnss;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback2);



        getWindow().setStatusBarColor(Color.TRANSPARENT);

        btnss = findViewById(R.id.btnss);


        btnss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(FeedbackActivity.this , SuccessActivity.class));
                finish();
            }
        });
//// clear FLAG_TRANSLUCENT_STATUS flag:
//
//
//        yyyy-MM-dd HH:mm:ss
        date_value = findViewById(R.id.date_value2);

//        Friday, 17/2/19, 10:35 PM

        String weekday_name = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(System.currentTimeMillis());  //Fruday
//        LocalDate.now().getDayOfWeek().name();
        Log.d("Day_is", weekday_name);


        String datesssss = new SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(new Date());
        Log.d("Date_is", datesssss);
        SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yy");
        try {
            parsed = format2.parse(datesssss);
            TimeZone tz = TimeZone.getTimeZone("Asia/Dubai");
            format2.setTimeZone(tz);

            result = format2.format(parsed);
            Log.d("Date_is44",result);

        } catch (ParseException e) {
            e.printStackTrace();
        }





        String datesssss2 = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        Log.d("Date_is2", ""+datesssss2);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            parsed = format.parse(datesssss2);
            TimeZone tz = TimeZone.getTimeZone("Asia/Dubai");
            format.setTimeZone(tz);

            result = format.format(parsed);
            Log.d("Date_is44",result);

        } catch (ParseException e) {
            e.printStackTrace();
        }





        String weekday_names = new SimpleDateFormat("a", Locale.ENGLISH).format(System.currentTimeMillis());
        Log.d("Date_is3", weekday_names);




        date_value.setText("  "+weekday_name +", " +  datesssss +", " +  ""+result +" " +  weekday_names);


    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }
}
