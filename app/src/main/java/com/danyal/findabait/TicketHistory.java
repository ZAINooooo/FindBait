package com.danyal.findabait;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class TicketHistory extends AppCompatActivity {

    LinearLayout back_pressed;
    ArrayList<TicketHistoryPojo> mFlowerList;
    ImageView homess,about_us;
    SharedPreferences sharedPreferences;
    boolean isLogin;
    String access_token;
    int tanent , realestate;

    RecyclerView mRecyclerView;
    Ticket_History_Adapter dataListAdapter;
String statusCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_history);


        mFlowerList =new ArrayList<>();
        sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
        isLogin = sharedPreferences.getBoolean("isLogin", false);
        access_token = sharedPreferences.getString("token", "");
        tanent = sharedPreferences.getInt("tenantIds", 0);



        homess = findViewById(R.id.homess);
        back_pressed = findViewById(R.id.back_pressed);

        about_us = findViewById(R.id.about_us);
//        rate_us = findViewById(R.id.rate_us);

        mRecyclerView = findViewById(R.id.recyclerview);

        mRecyclerView.setHasFixedSize(true);

        dataListAdapter = new Ticket_History_Adapter(TicketHistory.this, mFlowerList);
        dataListAdapter.setDataList(mFlowerList);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(TicketHistory.this), DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(TicketHistory.this));
        mRecyclerView.setAdapter(dataListAdapter);


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
                finish();
            }
        });



        gettickethistory();

//



    }

    private void gettickethistory() {

        mFlowerList =new ArrayList<>();
        com.android.volley.RequestQueue queue = Volley.newRequestQueue(TicketHistory.this);
        String url = "http://api.bms.dwtdemo.com/api/v1/en/tenants/"+tanent+"/services/requests";
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
                                JSONObject jsonObjCurrently= jsonObject.getJSONObject("data");
                                JSONArray jsonDataset1 = jsonObjCurrently.getJSONArray("services");



                                if (jsonDataset1.length()==0)
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

                                else
                                {
                                    mFlowerList.clear();

                                    for (int i = 0; i < jsonDataset1.length(); i++) {
                                        JSONObject jsonObject3 = new JSONObject(String.valueOf(jsonDataset1.get(i)));
                                        TicketHistoryPojo dataObject = new TicketHistoryPojo();

                                        dataObject.setID(jsonObject3.getInt("id"));
                                        dataObject.setName(jsonObject3.getString("name"));
                                        dataObject.setLogo(jsonObject3.getString("logo"));
                                        dataObject.setStatus(jsonObject3.getString("status"));
                                        dataObject.setServiceRequestNo(jsonObject3.getString("serviceRequestNo"));
                                        dataObject.setState(jsonObject3.getString("state"));


                                        mFlowerList.add(dataObject);
                                        dataListAdapter.notifyDataSetChanged();


                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {

//                                                    Utilss.hideSweetLoader(pDialog);
                                            }

                                        });


                                    }

                                    dataListAdapter = new Ticket_History_Adapter(TicketHistory.this, mFlowerList);
                                    dataListAdapter.setDataList(mFlowerList);
                                    mRecyclerView.setAdapter(dataListAdapter);

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











    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
