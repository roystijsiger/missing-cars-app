package com.wherismyvehicle.whereismyvehicle.Views.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wherismyvehicle.whereismyvehicle.Models.Vehicle;
import com.wherismyvehicle.whereismyvehicle.Views.R;

import java.util.ArrayList;

public class VehiclesAdapter extends ArrayAdapter<Vehicle> {
    public VehiclesAdapter(Context context, ArrayList<Vehicle> vehicles) {
        super(context, 0, vehicles);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Vehicle vehicle = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.vehicle_simple_list_item, parent, false);
        }

        TextView vehicleBrand = convertView.findViewById(R.id.txt_vehicle_brand);
        TextView vehicleType = convertView.findViewById(R.id.txt_vehicle_type);
        TextView vehicleLicense = convertView.findViewById(R.id.txt_vehicle_license);
        TextView vehicleColor = convertView.findViewById(R.id.txt_vehicle_color);

        vehicleBrand.setText(vehicle.getBrand());
        vehicleColor.setText(vehicle.getColor());
        vehicleLicense.setText(vehicle.getLicencePlate());
        vehicleType.setText(vehicle.getType().toString());

        return convertView;
    }
}
