package com.wherismyvehicle.whereismyvehicle.Views.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.wherismyvehicle.whereismyvehicle.Models.Sighting;
import com.wherismyvehicle.whereismyvehicle.Views.R;

import java.util.ArrayList;
import java.util.Locale;

public class SightingsAdapter extends ArrayAdapter<Sighting> {
    public SightingsAdapter(Context context, ArrayList<Sighting> sightings){
        super(context, 0, sightings);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        final Sighting sighting = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sighting_simple_list_item, parent, false);
        }

        ImageButton showLocationButton = convertView.findViewById(R.id.btn_show_location);
        showLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", sighting.getLocation().getLatitude(), sighting.getLocation().getLongitude());
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                getContext().startActivity(intent);
            }
        });
        ImageView photo = convertView.findViewById(R.id.img_vehicle);
        String encodedImage = sighting.getPhoto();

        byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        photo.setImageBitmap(decodedByte);


        // TODO: 1/19/2019 image source has to be changed and display the position in google maps or at least give a location name(street name, city etc.) ;
        return convertView;
    }

}
