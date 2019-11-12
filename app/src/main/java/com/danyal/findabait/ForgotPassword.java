package com.danyal.findabait;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
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

import com.rahman.dialog.Activity.SmartDialog;
import com.rahman.dialog.ListenerCallBack.SmartDialogClickListener;

import com.rahman.dialog.Utilities.SmartDialogBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class ForgotPassword extends BaseActivity {

    TextView back_login;
    ImageView home_forgot_password,about_us_forgot_password;
    EditText forgot_email;
    Button login_btn;String forgot;

    Typeface face,face2;
    JSONObject json;
    String value3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        home_forgot_password = findViewById(R.id.homess);
        about_us_forgot_password = findViewById(R.id.about_us);

        forgot_email = findViewById(R.id.forgot_email);


        face = Typeface.createFromAsset(ForgotPassword.this.getAssets(),"ptsanswebbold.ttf");
        face2 = Typeface.createFromAsset(ForgotPassword.this.getAssets(),"ptsanswebregular.ttf");

        back_login = findViewById(R.id.back_login);
        login_btn = findViewById(R.id.login_btn);

        home_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ForgotPassword.this , ThirdScreen.class));
//                finish();
            }
        });

        about_us_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ForgotPassword.this , AboutUsActivity.class));
//                finish();
            }
        });


        back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ForgotPassword.this , LoginActivity.class));
//                finish();
            }
        });




login_btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        pDialog = Utilss.showSweetLoader(ForgotPassword.this, SweetAlertDialog.PROGRESS_TYPE, "Reset Password...");
        forgot = forgot_email.getText().toString();

        if (forgot.equals(""))
        {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Utilss.hideSweetLoader(pDialog);
                }
            });

//            AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPassword.this);
//            builder.setMessage("Error Message").setMessage("Fill Up The Email Address")
//                    .setCancelable(false)
//                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            //do things
//
//                        }
//                    });
//            AlertDialog alert = builder.create();
//            alert.show();

            new SmartDialogBuilder(ForgotPassword.this)
                    .setTitle("Error Message")
                    .setSubTitle("Fill Up The Email Address")
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
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("email", forgot);
                Log.d("HHHHH" , "            "      +   forgot          );

                OkHttpClient client = new OkHttpClient();
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                // put your json here
                RequestBody body = RequestBody.create(JSON, jsonObject.toString());
                okhttp3.Request request = new okhttp3.Request.Builder() .url("http://api.bms.dwtdemo.com/api/v1/account/recovery").post(body).addHeader("Content-Type", "application/json").build();


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

                        Toast.makeText(ForgotPassword.this, "Error in retreival", Toast.LENGTH_SHORT).show();
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


//                                        AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPassword.this);
//                                        builder.setMessage("Confirmation Message").setMessage(value3)
//                                                .setCancelable(false)
//                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                    public void onClick(DialogInterface dialog, int id) {
//                                                        //do things
//
//                                                        startActivity(new Intent(ForgotPassword.this , LoginActivity.class));
//                                                        finish();
//
//                                                    }
//                                                });
//                                        AlertDialog alert = builder.create();
//                                        alert.show();

                                        new SmartDialogBuilder(ForgotPassword.this)
                                                .setTitle("Confirmation Message")
                                                .setSubTitle(value3)
                                                .setCancalable(true)
                                                .setTitleFont(face)
                                                .setSubTitleFont(face2)
                                                .setPositiveButton("OK", new SmartDialogClickListener() {
                                                    @Override
                                                    public void onClick(SmartDialog smartDialog) {
                                                        smartDialog.dismiss();

                                                        startActivity(new Intent(ForgotPassword.this , LoginActivity.class));
                                                        finish();

                                                    }
                                                }).build().show();

                                    }
                                });





                            }

                            else if (response.code()==404)
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


//                                        AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPassword.this);
//                                        builder.setMessage("Error Message").setMessage(value3)
//                                                .setCancelable(false)
//                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                    public void onClick(DialogInterface dialog, int id) {
//                                                        //do things
//
//                                                    }
//                                                });
//                                        AlertDialog alert = builder.create();
//                                        alert.show();

                                        new SmartDialogBuilder(ForgotPassword.this)
                                                .setTitle("Error Message")
                                                .setSubTitle(value3)
                                                .setCancalable(true)
                                                .setTitleFont(face)
                                                .setSubTitleFont(face2)
                                                .setPositiveButton("OK", new SmartDialogClickListener() {
                                                    @Override
                                                    public void onClick(SmartDialog smartDialog) {
                                                        smartDialog.dismiss();

//                                                        startActivity(new Intent(ForgotPassword.this , LoginActivity.class));
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










    }
});


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
        startActivity(new Intent(ForgotPassword.this , ThirdScreen.class));
        finish();


    }
}
