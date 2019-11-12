package com.danyal.findabait;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
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

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.R.layout.simple_spinner_item;

public class ServicesList extends BaseActivity implements AdapterView.OnItemSelectedListener{

Spinner spLeaveSubject2;
    RecyclerView mRecyclerView;
    ArrayList<ServicesCategories_Pojo> mFlowerList;
    ArrayList<ServicesCategories_Pojo> mFlowerList3;
    ArrayList<ServiceSpinner> lstAnime2;

    Button check_history;

    int spinner1;
String access_token;
    Main_Services_Adapter dataListAdapter;
    GridLayoutManager mGridLayoutManager;
    String statusCode;
    private ArrayList<String> names8 = new ArrayList<String>();
    ArrayAdapter<String> spinnerArrayAdapter;

    ImageView homess,about_us,rate_us;
    SharedPreferences sharedPreferences;
    boolean isLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_list);

        sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
        isLogin = sharedPreferences.getBoolean("isLogin", false);
        access_token = sharedPreferences.getString("token", "");

        homess = findViewById(R.id.homess);
        about_us = findViewById(R.id.about_us);
        rate_us = findViewById(R.id.rate_us);


        check_history = findViewById(R.id.check_history);

        mRecyclerView = findViewById(R.id.recyclerview);
        lstAnime2 = new ArrayList<>();

        spLeaveSubject2 = (Spinner) findViewById(R.id.spLeaveSubject2);
        spLeaveSubject2.setOnItemSelectedListener(ServicesList.this);


        mRecyclerView.setHasFixedSize(true);

        dataListAdapter = new Main_Services_Adapter(ServicesList.this, mFlowerList , spinner1);
        dataListAdapter.setDataList(mFlowerList);
//
        int spanCount = 3; // 3 columns
        int spacing = 0; // 50px
        boolean includeEdge = false;
        mRecyclerView.addItemDecoration(new ItemOffsetDecoration(spanCount, spacing, includeEdge));
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(dataListAdapter);



        check_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ServicesList.this , TicketHistory.class));
//                finish();
            }
        });
        retreivebuilding();

        homess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!isLogin) //if login is false
                {
                    startActivity(new Intent(ServicesList.this , ThirdScreen.class));
                    finish();
                }

                else
                {
                    startActivity(new Intent(ServicesList.this, Home_Screen.class));
                    finish();
                }

            }
        });



        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ServicesList.this , AboutUsActivity.class));
//                finish();
            }
        });



//        getServiceList();


    }

    private void retreivebuilding()
    {

//            pdialog = Utilss.showSweetLoader(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");


            StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://api.bms.dwtdemo.com/api/v1/tenant/session", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    Log.d("ResponseIs" , response);

                    Log.d("strrrrr", ">>" + response);

                    try {

//                        JSONArray obj = new JSONArray(response);
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject jsonObjCurrently= jsonObject.getJSONObject("data");
                        JSONObject jsonObjCurrently2= jsonObjCurrently.getJSONObject("session");
                        JSONArray obj = jsonObjCurrently2.getJSONArray("buildings");

//                        lstAnime2 = new ArrayList<>();

                        for (int i = 0; i < obj.length(); i++) {

//                            lstAnime2.clear();
                            ServiceSpinner playerModel7 = new ServiceSpinner();
                            JSONObject dataobj = obj.getJSONObject(i);

                            playerModel7.setID(dataobj.getInt("id"));
                            playerModel7.setBuildingName(dataobj.getString("buildingName"));
                            playerModel7.setBuildingNameAR(dataobj.getString("buildingNameAR"));
                            lstAnime2.add(playerModel7);
                        }

                        for (int i = 0; i < lstAnime2.size(); i++) {
                            names8.add(lstAnime2.get(i).getBuildingName());
                        }

                        spinnerArrayAdapter = new ArrayAdapter<>(ServicesList.this, simple_spinner_item, names8);
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        spLeaveSubject2.setAdapter(spinnerArrayAdapter);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


//                                Utilss.hideSweetLoader(pdialog);
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
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {


                                    Log.d("ErrorIs" , error.toString());
//                                    Utilss.hideSweetLoader(pdialog);

                                }
                            });
                        }
                    }) {
                @Override
                public Map getHeaders() {
                    HashMap headers = new HashMap();
                    headers.put("Authorization", "Bearer " + access_token);
                    return headers;
                }
            };





        RequestQueue requestQueue = Volley.newRequestQueue(this);
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(stringRequest);
        }








    private void getServiceList()
    {

        if (access_token.equals(""))
        {

        }

        else
        {

            Log.d("SpinnerValue" , ""+spinner1);
            mFlowerList3 =new ArrayList<>();
//            pDialog = Utilss.showSweetLoader(ServicesList.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");

            com.android.volley.RequestQueue queue = Volley.newRequestQueue(ServicesList.this);
            String url = "http://api.bms.dwtdemo.com/api/v1/en/Building/"+spinner1+"/Services";
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
                                        SweetAlertDialog pDialog = new SweetAlertDialog(ServicesList.this, SweetAlertDialog.ERROR_TYPE).setConfirmButton("OK" , new SweetAlertDialog.OnSweetClickListener() {
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
                                        mFlowerList3.clear();

                                        for (int i = 0; i < jsonDataset1.length(); i++) {
                                            JSONObject jsonObject3 = new JSONObject(String.valueOf(jsonDataset1.get(i)));
                                            ServicesCategories_Pojo dataObject = new ServicesCategories_Pojo();

                                            dataObject.setID(jsonObject3.getInt("id"));
                                            dataObject.setName(jsonObject3.getString("name"));
                                            dataObject.setImage(jsonObject3.getString("logo"));
                                            mFlowerList3.add(dataObject);
                                            dataListAdapter.notifyDataSetChanged();


                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {

//                                                    Utilss.hideSweetLoader(pDialog);
                                                }

                                            });


                                        }

                                        dataListAdapter = new Main_Services_Adapter(ServicesList.this, mFlowerList3 , spinner1);
                                        GridLayoutManager mGridLayoutManager = new GridLayoutManager(ServicesList.this, 3);
                                        dataListAdapter.setDataList(mFlowerList3);

                                        int spanCount = 3; // 3 columns
                                        int spacing = 0; // 50px
                                        boolean includeEdge = false;
                                        mRecyclerView.addItemDecoration(new ItemOffsetDecoration(spanCount, spacing, includeEdge));
                                        mRecyclerView.setLayoutManager(mGridLayoutManager);
                                        mRecyclerView.setAdapter(dataListAdapter);

                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }

                        else if (statusCode.equals("401"))
                        {
//                            SweetAlertDialog pDialog = new SweetAlertDialog(ServicesList.this, SweetAlertDialog.ERROR_TYPE).setConfirmButton("OK" , new SweetAlertDialog.OnSweetClickListener() {
//                                @Override
//                                public void onClick(SweetAlertDialog sweetAlertDialog) {
//
//                                    startActivity(new Intent(ServicesList.this , LoginActivity.class));
//                                    finish();
//                                }
//                            });
//
//                            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
//                            pDialog.setTitleText("Session Expired..!!");
//                            pDialog.setCancelable(true);
//                            pDialog.show();

                            AlertDialog.Builder builder = new AlertDialog.Builder(ServicesList.this);
                            builder.setMessage("Session Expired..!!")
                                    .setCancelable(true)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            //do things

//
                                    startActivity(new Intent(ServicesList.this , LoginActivity.class));
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

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

         spinner1 = lstAnime2.get(position).getID();
         getServiceList();

        Log.d("CCCC" , ""+spinner1);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
