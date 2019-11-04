package com.danyal.findabait;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Objects;

import static java.security.AccessController.getContext;

public class Submit_Request extends AppCompatActivity {

   static EditText edittext,edittext2;
   ImageView home_submit_Request,about_us_submit_Request;

    ImageView image3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit__request);



        home_submit_Request = findViewById(R.id.homess);
        about_us_submit_Request = findViewById(R.id.about_us);

        edittext = findViewById(R.id.editss4);
        edittext2 = findViewById(R.id.editss);

        edittext.setInputType(InputType.TYPE_NULL);
        edittext2.setInputType(InputType.TYPE_NULL);


        home_submit_Request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Submit_Request.this , ThirdScreen.class));
                finish();
            }
        });

        about_us_submit_Request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Submit_Request.this , AboutUsActivity.class));
                finish();
            }
        });

//
//
//
        image3 = findViewById(R.id.image3);
//
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Submit_Request.this , SuccessActivity.class));
                finish();
            }
        });
//
////        DialogFragment newFragment = new selected_date_fragment1();
////        newFragment.show(getSupportFragmentManager(), "date picker");
//
//
        edittext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new selected_date_fragment1();
                newFragment.show(getSupportFragmentManager(), "date picker");
            }

        });







        edittext.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        TimePickerFragment timePicker = new TimePickerFragment(edittext, Submit_Request.this);
        timePicker.show(getSupportFragmentManager(), "Pick Time");
    }
});

        getWindow().setStatusBarColor(Color.TRANSPARENT);
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
