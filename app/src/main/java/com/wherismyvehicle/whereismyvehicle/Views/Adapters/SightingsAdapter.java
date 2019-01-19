package com.wherismyvehicle.whereismyvehicle.Views.Adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wherismyvehicle.whereismyvehicle.Models.Sighting;
import com.wherismyvehicle.whereismyvehicle.Views.R;

import java.util.ArrayList;

public class SightingsAdapter extends ArrayAdapter<Sighting> {
    public SightingsAdapter(Context context, ArrayList<Sighting> sightings){
        super(context, 0, sightings);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Sighting sighting = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sighting_simple_list_item, parent, false);
        }

        TextView location = convertView.findViewById(R.id.txt_location_temp);
        TextView licensePlate = convertView.findViewById(R.id.txt_vehicle_license);
        ImageView photo = convertView.findViewById(R.id.img_vehicle);

        location.setText(sighting.getLocation().toString());
        licensePlate.setText(sighting.getVehicle().getLicencePlate());
        // TODO: 1/19/2019 image source has to be changed and display the position in google maps or at least give a location name(street name, city etc.) ;
        return convertView;
    }
}
