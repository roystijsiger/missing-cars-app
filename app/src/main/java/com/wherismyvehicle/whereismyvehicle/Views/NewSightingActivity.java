package com.wherismyvehicle.whereismyvehicle.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.wherismyvehicle.whereismyvehicle.Presenters.NewSightingActivityPresenter;

import java.io.ByteArrayOutputStream;


public class NewSightingActivity extends AppCompatActivity implements NewSightingActivityPresenter.View
{
    private final static int REQUEST_IMAGE_CAPTURE = 1;
    private final static int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private boolean locationPermissionGranted;
    private String photo;
    private Location location;
    private int vehicleId;
    private Activity activity;
    private NewSightingActivityPresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sighting);
        this.vehicleId = getIntent().getIntExtra("vehicle_id", 0);
        presenter = new NewSightingActivityPresenter(this);
        this.activity = this;

        // Construct a FusedLocationProviderClient.
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLocationPermission();
        getLastLocation();

        //open camera listener
        ImageButton openCameraButton = findViewById(R.id.btn_open_camera);
        openCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        Button addSighting = findViewById(R.id.btn_add_sighting);
        addSighting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.addNewSightings(location, photo);
            }
        });
    }

    public Context getContext(){
        return this;
    }

    public int getVehicleId(){
        return vehicleId;
    }

    public void showSuccessMessage(final String message){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String toastMsg = String.format(message);
                Toast.makeText(activity, toastMsg, Toast.LENGTH_LONG).show();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream .toByteArray();
            String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
            this.photo = encoded;

            ImageView imagePreview = findViewById(R.id.img_taken_picture);
            imagePreview.setImageBitmap(imageBitmap);
        }
    }

    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        locationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationPermissionGranted = true;
                }
            }
        }
        getLastLocation();
    }

    private void getLastLocation(){
        try{
            Task locationResult = fusedLocationProviderClient.getLastLocation();
            locationResult.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location locationResult) {
                    location = locationResult;
                }
            });
        }
        catch(SecurityException e){
            // TODO: 1/20/2019 : throw the exception nicely to user
            Log.e("Error: ", e.getMessage());
        }

    }
}
