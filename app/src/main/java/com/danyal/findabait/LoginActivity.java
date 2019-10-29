package com.danyal.findabait;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {
    Date parsed = null;
    TextView forgot_password;
    Button login_btn;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        forgot_password = findViewById(R.id.forgot_password);
//        date_value = findViewById(R.id.date_value);


        login_btn = findViewById(R.id.login_btn);


//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try
//        {
//            parsed = format.parse("2011-03-01 15:10:37");
//            TimeZone tz = TimeZone.getTimeZone("Asia/Dubai");
//            format.setTimeZone(tz);
//            String result = format.format(parsed);
//            Log.d("Result" , result);
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
// => This time is in the user phone timezone, you will maybe need to turn it in UTC!



        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this , Home_Screen.class));
                finish();
            }
        });





        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(LoginActivity.this , ForgotPassword.class));
                finish();
            }
        });
//        SystemBarTintManager tintManager = new SystemBarTintManager(this);
//        tintManager.setNavigationBarTintResource(R.color.black);
        Window window = this.getWindow();
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }



}
