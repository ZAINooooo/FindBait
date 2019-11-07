package com.danyal.findabait;

import androidx.annotation.RequiresApi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.rahman.dialog.Activity.SmartDialog;
import com.rahman.dialog.ListenerCallBack.SmartDialogClickListener;
import com.rahman.dialog.Utilities.SmartDialogBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends BaseActivity {
    Date parsed = null;
    TextView forgot_password;
    EditText email,password;
String emailaddress , passwowrdfield,emailPattern;
    ImageView about_us_login,home_login;
    OkHttpClient client;
String responses2,value3,valueError;
    static  String value2;
    Button login_btn;
    boolean isLogin;

    SharedPreferences sharedPreferences;
    Typeface face,face2;


//    SharedPreferences sharedPreferences;
//    boolean isLogin;

    MaterialStyledDialog.Builder dialogHeader_3;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        client = new OkHttpClient();

         face = Typeface.createFromAsset(LoginActivity.this.getAssets(),"ptsanswebbold.ttf");
         face2 = Typeface.createFromAsset(LoginActivity.this.getAssets(),"ptsanswebregular.ttf");

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


        forgot_password = findViewById(R.id.forgot_password);
//        date_value = findViewById(R.id.date_value);

        about_us_login = findViewById(R.id.about_us);
        home_login = findViewById(R.id.homess);


        sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);


        home_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this , ThirdScreen.class));
                finish();
            }
        });

        about_us_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this , AboutUsActivity.class));
                finish();
            }
        });



        login_btn = findViewById(R.id.login_btn);


//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try
//        {
//            parsed = format.parse("2011-03-01 15:10:37");
//            TimeZone tz = TimeZone.getTimeZone("Asia/Dubai");
//            format.setTimeZone(tz);
//            String result = format.format(parsed);
//            Log.d("Result" , result);
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
// => This time is in the user phone timezone, you will maybe need to turn it in UTC!



        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                 emailaddress = email.getText().toString();
                passwowrdfield = password.getText().toString();

                if (emailaddress.equals("") || passwowrdfield.equals(""))
                {
//                    Toast.makeText(LoginActivity.this, "Fill Up The credentials", Toast.LENGTH_SHORT).show();

//                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                    builder.setMessage("Fill Up The credentials both")
//                            .setCancelable(false)
//                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    //do things
//
//
//                                }
//                            });
//                    AlertDialog alert = builder.create();
//                    alert.show();

                    new SmartDialogBuilder(LoginActivity.this)
                            .setTitle("Error Message")
                            .setSubTitle("Credentials Required")
                            .setCancalable(true)
                            .setTitleFont(face)
                            .setSubTitleFont(face2)
                            .setPositiveButton("OK", new SmartDialogClickListener(){
                                @Override
                                public void onClick(SmartDialog smartDialog) {
                                    smartDialog.dismiss();
                                }
                            }).build().show();


                }

                else if (emailaddress.equals("") && passwowrdfield.equals(""))
                {
//                    Toast.makeText(LoginActivity.this, "Fill Up The credentials both", Toast.LENGTH_SHORT).show();

                    new SmartDialogBuilder(LoginActivity.this)
                            .setTitle("Error Message")
                            .setSubTitle("Both Credentials Required")
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

                else if (!emailaddress.matches(emailPattern))
                {
//                    Toast.makeText(LoginActivity.this, "Wrong Email Format.!!", Toast.LENGTH_SHORT).show();

//                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                    builder.setMessage("Wrong Email Format")
//                            .setCancelable(false)
//                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    //do things
//
//
//                                }
//                            });
//                    AlertDialog alert = builder.create();
//                    alert.show();

                    new SmartDialogBuilder(LoginActivity.this)
                            .setTitle("Error Message")
                            .setSubTitle("Wrong Email Format")
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
                    login();
                }


//                startActivity(new Intent(LoginActivity.this , Home_Screen.class));
//                finish();
            }
        });





        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(LoginActivity.this , ForgotPassword.class));
//                finish();
            }
        });
//        SystemBarTintManager tintManager = new SystemBarTintManager(this);
//        tintManager.setNavigationBarTintResource(R.color.black);
        Window window = this.getWindow();
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    private void login()
    {
        pDialog = Utilss.showSweetLoader(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Loging...");
        RequestBody formBody = new FormBody.Builder().add("grant_type", "password").add("username", emailaddress).add("password", passwowrdfield).build();

        final Request request = new Request.Builder()
                .url("http://api.bms.dwtdemo.com/api/security/token")
                .post(formBody).addHeader("Host", "api.bms.dwtdemo.com")
            .addHeader("Accept-Encoding", "gzip, deflate")
            .addHeader("Content-Length", "60")
            .addHeader("Connection", "keep-alive")
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {


                runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {
                        Utilss.hideSweetLoader(pDialog);
                    }
                });

                Log.e("HttpService", "onFailure() Request was: " + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                responses2 = response.body().string();
                if (response.code()==200)
                {
                    Log.e("response", "onResponse(): " + responses2);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Utilss.hideSweetLoader(pDialog);



                            try {
                                JSONObject jsonObject = new JSONObject(responses2);
                                value2 = jsonObject.getString("access_token");
                                value3 = jsonObject.getString("refresh_token");
                                Log.d("Token_Value", value2);

                                isLogin = true;
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("email", emailaddress);
                                editor.putString("password", passwowrdfield);

                                editor.putString("token", value2);
                                editor.putBoolean("isLogin", isLogin);
                                editor.apply();


                                startActivity(new Intent(LoginActivity.this , Home_Screen.class));
                                finish();


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }




                        }
                    });
                }

                else if (response.code()==400)
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Utilss.hideSweetLoader(pDialog);

                            try {
                                JSONObject jsonObject = new JSONObject(responses2);
                                valueError = jsonObject.getString("error_description");
                                Log.e("responseError", "onResponse2(): " + valueError);


//                                dialogHeader_3 = new MaterialStyledDialog.Builder(LoginActivity.this)
//                                        .setHeaderDrawable(R.drawable.header)
//                                        .setIcon(new IconicsDrawable(LoginActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
//                                        .withDialogAnimation(true)
//                                        .setTitle("Error Message")
//                                        .setDescription(valueError)
//                                        .setPositiveText("OK")
//                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                            @Override
//                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////
////                                                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
//
//                                            }
//                                        });
//                                dialogHeader_3.show();

                                new SmartDialogBuilder(LoginActivity.this)
                                        .setTitle("Error Message")
                                        .setSubTitle(valueError)
                                        .setCancalable(true)
                                        .setTitleFont(face)
                                        .setSubTitleFont(face2)
                                        .setPositiveButton("OK", new SmartDialogClickListener() {
                                            @Override
                                            public void onClick(SmartDialog smartDialog) {
                                                smartDialog.dismiss();
                                            }
                                        }).build().show();


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });




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
//        super.onBackPressed();

        startActivity(new Intent(LoginActivity.this , ThirdScreen.class));
        finish();


    }


}
