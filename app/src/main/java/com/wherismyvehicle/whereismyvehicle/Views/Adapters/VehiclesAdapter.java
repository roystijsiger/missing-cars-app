package com.wherismyvehicle.whereismyvehicle.Views.Adapters;

        import android.content.Context;
        import android.content.Intent;
        import android.net.Uri;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageButton;
        import android.widget.TextView;

        import com.wherismyvehicle.whereismyvehicle.Models.Vehicle;
        import com.wherismyvehicle.whereismyvehicle.Views.NewSightingActivity;
        import com.wherismyvehicle.whereismyvehicle.Views.R;

        import java.util.ArrayList;
        import java.util.Locale;

public class VehiclesAdapter extends ArrayAdapter<Vehicle> {
    public VehiclesAdapter(Context context, ArrayList<Vehicle> vehicles) {
        super(context, 0, vehicles);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        final Vehicle vehicle = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.vehicle_simple_list_item, parent, false);
        }

        TextView vehicleBrand = convertView.findViewById(R.id.txt_vehicle_brand);
        TextView vehicleType = convertView.findViewById(R.id.txt_vehicle_type);
        TextView vehicleLicense = convertView.findViewById(R.id.txt_vehicle_license);
        TextView vehicleColor = convertView.findViewById(R.id.txt_vehicle_color);
        ImageButton showLocationButton = convertView.findViewById(R.id.btn_show_location);
        ImageButton addSightingButton = convertView.findViewById(R.id.btn_add_sighting);

        addSightingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NewSightingActivity.class);
                intent.putExtra("vehicle_id", vehicle.getId());
                getContext().startActivity(intent);
            }
        });
        showLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", vehicle.getLocation().getLatitude(), vehicle.getLocation().getLongitude());
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                getContext().startActivity(intent);
            }
        });


        vehicleBrand.setText(vehicle.getBrand());
        vehicleColor.setText(vehicle.getColor());
        vehicleLicense.setText(vehicle.getLicensePlate());

        if(vehicle.getType() != null){
            vehicleType.setText(vehicle.getType());
        }


        return convertView;
    }
}
