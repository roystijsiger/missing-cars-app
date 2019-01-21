package com.wherismyvehicle.whereismyvehicle.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.wherismyvehicle.whereismyvehicle.models.VehicleType;
import com.wherismyvehicle.whereismyvehicle.presenters.NewVehicleActivityPresenter;

public class NewVehicleActivity extends AppCompatActivity implements NewVehicleActivityPresenter.View {
    private final static int PLACE_PICKER_REQUEST = 999;
    private Activity activity;
    private Context context;
    private LatLng locationSelected;
    private NewVehicleActivityPresenter presenter;

    protected GeoDataClient mGeoDataClient;
    protected PlaceDetectionClient mPlacesDetectionClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_vehicle);

        this.activity = this;
        this.context = this;
        this.presenter = new NewVehicleActivityPresenter(this);

        Spinner spinner = findViewById(R.id.spinner_vehicle_type);
        spinner.setAdapter(new ArrayAdapter<VehicleType>(this,android.R.layout.simple_list_item_1, VehicleType.values()));

        mGeoDataClient = Places.getGeoDataClient(context);
        mPlacesDetectionClient = Places.getPlaceDetectionClient(context);


        //set listener for the pick a place button
        Button locationPickerButton = findViewById(R.id.btn_location_picker);
        locationPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(activity), PLACE_PICKER_REQUEST);
                }
                catch(Exception e){
                    String toastMsg = String.format("Error: %s", e.getMessage());
                    Toast.makeText(activity, toastMsg, Toast.LENGTH_LONG).show();
                }

            }
        });


        //onclick event add vehicle
        Button addVehicleButton = findViewById(R.id.btn_add_vehicle);
        addVehicleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView licenseTextView = findViewById(R.id.txt_vehicle_license);
                String license = licenseTextView.getText().toString();

                Spinner vehicleTypeSpinner = findViewById(R.id.spinner_vehicle_type);
                String type = vehicleTypeSpinner.getSelectedItem().toString();

                TextView brandTextView = findViewById(R.id.txt_vehicle_brand);
                String brand = brandTextView.getText().toString();

                TextView colorTextView = findViewById(R.id.txt_vehicle_color);
                String color = colorTextView.getText().toString();



                presenter.addNewVehicle(license,brand,type,color, locationSelected);


            }
        });
    }

    public Context getContext() {
        return context;
    }

    public void showToast(final String message){
        final Activity activity = this;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
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
                TextView currentSelectedLocation = findViewById(R.id.txt_current_selected_location);
                currentSelectedLocation.setText(String.format("The selected last known location is %s", place.getName()));
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
            }
        }
    }

}
