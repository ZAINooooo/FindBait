package com.danyal.findabait;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rahman.dialog.Activity.SmartDialog;
import com.rahman.dialog.ListenerCallBack.SmartDialogClickListener;
import com.rahman.dialog.Utilities.SmartDialogBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

import static android.R.layout.simple_spinner_item;
import static com.google.android.gms.common.util.IOUtils.copyStream;
import static java.security.AccessController.getContext;

public class Submit_Request extends BaseActivity  implements AdapterView.OnItemSelectedListener{

    public static  EditText edittext,edittext2;
   ImageView home_submit_Request,about_us_submit_Request , images;

   TextView date_value22;
   String  name,image;
   int id,building_ids ;
   TextView date_value;
    ImageView homess,about_us,rate_us ,   image2;
    Spinner spLeaveSubject2;

    String encodedImage;
    String v;
    JSONObject json;
    String value3;


    EditText editss5;

    String imageCamera;
    String imgGallery;

    ImageView image3;
File finalFile;

Uri selectedImage,tempUri;
    private static final int CAMERA_REQUEST = 1888;
    //    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_IMAGE_GALLAREY = 0;

    String mCurrentPhotoPath;

File imagess;


String access_token;
    SharedPreferences sharedPreferences;
    boolean isLogin;
    private ArrayList<String> names9 = new ArrayList<String>();
    int value;
    ArrayAdapter<String> spinnerArrayAdapter;
    int tanent , realestate;
    Typeface face,face2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit__request);

        sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
        isLogin = sharedPreferences.getBoolean("isLogin", false);
        access_token = sharedPreferences.getString("token", "");

        tanent = sharedPreferences.getInt("tenantIds", 0);
        realestate = sharedPreferences.getInt("realStateIds", 0);

        face = Typeface.createFromAsset(Submit_Request.this.getAssets(),"ptsanswebbold.ttf");
        face2 = Typeface.createFromAsset(Submit_Request.this.getAssets(),"ptsanswebregular.ttf");


        name = getIntent().getStringExtra("service_name");
        image = getIntent().getStringExtra("service_image");
        id = getIntent().getIntExtra("service_id" , 0);
        building_ids = getIntent().getIntExtra("building_id" , 0);

        Log.d("nameimageid" , name+    "           "           + "               " +    image +             "                 "+ building_ids);


        homess = findViewById(R.id.homess);
        editss5 = findViewById(R.id.editss5);

        about_us = findViewById(R.id.about_us);
        rate_us = findViewById(R.id.rate_us);
        image3 = findViewById(R.id.image3);

        image2 = findViewById(R.id.image2);
        date_value= findViewById(R.id.date_value);


        date_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });


//        home_submit_Request = findViewById(R.id.homess);
        date_value22 = findViewById(R.id.date_value22);
        date_value22.setText(name);
        images = findViewById(R.id.image);

        Glide.with(Submit_Request.this).load(image).diskCacheStrategy( DiskCacheStrategy.ALL ).override(1080, 600).into(images);

        spLeaveSubject2 = (Spinner) findViewById(R.id.spLeaveSubject2);
        spLeaveSubject2.setOnItemSelectedListener(Submit_Request.this);

        edittext = findViewById(R.id.editss4);
        edittext2 = findViewById(R.id.editss);

        edittext.setInputType(InputType.TYPE_NULL);
        edittext2.setInputType(InputType.TYPE_NULL);


        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                final View layout = inflater.inflate(R.layout.custom_dialog, null);
                final Vector<AlertDialog> listOfDialogs = new Vector<AlertDialog>();

                android.app.AlertDialog.Builder aDialog = new android.app.AlertDialog.Builder(Submit_Request.this);

                Button imageSelector = layout.findViewById(R.id.gallery);
                Button camera = layout.findViewById(R.id.camera);
                final AlertDialog dialog = aDialog.create();

                aDialog.setView(layout);
                final android.app.AlertDialog ad = aDialog.create();
                ad.show();


                imageSelector.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        galleryIntent();
                        ad.dismiss();
                    }
                });


                camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dispatchTakePictureIntent();
                        ad.dismiss();
                    }
                });
            }
        });

        homess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!isLogin) //if login is false
                {
                    startActivity(new Intent(Submit_Request.this , ThirdScreen.class));
                    finish();
                }

                else
                {
                    startActivity(new Intent(Submit_Request.this, Home_Screen.class));
                    finish();
                }

            }
        });


        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Submit_Request.this , AboutUsActivity.class));
                finish();
            }
        });

//
//
//

//
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submitapi();

//                startActi
//              vity(new Intent(Submit_Request.this , SuccessActivity.class));
//                finish();
            }
        });
//
////        DialogFragment newFragment = new selected_date_fragment1();
////        newFragment.show(getSupportFragmentManager(), "date picker");
//
//
        edittext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new selected_date_fragment1();
                newFragment.show(getSupportFragmentManager(), "date picker");
            }

        });







        edittext.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        TimePickerFragment timePicker = new TimePickerFragment(edittext, Submit_Request.this);
        timePicker.show(getSupportFragmentManager(), "Pick Time");
    }
});

        getWindow().setStatusBarColor(Color.TRANSPARENT);


        retreiveflats();
    }

    private void submitapi() {

        if (edittext.getText().toString().equals("") && edittext2.getText().toString().equals(""))
        {
            new SmartDialogBuilder(Submit_Request.this)
                    .setTitle("Error Message")
                    .setSubTitle("Date And Time Is Required..!!")
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

        else if (edittext.getText().toString().equals("") || edittext2.getText().toString().equals(""))
        {
            new SmartDialogBuilder(Submit_Request.this)
                    .setTitle("Error Message")
                    .setSubTitle("Date And Time Is Required..!!")
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
            pDialog = Utilss.showSweetLoader(Submit_Request.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("TenantID", tanent);
                jsonObject.put("RealStateID", realestate);
                jsonObject.put("BuildingID", building_ids );
                jsonObject.put("FlatNo", v);
                jsonObject.put("ServiceDate", edittext2.getText().toString());
                jsonObject.put("AvailableTime", edittext.getText().toString());
                jsonObject.put("Description", editss5.getText().toString());
                jsonObject.put("Picture", encodedImage);

                OkHttpClient client = new OkHttpClient();
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                // put your json here
                RequestBody body = RequestBody.create(JSON, jsonObject.toString());
                okhttp3.Request request = new okhttp3.Request.Builder() .url("http://api.bms.dwtdemo.com/api/v1/Services/"+id+"/requests").post(body)
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

                        Toast.makeText(Submit_Request.this, "Error in retreival", Toast.LENGTH_SHORT).show();



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

                                        new SmartDialogBuilder(Submit_Request.this)
                                                .setTitle("Confirmation Message")
                                                .setSubTitle(value3)
                                                .setCancalable(true)
                                                .setTitleFont(face)
                                                .setSubTitleFont(face2)
                                                .setPositiveButton("OK", new SmartDialogClickListener() {
                                                    @Override
                                                    public void onClick(SmartDialog smartDialog) {
                                                        smartDialog.dismiss();

//                                                    imageCamera.setText("");

                                                        encodedImage="";
                                                        edittext.setText("");
                                                        edittext2.setText("");
                                                        editss5.setText("");
                                                        startActivity(new Intent(Submit_Request.this , SuccessActivity.class));
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

    public void dispatchTakePictureIntent() {


        if (ActivityCompat.checkSelfPermission(Submit_Request.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(Submit_Request.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(Submit_Request.this, new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 22);
        }

        else
        {
            Intent camera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            Submit_Request.this.startActivityForResult(camera, REQUEST_TAKE_PHOTO);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }


    private void retreiveflats()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://api.bms.dwtdemo.com/api/v1/buildings/"+building_ids+"/flats", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Log.d("ResponseIs" , response);

                Log.d("strrrrr", ">>" + response);

                try {

//                        JSONArray obj = new JSONArray(response);
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject jsonObjCurrently= jsonObject.getJSONObject("data");
                    JSONArray obj = jsonObjCurrently.getJSONArray("flats");

//                        lstAnime2 = new ArrayList<>();

                    for (int i = 0; i < obj.length(); i++) {

//                            lstAnime2.clear();
                         value=obj.getInt(i);
                        Log.e("json", ""+value);

                        names9.add(""+value);
                    }

                    spinnerArrayAdapter = new ArrayAdapter<>(Submit_Request.this, simple_spinner_item, names9);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spLeaveSubject2.setAdapter(spinnerArrayAdapter);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

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


        v = (String) spLeaveSubject2.getSelectedItem();
//        ki = v.getLeaveCode();
        Log.d("spinnervalue" ,""+v);
//        Log.d("spinnerName" , v.getLeaveName());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint({"StaticFieldLeak", "CommitPrefEdits", "ApplySharedPref"})
    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
//        For Camera option
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO) {
            if (resultCode == RESULT_OK) {

//                Bitmap photo = (Bitmap) data.getExtras().get("data");
//                // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
//                Uri tempUri = getImageUri(getApplicationContext(), photo);
//
//                Log.d("Capture_Uri", "" + tempUri);
//                // CALL THIS METHOD TO GET THE ACTUAL PATH
//                finalFile = new File(getRealPathFromURI(tempUri));
//                Log.d("FilePath", "" + finalFile);

//                Uri filePath = data.getData();

                Bitmap photo = (Bitmap) data.getExtras().get("data");
                Bitmap lastBitmap;
                lastBitmap = photo;
                //encoding image to string
                imageCamera = getStringImage(lastBitmap);
//                logLargeString(imageCamera);
//                Log.d("imagesssssss22",imageCamera);
                //passing the image to volley
//                    SendImage(image);
            }
        }

        Log.i("selector", "before result");


        if (requestCode == REQUEST_IMAGE_GALLAREY) {
            if (resultCode == RESULT_OK) {

                Uri filePath = data.getData();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                    Bitmap lastBitmap;
                    lastBitmap = bitmap;
                    //encoding image to string
                    imageCamera = getStringImage(lastBitmap);
//                    logLargeString(imageCamera);
//                    Log.d("imagesssssss",imageCamera);
                    //passing the image to volley
//                    SendImage(image);

                } catch (IOException e) {
                    e.printStackTrace();
                }

//                Log.i("selector22222", "in result");
//                Log.i("onActivityResult22222", "in gallery activity");
//                selectedImage = data.getData();
//
//                Log.d("SelectedPath", selectedImage.toString());
//
//                if (selectedImage != null) {
//                    this.getContentResolver().takePersistableUriPermission(selectedImage, Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//                }
//
//                tempUri = selectedImage;
//                Log.d("Selected_Image", "" + tempUri);


            }
        }

    }



    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
         encodedImage = Base64.encodeToString(imageBytes, Base64.NO_WRAP);
        Log.d("FileImagePath1ssss222", encodedImage);

        return encodedImage;

    }

    private void logLargeString(String content) {
        if (content.length() > 3000) {
            Log.d("mi22222", content.substring(0, 3000));
            logLargeString(content.substring(3000));
        } else {
            Log.d("mi111", content);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private File createImageFile1() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        Log.d("FilePathImageName1", imageFileName);
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        imagess = File.createTempFile(imageFileName,  /* prefix */".jpg",         /* suffix */storageDir);    /* directory */
        Log.d("FileImage1", "" + imagess);
        // Save a file: path for use with ACTION_VIEW intents
        String mCurrentPhotoPath = imagess.getAbsolutePath();
        Log.d("FileImagePath1", mCurrentPhotoPath);
        return imagess;
    }


    public void galleryIntent() {
//        Intent pickPhoto = new Intent(Intent.ACTION_OPEN_DOCUMENT, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        pickPhoto.setType("image/*");
//        startActivityForResult(pickPhoto, 0);//one can be replaced with any action code

//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("image/*");
//        startActivityForResult(intent, REQUEST_IMAGE_GALLAREY);

        Intent pickImageIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickImageIntent.setType("image/*");
        pickImageIntent.putExtra("aspectX", 1);
        pickImageIntent.putExtra("aspectY", 1);
        pickImageIntent.putExtra("scale", true);
        pickImageIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(pickImageIntent, REQUEST_IMAGE_GALLAREY);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }


    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (getContentResolver() != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }




}
