package com.danyal.findabait;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class SuccessActivity extends AppCompatActivity {

    LinearLayout back_pressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        getWindow().setStatusBarColor(Color.TRANSPARENT);

        back_pressed = findViewById(R.id.back_pressed);

        back_pressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
    }
}
