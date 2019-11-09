package com.danyal.findabait;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rahman.dialog.Activity.SmartDialog;
import com.rahman.dialog.ListenerCallBack.SmartDialogClickListener;
import com.rahman.dialog.Utilities.SmartDialogBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class TicketDetail extends BaseActivity implements AdapterView.OnItemSelectedListener{

    Button job_done;
    RatingBar simpleRatingBar;
    String access_token, statusCode;
    SharedPreferences sharedPreferences;
    boolean isLogin;
    ImageView about_us,homess;
    JSONObject jsonObject;
int service_ids;

LinearLayout lin,job_work;
ImageView image_show;

TextView start_date , end_date;

Spinner spLeaveSubject;
String v;
EditText type_something;
TextView namess, statuss,idss,states;
    JSONObject json;
    String value4;

    Typeface face,face2;
    String serviceRequestNo,name,logo,status,state,startDate,endDate,technical_rating;
    int rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);

        sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
        isLogin = sharedPreferences.getBoolean("isLogin", false);
        access_token = sharedPreferences.getString("token", "");

        service_ids = getIntent().getIntExtra("service_id" , 0);


        face = Typeface.createFromAsset(TicketDetail.this.getAssets(),"ptsanswebbold.ttf");
        face2 = Typeface.createFromAsset(TicketDetail.this.getAssets(),"ptsanswebregular.ttf");

        spLeaveSubject = findViewById(R.id.spLeaveSubject);
        spLeaveSubject.setOnItemSelectedListener(TicketDetail.this);


        lin = findViewById(R.id.lin);
        job_work = findViewById(R.id.job_work);


        about_us = findViewById(R.id.about_us);
        homess = findViewById(R.id.homess);


        start_date = findViewById(R.id.start_date);
        end_date = findViewById(R.id.end_date);

        namess = findViewById(R.id.namess);
        statuss = findViewById(R.id.statuss);
        idss = findViewById(R.id.idss);
        states = findViewById(R.id.states);
        image_show = findViewById(R.id.image_show);


        simpleRatingBar = (RatingBar) findViewById(R.id.ratingBar);

        type_something = findViewById(R.id.type_something);

        job_done= findViewById(R.id.job_done);



        lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });


        homess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isLogin) //if login is false
                {
                    startActivity(new Intent(TicketDetail.this , ThirdScreen.class));
                    finish();
                }

                else
                {
                    startActivity(new Intent(TicketDetail.this, Home_Screen.class));
                    finish();
                }

            }
        });

        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(TicketDetail.this , AboutUsActivity.class));
                finish();
            }
        });




        simpleRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser)
            {
//                txtRatingValue.setText(String.valueOf(rating));

            }
        });



        job_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                startActivity( new Intent( TicketDetail.this , SuccessActivity.class));
//                finish();
//                Toast.makeText(TicketDetail.this, String.valueOf(simpleRatingBar.getRating()), Toast.LENGTH_SHORT).show();
                submitapi();

            }
        });


        getValues();



    }

    private void submitapi()
    {
        pDialog = Utilss.showSweetLoader(TicketDetail.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting Data...");

        JSONObject jsonObject = new JSONObject();
        try {

            Log.d("Vlssss343" , simpleRatingBar.getRating() + "                  " + type_something.getText().toString() + "              " + v + "             ");

            jsonObject.put("Rating",simpleRatingBar.getRating());
            jsonObject.put("Comments",type_something.getText().toString() );
            jsonObject.put("Status", v  );

            OkHttpClient client = new OkHttpClient();
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            // put your json here
            RequestBody body = RequestBody.create(JSON, jsonObject.toString());
            okhttp3.Request request = new okhttp3.Request.Builder() .url("http://api.bms.dwtdemo.com/api/v1/services/requests/"+service_ids+"/feedback").post(body)
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

                    Toast.makeText(TicketDetail.this, "Error in retreival", Toast.LENGTH_SHORT).show();



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
                            value4 = json.getString("message");
                            Log.d("Vlsss" , value4);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    new SmartDialogBuilder(TicketDetail.this)
                                            .setTitle("Confirmation Message")
                                            .setSubTitle(value4)
                                            .setCancalable(true)
                                            .setTitleFont(face)
                                            .setSubTitleFont(face2)
                                            .setPositiveButton("OK", new SmartDialogClickListener() {
                                                @Override
                                                public void onClick(SmartDialog smartDialog) {
                                                    smartDialog.dismiss();

//                                                    imageCamera.setText("");

                                                    startActivity(new Intent(TicketDetail.this , Home_Screen.class));
                                                        finish();
                                                }
                                            }).build().show();

                                }
                            });

                        }

                        else if (response.code()==500)
                        {
                            json = new JSONObject(responses);
                           JSONObject json2 = json.getJSONObject("data");
                            value4 = json2.getString("message");

                            Log.d("Vlsss22" , value4);


                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Utilss.hideSweetLoader(pDialog);
                                }
                            });

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    new SmartDialogBuilder(TicketDetail.this)
                                            .setTitle("Error Message")
                                            .setSubTitle(value4)
                                            .setCancalable(true)
                                            .setTitleFont(face)
                                            .setSubTitleFont(face2)
                                            .setPositiveButton("OK", new SmartDialogClickListener() {
                                                @Override
                                                public void onClick(SmartDialog smartDialog) {
                                                    smartDialog.dismiss();

//                                                    imageCamera.setText("");

//                                                    startActivity(new Intent(TicketDetail.this , Home_Screen.class));
//                                                        finish();
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

    private void getValues()
    {
        com.android.volley.RequestQueue queue = Volley.newRequestQueue(TicketDetail.this);
        String url = "http://api.bms.dwtdemo.com/api/v1/en/services/requests/"+service_ids;
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try
                        {
                             jsonObject = new JSONObject(response);
                            JSONObject jsonObjCurrently= jsonObject.getJSONObject("data");
                            JSONObject jsonObjCurrently2= jsonObjCurrently.getJSONObject("services");

                            serviceRequestNo = jsonObjCurrently2.getString("serviceRequestNo");

                            name = jsonObjCurrently2.getString("name");
                            logo = jsonObjCurrently2.getString("logo");
                            status = jsonObjCurrently2.getString("status");
                            state = jsonObjCurrently2.getString("state");
                            startDate = jsonObjCurrently2.getString("startDate");
                            endDate = jsonObjCurrently2.getString("endDate");

                            rating = jsonObjCurrently2.getInt("rating");
                            technical_rating = jsonObjCurrently2.getString("technicianComment");


                            float val = (float) rating;
                            simpleRatingBar.setRating(val);
                            type_something.setText(technical_rating);




                            namess.setText(name);
                            statuss.setText(status);
                            idss.setText(serviceRequestNo);

                            if (state.equals("Closed"))
                            {
                                simpleRatingBar.setIsIndicator(true);
                                type_something.setEnabled(false);
                                type_something.setClickable(false);
                                type_something.setFocusable(false);
                                type_something.setTextColor(Color.BLACK);
                                job_work.setVisibility(View.GONE);
                                job_done.setVisibility(View.GONE);
                            }
                            else
                            {
                                simpleRatingBar.setIsIndicator(false);
                                type_something.setEnabled(true);
                                type_something.setClickable(true);
                                type_something.setFocusable(true);

                                job_work.setVisibility(View.VISIBLE);
                                job_done.setVisibility(View.VISIBLE);


                            }

                            states.setText(state);

                            start_date.setText(startDate);
                            end_date.setText(endDate);

                            Glide.with(TicketDetail.this).load(logo).diskCacheStrategy( DiskCacheStrategy.ALL ).override(1080, 600).into(image_show);

                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }


                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError error) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Log.d("Error_Fetch", error.toString());
//                            Utilss.hideSweetLoader(pDialog);
                    }

                });

            }
        }) {
            @Override
            public Map getHeaders() {
                HashMap headers = new HashMap();
                headers.put("Authorization", "Bearer "+access_token);
                return headers;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                statusCode = String.valueOf(response.statusCode);
                Log.d("StatusCode3", statusCode);
                //Handling logic
                return super.parseNetworkResponse(response);
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        v = spLeaveSubject.getSelectedItem().toString();
        Log.d("Spinnerss" , v);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
