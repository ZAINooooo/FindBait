package com.danyal.findabait;



import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import android.util.Log;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.Manifest.permission.INTERNET;

public class TicketHistory extends AppCompatActivity {

    // Array List (String Variables)
    ArrayList<Integer> id = new ArrayList<Integer>();
    ArrayList<String> name = new ArrayList<String>();
    ArrayList<String> logo = new ArrayList<String>();
    ArrayList<String> status = new ArrayList<String>();
    ArrayList<String> serviceRequestNo = new ArrayList<String>();
    ArrayList<String> state = new ArrayList<String>();

    // Objects for Recycler View
    RecyclerView myRecView;
    Ticket_History_Adapter mAdapt;
    LinearLayoutManager manager;

    ImageView homess,about_us;
    SharedPreferences sharedPreferences;
    boolean isLogin;
    String access_token;
    LinearLayout back_pressed;
    int tanent , realestate;
String statusCode;

    int totalPages,current_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_history);


        sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
        isLogin = sharedPreferences.getBoolean("isLogin", false);
        access_token = sharedPreferences.getString("token", "");
        tanent = sharedPreferences.getInt("tenantIds", 0);

        homess = findViewById(R.id.homess);
        back_pressed = findViewById(R.id.back_pressed);
        about_us = findViewById(R.id.about_us);


        // Assigning Views
        myRecView = (RecyclerView) findViewById(R.id.my_rec_view);
//        mAdapt = new Ticket_History_Adapter();

        // Checking network and internet permissions
        if (Networking.hasPermissions(this, INTERNET) && Networking.isConnected(this)) {
            // Call Get Page Method
            getPage(1);
        } else {
            // In Case internet is not available
            Toast.makeText(this, "Not Connected to Internet", Toast.LENGTH_SHORT).show();
        }

        // Setting up Recycler View



        homess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!isLogin) //if login is false
                {
                    startActivity(new Intent(TicketHistory.this , ThirdScreen.class));
                    finish();
                }

                else
                {
                    startActivity(new Intent(TicketHistory.this, Home_Screen.class));
                    finish();
                }

            }
        });

        back_pressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });



        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(TicketHistory.this , AboutUsActivity.class));
//                finish();
            }
        });


        manager = new LinearLayoutManager(TicketHistory.this);
        myRecView.setLayoutManager(manager);
        mAdapt = new Ticket_History_Adapter(id, logo,name,status,serviceRequestNo,state, TicketHistory.this, totalPages,current_page);
        myRecView.setAdapter(mAdapt);
    }

    // Function to get page
    public void getPage(int pageNum) {
        com.android.volley.RequestQueue queue = Volley.newRequestQueue(TicketHistory.this);
        String url = "http://api.bms.dwtdemo.com/api/v1/en/tenants/"+tanent+"/services/requests?page="+pageNum;
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Json-response1", response);

                        if (statusCode.equals("200"))
                        {
//                                Toast.makeText(context, "ju", Toast.LENGTH_SHORT).show();
                            try {

                                JSONObject jsonObject = new JSONObject(response);
                                totalPages = jsonObject.getInt("totalPages");
                                current_page = jsonObject.getInt("currentPage");

                                Log.d("Total_Pages" , ""+totalPages);

                                JSONObject jsonObjCurrently= jsonObject.getJSONObject("data");
                                JSONArray body = jsonObjCurrently.getJSONArray("services");



                                if (body.length()==0)
                                {
                                    SweetAlertDialog pDialog = new SweetAlertDialog(TicketHistory.this, SweetAlertDialog.ERROR_TYPE).setConfirmButton("OK" , new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {


                                        }
                                    });

                                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                    pDialog.setTitleText("No Data Found");
                                    pDialog.setCancelable(true);
                                    pDialog.show();
                                }
//
                                else {
//                                    mFlowerList.clear();

                                    for (int i = 0; i < body.length(); i++) {
//                                        JSONObject jsonObject3 = new JSONObject(String.valueOf(jsonDataset1.get(i)));

                                        id.add(body.getJSONObject(i).getInt("id"));
                                        name.add(body.getJSONObject(i).getString("name"));
                                        logo.add(body.getJSONObject(i).getString("logo"));
                                        status.add(body.getJSONObject(i).getString("status"));
                                        serviceRequestNo.add(body.getJSONObject(i).getString("serviceRequestNo"));
                                        state.add(body.getJSONObject(i).getString("state"));
                                        mAdapt = new Ticket_History_Adapter(id, name, logo, status, serviceRequestNo, state, TicketHistory.this, totalPages, current_page);
                                        mAdapt.notifyDataSetChanged();


                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {

//                                                    Utilss.hideSweetLoader(pDialog);
                                            }

                                        });
                                    }

                                    manager = new LinearLayoutManager(TicketHistory.this);
                                    myRecView.setLayoutManager(manager);
//                                mAdapt = new Ticket_History__Adapter(id, logo,name,status,serviceRequestNo,state, TicketHistory.this, totalPages);
                                    myRecView.setAdapter(mAdapt);


//                                    dataListAdapter = new Ticket_History_Adapter(TicketHistory.this, mFlowerList);
//                                    dataListAdapter.setDataList(mFlowerList);
//                                    mRecyclerView.setAdapter(dataListAdapter);

                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

                        else if (statusCode.equals("401"))
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(TicketHistory.this);
                            builder.setMessage("Session Expired..!!")
                                    .setCancelable(true)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            //do things

//
                                            startActivity(new Intent(TicketHistory.this , LoginActivity.class));
                                            finish();
                                        }
                                    });
                            AlertDialog alert = builder.create();
                            alert.show();

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









        // Enqueuing request with singleton class
//        Singleton.getInstance(this).addToRequestQue(sr);




    }
}
