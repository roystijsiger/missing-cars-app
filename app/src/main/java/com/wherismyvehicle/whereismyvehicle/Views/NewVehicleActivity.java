package com.wherismyvehicle.whereismyvehicle.Views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.wherismyvehicle.whereismyvehicle.Models.VehicleType;

public class NewVehicleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_vehicle);

        Spinner spinner = findViewById(R.id.spinner_vehicle_type);
        spinner.setAdapter(new ArrayAdapter<VehicleType>(this,android.R.layout.simple_list_item_1, VehicleType.values()));
    }
}
