package com.danyal.findabait;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;

public class SplashScreenMain extends AppCompatActivity  {


    SharedPreferences sharedPreferences;
    boolean isLogin;


    ImageView btn_started;
//    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
//    LocationRequest mLocationRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash1);

//        SystemBarTintManager tintManager = new SystemBarTintManager(this);
//        tintManager.setNavigationBarTintResource(R.color.black);

//        checkLocationPermission();

        Window window = this.getWindow();
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
        isLogin = sharedPreferences.getBoolean("isLogin", false);


//// finally change the color
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
//        {
//            window.setStatusBarColor(ContextCompat.getColor(SplashScreenMain.this,R.color.colorPrimaryDark));
//        }


        if (!isLogin) //if login is false

        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    final Intent mainIntent = new Intent(SplashScreenMain.this, SplashScreen.class);
                    startActivity(mainIntent);
                    finish();
                }
            }, 1000);
        }

        else
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    final Intent mainIntent = new Intent(SplashScreenMain.this, Home_Screen.class);
                    startActivity(mainIntent);
                    finish();
                }
            }, 1000);

        }






//        if (checkLocationPermission())
//        {
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    final Intent mainIntent = new Intent(SplashScreenMain.this, SplashScreen.class);
//                    startActivity(mainIntent);
//                    finish();
//                }
//            }, 5000);
//        }
//
//        else
//        {
//            //clicked.setVisibility(View.GONE);
//            Toast.makeText(this, "No Permission Grantd..!!", Toast.LENGTH_SHORT).show();
//        }



    }
//
//    public boolean checkLocationPermission() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
//        {
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION))
//            {
//                Toast.makeText(this, "Already Giveb", Toast.LENGTH_SHORT).show();
//            }
//
//            else
//            {
//                // No explanation needed, we can request the permission.
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
//                Toast.makeText(this, " Giveb", Toast.LENGTH_SHORT).show();
//
//            }
//            return false;
//        }
//
//        else {
//            return true;
//        }
//    }







//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case MY_PERMISSIONS_REQUEST_LOCATION: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    // permission was granted, yay! Do the
//                    // location-related task you need to do.
//                    if (ContextCompat.checkSelfPermission(this,
//                            Manifest.permission.ACCESS_FINE_LOCATION)
//                            == PackageManager.PERMISSION_GRANTED) {
//
//
//                        mLocationRequest = new LocationRequest();
//                        mLocationRequest.setInterval(1000);
//                        mLocationRequest.setFastestInterval(1000);
//                        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
//
////                        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
//
//                        //Request location updates:
////                        locationManager.requestLocationUpdates(provider, 400, 1, this);
//                    }
//
//                } else {
//
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//
//                }
//                return;
//            }
//
//        }
//    }
//
//
//    @Override
//    public void onConnected(@Nullable Bundle bundle) {
//
//    }
//
//    @Override
//    public void onConnectionSuspended(int i) {
//
//    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }

}
