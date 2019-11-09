package com.danyal.findabait;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AboutUsActivity extends AppCompatActivity {


    ImageView home_about_us,about_about_us;
    SharedPreferences sharedPreferences;
    boolean isLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
        isLogin = sharedPreferences.getBoolean("isLogin", false);


        home_about_us = findViewById(R.id.homess);
        about_about_us = findViewById(R.id.about_us);


        home_about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isLogin) //if login is false
                {
                                    startActivity(new Intent(AboutUsActivity.this , ThirdScreen.class));
                                    finish();
                }

                else
                {
                    startActivity(new Intent(AboutUsActivity.this, Home_Screen.class));
                    finish();
                }


            }
        });

        about_about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                startActivity(new Intent(AboutUsActivity.this , AboutUsActivity.class));
//                finish();
            }
        });

    }


    @Override
    public void onBackPressed() {
        if (!isLogin) //if login is false

        {
            startActivity(new Intent(AboutUsActivity.this , ThirdScreen.class));
            finish();
        }

        else
        {
            startActivity(new Intent(AboutUsActivity.this, Home_Screen.class));
            finish();
        }



        super.onBackPressed();
    }
}
