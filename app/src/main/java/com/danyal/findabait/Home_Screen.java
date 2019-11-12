package com.danyal.findabait;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.rahman.dialog.Activity.SmartDialog;
import com.rahman.dialog.ListenerCallBack.SmartDialogClickListener;
import com.danyal.findabait.SmartDialogClickListener2;

import com.rahman.dialog.Utilities.SmartDialogBuilder;


import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.danyal.findabait.LoginActivity.value2;

//implements RecyclerViewAdapter.ItemListener

public class Home_Screen extends BaseActivity {

    RecyclerView recyclerView;
    ArrayList<DataModel> arrayList;

    Date parsed;
    String result;
    TextView date_value1,name2;
    ImageView feedback_image;
    ImageView contact_us;
    MaterialStyledDialog.Builder dialogHeader_3;

    SharedPreferences sharedPreferences;

    ImageView logout;

    TextView name;
    ImageView home_home, about_us_home, sevice_list;
    JSONObject jsonObject;

    String UsernameSharedPreference , PasswordeSharedPreference;
    boolean isLogin;
Typeface face,face2;

    ImageView image_show;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
        isLogin = sharedPreferences.getBoolean("isLogin", false);


        face = Typeface.createFromAsset(Home_Screen.this.getAssets(),"ptsanswebbold.ttf");
        face2 = Typeface.createFromAsset(Home_Screen.this.getAssets(),"ptsanswebregular.ttf");

        contact_us = findViewById(R.id.contact_us);

        name = findViewById(R.id.name);
        name2 = findViewById(R.id.name2);

        image_show = findViewById(R.id.image_show);
        home_home = findViewById(R.id.homess);
        about_us_home = findViewById(R.id.about_us);
        sevice_list = findViewById(R.id.sevice_list);


        home_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!isLogin) //if login is false
                {
                    startActivity(new Intent(Home_Screen.this, ThirdScreen.class));
                    finish();
                }

                else
                {
//                    startActivity(new Intent(Home_Screen.this, ThirdScreen.class));
//                    finish();
                }
            }
        });



        sevice_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Home_Screen.this , ServicesList.class));
//                finish();
//                finish();
            }
        });


        contact_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Home_Screen.this , MapActivity.class));
                finish();
//                finish();
            }
        });

        about_us_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Home_Screen.this, AboutUsActivity.class));
//                finish();
            }
        });


        date_value1 = findViewById(R.id.date_value1);
        feedback_image = findViewById(R.id.feedback_image);
        logout = findViewById(R.id.logout);


        getWindow().setStatusBarColor(Color.TRANSPARENT);


        feedback_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Home_Screen.this, FeedbackActivity.class));
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
            Log.d("Date_is44", result);

        } catch (ParseException e) {
            e.printStackTrace();
        }


        String datesssss3 = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        Log.d("Date_is2", "" + datesssss3);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            parsed = format.parse(datesssss3);
            TimeZone tz = TimeZone.getTimeZone("Asia/Dubai");
            format.setTimeZone(tz);

            result = format.format(parsed);
            Log.d("Date_is44", result);

        } catch (ParseException e) {
            e.printStackTrace();
        }


        String weekday_names22 = new SimpleDateFormat("a", Locale.ENGLISH).format(System.currentTimeMillis());
        Log.d("Date_is34", weekday_names22);

        String weekday_names = new SimpleDateFormat("a", Locale.ENGLISH).format(System.currentTimeMillis());
        Log.d("Date_is3", weekday_names);

        date_value1.setText("  " + weekday_name2 + ", " + datesssss2 + ", " + "" + result + " " + weekday_names22);



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {



                        new SmartDialogBuilder2(Home_Screen.this)
                                .setTitle("Error Message")
                                .setSubTitle("Do You Want To Logout")
                                .setCancalable(true)
                                .setTitleFont(face)
                                .setSubTitleFont(face2).setPositiveButton("OK", new SmartDialogClickListener2() {
                                    @Override
                                    public void onClick(SmartDialog2 smartDialog) {

                                                                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                        editor.putBoolean("isLogin",false);
                                        editor.apply();
                                        smartDialog.dismiss();
                                        startActivity(new Intent(Home_Screen.this , LoginActivity.class));
                                        finish();

                                    }
                                }).setNegativeButton("NO" , new SmartDialogClickListener2()
                        {
                            @Override
                            public void onClick(SmartDialog2 smartDialog) {

                                smartDialog.dismiss();
                            }
                        }).build().show();


//                        AlertDialog.Builder builder = new AlertDialog.Builder(Home_Screen.this);
//                        builder.setMessage("Do You Want To Logout")
//                                .setCancelable(true)
//                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//                                        //do things
//
//
//                                        SharedPreferences.Editor editor=sharedPreferences.edit();
//                                        editor.putBoolean("isLogin",false);
//                                        editor.apply();
//                                        startActivity(new Intent(Home_Screen.this , LoginActivity.class));
//                                        finish();
////
//
//                                    }
//                                }).setNegativeButton("No" , new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                            }
//                        });
//                        AlertDialog alert = builder.create();
//                        alert.show();

//                        dialogHeader_3 = new MaterialStyledDialog.Builder(Home_Screen.this)
//                                .setHeaderDrawable(R.drawable.header)
//                                .setIcon(new IconicsDrawable(Home_Screen.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                .withDialogAnimation(true)
//                                .setTitle("Do You Want To Logout")
//                                .setPositiveText("Yes")
//                                .setNegativeText("No")
//                                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                    @Override
//                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                                        SharedPreferences.Editor editor=sharedPreferences.edit();
//                                        editor.putBoolean("isLogin",false);
//                                        editor.apply();
//                                        startActivity(new Intent(Home_Screen.this , LoginActivity.class));
//                                        finish();
//
//                                    }
//                                }).onNegative(new MaterialDialog.SingleButtonCallback() {
//                                    @Override
//                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//
//                                    }
//                                });
//                        dialogHeader_3.show();

                    }
                });

            }
        });

        UsernameSharedPreference = sharedPreferences.getString("name", "");
        PasswordeSharedPreference = sharedPreferences.getString("photo", "");

        if (UsernameSharedPreference.equals("") && PasswordeSharedPreference.equals(""))
        {
            getName();
        }

        else
        {
            name2.setText(UsernameSharedPreference);
            Glide.with(Home_Screen.this).load(PasswordeSharedPreference).diskCacheStrategy( DiskCacheStrategy.ALL ).override(1080, 600).into(image_show);
        }


//        Glide.with(Home_Screen.this).load(R.drawable.group_70).diskCacheStrategy( DiskCacheStrategy.ALL ).override(1080, 600).into(image_show);
//    }
//
////                    Glide.with(Home_Screen.this).load(R.drawable.group_70).diskCacheStrategy( DiskCacheStrategy.ALL ).override(1080, 600).into(image_show);
//
//                    name2.setText(namess);



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

    private void getName() {
//        pDialog = Utilss.showSweetLoader(Home_Screen.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");


        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://api.bms.dwtdemo.com/api/v1/tenant/session", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Log.d("ResponseIs", response);
                Log.d("strrrrr", ">>" + response);

                try {


                    jsonObject = new JSONObject(response);
                    JSONObject jsonObjCurrently= jsonObject.getJSONObject("data");
                    JSONObject jsonObjCurrently2= jsonObjCurrently.getJSONObject("session");



                    int namess1= jsonObjCurrently2.getInt("tenantId");
                    int namess11= jsonObjCurrently2.getInt("realStateId");

                    String namess2= jsonObjCurrently2.getString("tenantName");
                    String namess3= jsonObjCurrently2.getString("tenantPhoto");

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name", namess2);
                    editor.putString("photo", namess3);

                    editor.putInt("tenantIds",namess1 );
                    editor.putInt("realStateIds",namess11 );
                    editor.apply();

//
                    if (!namess3.equals(""))
                    {
                        Glide.with(Home_Screen.this).load(namess3) .diskCacheStrategy( DiskCacheStrategy.ALL ).override(1080, 600).into(image_show);
                    }

                    else
                    {
                        Glide.with(Home_Screen.this).load(R.drawable.group_70).diskCacheStrategy( DiskCacheStrategy.ALL ).override(1080, 600).into(image_show);
                    }

//                    Glide.with(Home_Screen.this).load(R.drawable.group_70).diskCacheStrategy( DiskCacheStrategy.ALL ).override(1080, 600).into(image_show);

                    name2.setText(namess2);

                   Log.d("Namess" , namess1  +namess11  +namess2 + namess3) ;

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {


//                            Utilss.hideSweetLoader(pDialog);
                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError error) {
                        //displaying the error in toast if occurrs

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                                Log.d("ErrorIs", error.toString());
//                                Utilss.hideSweetLoader(pDialog);
                            }
                        });
                    }
                })
        {
            @Override
            public Map getHeaders() {
                HashMap headers = new HashMap();
//                headers.put("Authorization", value2);

                headers.put("Authorization", "Bearer "+value2);
                return headers;
            }


        };


                ;



        RequestQueue requestQueue = Volley.newRequestQueue(this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);

    }

    @Override
    public void onBackPressed() {


        if (!isLogin) //if login is false

        {
            super.onBackPressed();
        }

        else
        {
//                    startActivity(new Intent(Home_Screen.this, ThirdScreen.class));
//                    finish();
        }



    }
}
