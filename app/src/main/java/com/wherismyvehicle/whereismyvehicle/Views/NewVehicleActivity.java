package com.wherismyvehicle.whereismyvehicle.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.wherismyvehicle.whereismyvehicle.Models.VehicleType;

public class NewVehicleActivity extends AppCompatActivity {
    private final static int PLACE_PICKER_REQUEST = 999;
    private Activity activity;
    private Context context;
    private LatLng locationSelected;

    protected GeoDataClient mGeoDataClient;
    protected PlaceDetectionClient mPlacesDetectionClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_vehicle);

        this.activity = this;
        this.context = this;

        Spinner spinner = findViewById(R.id.spinner_vehicle_type);
        spinner.setAdapter(new ArrayAdapter<VehicleType>(this,android.R.layout.simple_list_item_1, VehicleType.values()));

        mGeoDataClient = Places.getGeoDataClient(context);
        mPlacesDetectionClient = Places.getPlaceDetectionClient(context);


        //set listener for the pcik a place button
        Button locationPicker = findViewById(R.id.btn_location_picker);
        locationPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {

                    startActivityForResult(builder.build(activity), PLACE_PICKER_REQUEST);
                }
                catch(Exception e){
                    // TODO: 1/20/2019 return input to the user
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                String toastMsg = String.format("Place: %s", place.getName());
                this.locationSelected = new LatLng(place.getLatLng().latitude, place.getLatLng().longitude);

                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
            }
        }
    }

}
