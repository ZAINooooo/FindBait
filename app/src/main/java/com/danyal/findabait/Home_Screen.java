package com.danyal.findabait;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

//implements RecyclerViewAdapter.ItemListener

public class Home_Screen extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<DataModel> arrayList;

    Date parsed;
    String  result;   TextView date_value1;
ImageView feedback_image;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);




        date_value1 = findViewById(R.id.date_value1);
        feedback_image= findViewById(R.id.feedback_image);

        getWindow().setStatusBarColor(Color.TRANSPARENT);




        feedback_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Home_Screen.this , FeedbackActivity.class));
//                finish();
            }
        });
        String weekday_name2 = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(System.currentTimeMillis());  //Fruday
        Log.d("Day_is", weekday_name2);


        String datesssss2 = new SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(new Date());
        Log.d("Date_is", datesssss2);
        SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yy");
        try {
            parsed = format2.parse(datesssss2);
            TimeZone tz = TimeZone.getTimeZone("Asia/Dubai");
            format2.setTimeZone(tz);

            result = format2.format(parsed);
            Log.d("Date_is44",result);

        } catch (ParseException e) {
            e.printStackTrace();
        }





        String datesssss3 = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        Log.d("Date_is2", ""+datesssss3);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            parsed = format.parse(datesssss3);
            TimeZone tz = TimeZone.getTimeZone("Asia/Dubai");
            format.setTimeZone(tz);

            result = format.format(parsed);
            Log.d("Date_is44",result);

        } catch (ParseException e) {
            e.printStackTrace();
        }




        String weekday_names22 = new SimpleDateFormat("a", Locale.ENGLISH).format(System.currentTimeMillis());
        Log.d("Date_is34", weekday_names22);

        String weekday_names = new SimpleDateFormat("a", Locale.ENGLISH).format(System.currentTimeMillis());
        Log.d("Date_is3", weekday_names);

        date_value1.setText("  "+weekday_name2 +", " +  datesssss2 +", " +  ""+result +" " +weekday_names22);








//        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        arrayList = new ArrayList<>();
//        arrayList.add(new DataModel("Item 1", R.drawable.battle, "#09A9FF"));
//        arrayList.add(new DataModel("Item 2", R.drawable.beer, "#3E51B1"));
//        arrayList.add(new DataModel("Item 3", R.drawable.ferrari, "#673BB7"));
//        arrayList.add(new DataModel("Item 4", R.drawable.jetpack_joyride, "#4BAA50"));
//        arrayList.add(new DataModel("Item 5", R.drawable.three_d, "#F94336"));
//        arrayList.add(new DataModel("Item 6", R.drawable.terraria, "#0A9B88"));
//
//
//
//        if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
//            RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,arrayList, this);
//            GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 3);
//            int spanCount = 3; // 3 columns
//            int spacing = 50; // 50px
//            boolean includeEdge = false;
//            recyclerView.addItemDecoration(new ItemOffsetDecoration(spanCount, spacing, includeEdge));
//            recyclerView.setLayoutManager(mGridLayoutManager);
//            recyclerView.setAdapter(adapter);
//        }
//        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
//            RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,arrayList, this);
//            GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 2);
//            int spanCount = 2; // 3 columns
//            int spacing = 40; // 50px
//            boolean includeEdge = false;
//            recyclerView.addItemDecoration(new ItemOffsetDecoration(spanCount, spacing, includeEdge));
//            recyclerView.setLayoutManager(mGridLayoutManager);
//            recyclerView.setAdapter(adapter);
//        }
//        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {
//            RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,arrayList, this);
//            GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 2);
//            int spanCount = 2; // 3 columns
//            int spacing = 50; // 50px
//            boolean includeEdge = false;
//            recyclerView.addItemDecoration(new ItemOffsetDecoration(spanCount, spacing, includeEdge));
//            recyclerView.setLayoutManager(mGridLayoutManager);
//            recyclerView.setAdapter(adapter);
//        }
//        else {
//            RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,arrayList, this);
//            GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 3);
//            int spanCount = 3; // 3 columns
//            int spacing = 50; // 50px
//            boolean includeEdge = false;
//            recyclerView.addItemDecoration(new ItemOffsetDecoration(spanCount, spacing, includeEdge));
//            recyclerView.setLayoutManager(mGridLayoutManager);
//            recyclerView.setAdapter(adapter);
//        }
//
//
////        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,arrayList, this);
////        GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 3);
////        int spanCount = 3; // 3 columns
////        int spacing = 50; // 50px
////        boolean includeEdge = false;
////        recyclerView.addItemDecoration(new ItemOffsetDecoration(spanCount, spacing, includeEdge));
////        recyclerView.setLayoutManager(mGridLayoutManager);
////        recyclerView.setAdapter(adapter);
//    }
//
//
//
//
//
//
//    @Override
//    public void onItemClick(DataModel item) {
//
//        Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();
//
//    }
    }
}
