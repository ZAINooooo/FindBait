package com.danyal.findabait;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;
import io.nlopez.smartlocation.location.config.LocationParams;
import io.nlopez.smartlocation.location.providers.LocationGooglePlayServicesWithFallbackProvider;

public class MapActivity extends AppCompatActivity  implements OnMapReadyCallback ,  GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    protected GoogleMap mGoogleMap;
    LocationGooglePlayServicesWithFallbackProvider provider;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    Context c;
    Bitmap bitmap;
TextView date_value;
    Date parsed;
    String  result;
    SharedPreferences sharedPreferences;
    boolean isLogin;

    ImageView map_home,map_about_us;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
        isLogin = sharedPreferences.getBoolean("isLogin", false);

        map_home = findViewById(R.id.homess);
        map_about_us = findViewById(R.id.about_us);




        date_value = findViewById(R.id.date_value);



        map_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                startActivity(new Intent(MapActivity.this , ThirdScreen.class));
//                finish();
                if (!isLogin) //if login is false

                {
                    startActivity(new Intent(MapActivity.this , ThirdScreen.class));
                    finish();
                }

                else
                {
                    startActivity(new Intent(MapActivity.this, Home_Screen.class));
                    finish();
                }

            }
        });

        map_about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MapActivity.this , AboutUsActivity.class));
//                finish();
            }
        });


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
        provider = new LocationGooglePlayServicesWithFallbackProvider(MapActivity.this);
        statusCheck();
    }




    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            showSettingsAlert();
        } else {
            SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            supportMapFragment.getMapAsync(MapActivity.this);
        }
    }

    private void showSettingsAlert() {
        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
    }


    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mGoogleMap = googleMap;

//        mGoogleMap.setMinZoomPreference(3.0f);
//        mGoogleMap.setMaxZoomPreference(5.5f);

        if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(MapActivity.this), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Objects.requireNonNull(MapActivity.this), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        } else {

        }


        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mGoogleMap.setMyLocationEnabled(true);
        zoomToCurrentPosition();

    }

    private void zoomToCurrentPosition() {
        SmartLocation.with(MapActivity.this).location(provider).oneFix().config(LocationParams.NAVIGATION).start(new OnLocationUpdatedListener() {
            @Override
            public void onLocationUpdated(Location location) {
                Log.e("onLocationUpdated", "onLocationUpdated: " + location);

                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 14));
                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));


                // Move the camera instantly to location with a zoom of 15.




//                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude(), 11));
                mGoogleMap.addMarker(new MarkerOptions()

                        .position(new LatLng(location.getLatitude(), location.getLongitude()) )
                        .title("Your Location")
                        .snippet("This Is Your Location")
                        .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("m",100,120))));


                mGoogleMap.addCircle(
                        new CircleOptions()
                                .center(new LatLng(location.getLatitude(), location.getLongitude()))
                                .radius(200.0)
                                .strokeWidth(3f)
                                .strokeColor(Color.RED)
                                .fillColor(Color.argb(70,150,50,50)));


                return;
            }
        });
    }



    public Bitmap resizeMapIcons(String iconName,int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }











    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(MapActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {


        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");

        markerOptions.icon(bitmapDescriptorFromVector(c, R.drawable.m));
        mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);
        //move map camera
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, (float) 16));

    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context c, @DrawableRes int vectorDrawableResourceId) {

        Drawable background;

        try {
            background = ContextCompat.getDrawable(c, vectorDrawableResourceId);
            background.setBounds(0, 0, background.getIntrinsicWidth(), background.getIntrinsicHeight());
            bitmap = Bitmap.createBitmap(background.getIntrinsicWidth(), background.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            background.draw(canvas);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


    @Override
    public void onBackPressed()
    {

        if (!isLogin) //if login is false

        {
            startActivity(new Intent(MapActivity.this , ThirdScreen.class));
            finish();
        }

        else
        {
            startActivity(new Intent(MapActivity.this , Home_Screen.class));
            finish();
        }




    }
}
