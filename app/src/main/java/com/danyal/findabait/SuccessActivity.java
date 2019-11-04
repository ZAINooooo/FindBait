package com.danyal.findabait;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SuccessActivity extends AppCompatActivity {

    LinearLayout back_pressed;

    SharedPreferences sharedPreferences;
    boolean isLogin;


    ImageView home_success,abous_us_success;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
        isLogin = sharedPreferences.getBoolean("isLogin", false);

        getWindow().setStatusBarColor(Color.TRANSPARENT);

        back_pressed = findViewById(R.id.back_pressed);

        home_success = findViewById(R.id.homess);
        abous_us_success = findViewById(R.id.about_us);



        home_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!isLogin) //if login is false
                {
                    startActivity(new Intent(SuccessActivity.this , ThirdScreen.class));
                    finish();
                }

                else
                {
                    startActivity(new Intent(SuccessActivity.this, Home_Screen.class));
                    finish();
                }


            }
        });

        abous_us_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SuccessActivity.this , AboutUsActivity.class));
                finish();
            }
        });



        back_pressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
    }
}
