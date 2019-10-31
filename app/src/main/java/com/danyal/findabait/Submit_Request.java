package com.danyal.findabait;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import java.util.Objects;

import static java.security.AccessController.getContext;

public class Submit_Request extends AppCompatActivity {

   static EditText edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit__request);


        edittext = findViewById(R.id.edittext);
        edittext.setInputType(InputType.TYPE_NULL);



//        DialogFragment newFragment = new selected_date_fragment1();
//        newFragment.show(getSupportFragmentManager(), "date picker");


edittext.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        TimePickerFragment timePicker = new TimePickerFragment(edittext, Submit_Request.this);
        timePicker.show(getSupportFragmentManager(), "Pick Time");
    }
});

        getWindow().setStatusBarColor(Color.TRANSPARENT);
    }
}
