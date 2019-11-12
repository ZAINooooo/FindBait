package com.danyal.findabait;

import androidx.annotation.RequiresApi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.rahman.dialog.Activity.SmartDialog;

//import com.rahman.dialog2.ListenerCallBack.SmartDialogClickListener2;
import com.rahman.dialog.ListenerCallBack.SmartDialogClickListener;
import com.rahman.dialog.Utilities.SmartDialogBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class FeedbackActivity extends BaseActivity {
    String  result,access_token;
    Date parsed;
    TextView date_value;
    Button btnss;
    ImageView home_Feedback,about_us_Feedback;
    SharedPreferences sharedPreferences;
    boolean isLogin;
    JSONObject json;
    String value3;
    MaterialStyledDialog.Builder dialogHeader_3;

    EditText customer_info;
    int tanent , realestate;
    String info;

    Typeface face,face2;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback2);
        sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
        isLogin = sharedPreferences.getBoolean("isLogin", false);

        access_token = sharedPreferences.getString("token", "");


        tanent = sharedPreferences.getInt("tenantIds", 0);
        realestate = sharedPreferences.getInt("realStateIds", 0);



        getWindow().setStatusBarColor(Color.TRANSPARENT);


        face = Typeface.createFromAsset(FeedbackActivity.this.getAssets(),"ptsanswebbold.ttf");
        face2 = Typeface.createFromAsset(FeedbackActivity.this.getAssets(),"ptsanswebregular.ttf");

        customer_info = findViewById(R.id.customer_info);

        about_us_Feedback = findViewById(R.id.about_us);
        home_Feedback = findViewById(R.id.homess);



        btnss = findViewById(R.id.btnss);



        home_Feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isLogin) //if login is false
                {
                    startActivity(new Intent(FeedbackActivity.this , ThirdScreen.class));
                    finish();
                }

                else
                {
                    startActivity(new Intent(FeedbackActivity.this, Home_Screen.class));
                    finish();
                }


            }
        });

        about_us_Feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(FeedbackActivity.this , AboutUsActivity.class));
//                finish();
            }
        });

        btnss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submitfeedback();


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

    private void submitfeedback()
    {
         info = customer_info.getText().toString();
        if (access_token.equals(""))
        {

        }

        else if (info.equals(""))
        {
//            AlertDialog.Builder builder = new AlertDialog.Builder(FeedbackActivity.this);
//            builder.setMessage("Error Message").setMessage("Feedback is required")
//                    .setCancelable(false)
//                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            //do things
//
//                        }
//                    });
//            AlertDialog alert = builder.create();
//            alert.show();

            new SmartDialogBuilder(FeedbackActivity.this)
                    .setTitle("Error Message")
                    .setSubTitle("Feedback is required")
                    .setCancalable(true)
                    .setTitleFont(face)
                    .setSubTitleFont(face2)
                    .setPositiveButton("OK", new SmartDialogClickListener() {
                        @Override
                        public void onClick(SmartDialog smartDialog) {
                            smartDialog.dismiss();
                        }
                    }).build().show();

        }

        else
        {
            pDialog = Utilss.showSweetLoader(FeedbackActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");



            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("TenantId", tanent);
                jsonObject.put("RealStateId", realestate);
                jsonObject.put("Feedback", info);

                Log.d("HHHHH" , "            "      +   tanent  +  "       "    +     realestate+     "              " +         info   );

                OkHttpClient client = new OkHttpClient();
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                // put your json here
                RequestBody body = RequestBody.create(JSON, jsonObject.toString());
                okhttp3.Request request = new okhttp3.Request.Builder() .url("http://api.bms.dwtdemo.com/api/v1/feedback").post(body)
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Authorization", "Bearer "+access_token).build();


                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(final Call call, final IOException e) {




                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Utilss.hideSweetLoader(pDialog);

                                Log.e("HttpService", "onFailure() Request was: " + call);
                                e.printStackTrace();
                            }
                        });

                        Toast.makeText(FeedbackActivity.this, "Error in retreival", Toast.LENGTH_SHORT).show();



                    }

                    @Override
                    public void onResponse(Call call, okhttp3.Response response) throws IOException {



                        String responses = response.body().string();
                        Log.e("response", "onResponse(): " + responses);

                        try {

                            if (response.code() == 200)
                            {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Utilss.hideSweetLoader(pDialog);
                                    }
                                });

                                json = new JSONObject(responses);
                                value3 = json.getString("message");



                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

//                                        dialogHeader_3 = new MaterialStyledDialog.Builder(FeedbackActivity.this)
//                                                .setHeaderDrawable(R.drawable.header)
//                                                .setIcon(new IconicsDrawable(FeedbackActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                                .withDialogAnimation(true)
//                                                .setTitle("Confirmation Message")
//                                                .setDescription(value3)
//                                                .setPositiveText("OK")
//                                                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                                    @Override
//                                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////
////                                                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
//
//                                                        customer_info.setText("");
//                                                        startActivity(new Intent(FeedbackActivity.this , Home_Screen.class));
//                                                        finish();
//
//                                                    }
//                                                });
//                                        dialogHeader_3.show();


                                        new SmartDialogBuilder(FeedbackActivity.this)
                                                .setTitle("Confirmation Message")
                                                .setSubTitle(value3)
                                                .setCancalable(true)
                                                .setTitleFont(face)
                                                .setSubTitleFont(face2)
                                                .setPositiveButton("OK", new SmartDialogClickListener() {
                                                    @Override
                                                    public void onClick(SmartDialog smartDialog) {
                                                        smartDialog.dismiss();

                                                        customer_info.setText("");
                                                        startActivity(new Intent(FeedbackActivity.this , Home_Screen.class));
                                                        finish();

                                                    }
                                                }).build().show();

                                    }
                                });





                            }


                        }

                        catch (JSONException e)
                        {

                        }






                    }
                });




            } catch (JSONException e) {
                e.printStackTrace();
            }
        }











//        startActivity(new Intent(FeedbackActivity.this , SuccessActivity.class));
//        finish();
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
