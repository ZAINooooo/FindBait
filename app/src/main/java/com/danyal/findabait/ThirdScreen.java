package com.danyal.findabait;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.jaeger.library.StatusBarUtil;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class ThirdScreen extends AppCompatActivity {
    Date parsed;

    ImageView btn_started,feedback_image2    , sss ,about_us,homess;
   TextView date_value;
   ImageView contact_us;
   LinearLayout third;
String  result;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);



        btn_started = (ImageView) findViewById(R.id.login_btn);
        date_value = findViewById(R.id.date_value);
        feedback_image2 = findViewById(R.id.feedback_image2);
        contact_us = findViewById(R.id.contact_us);

        third = findViewById(R.id.third);
        third.setVisibility(View.VISIBLE);
        about_us = findViewById(R.id.about_us);
        homess = findViewById(R.id.homess);

        homess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                startActivity(new Intent(ThirdScreen.this , Submit_Request.class));
////                finish();
            }
        });

        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ThirdScreen.this , AboutUsActivity.class));
                finish();
            }
        });


        sss = findViewById(R.id.sss);


//        SystemBarTintManager tintManager = new SystemBarTintManager(this);
//        tintManager.setNavigationBarTintResource(R.color.black);
//
//


        sss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ThirdScreen.this , TicketDetail.class));
//                finish();
            }
        });




        contact_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ThirdScreen.this , MapActivity.class));
//                finish();
            }
        });


        feedback_image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        startActivity(new Intent(ThirdScreen.this , FeedbackActivity.class));
//        finish();
            }
        });


        getWindow().setStatusBarColor(Color.TRANSPARENT);
//// clear FLAG_TRANSLUCENT_STATUS flag:
//
//
//        yyyy-MM-dd HH:mm:ss

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


//        getWindow().setStatusBarColor(Color.TRANSPARENT);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

//        StatusBarUtil.setTransparent(ThirdScreen.this);

//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.black));
////        }
////
////        else
////        {
////            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.black));
////        }
//
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        btn_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ThirdScreen.this , LoginActivity.class));
//                finish();

            }
        });
    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();

    }
}
