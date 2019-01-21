package com.wherismyvehicle.whereismyvehicle.presenters;

import android.content.Context;
import android.content.Intent;

import com.google.android.gms.maps.model.LatLng;
import com.wherismyvehicle.whereismyvehicle.data.DataPersistence;
import com.wherismyvehicle.whereismyvehicle.data.DataPersistenceActionEventHandler;
import com.wherismyvehicle.whereismyvehicle.data.webPersistence.ModelPersistenceService;
import com.wherismyvehicle.whereismyvehicle.models.Vehicle;
import com.wherismyvehicle.whereismyvehicle.views.MainActivity;

public class NewVehicleActivityPresenter{
    private View view;
    private DataPersistence<Vehicle> dataPersistence;

    public NewVehicleActivityPresenter(View view){
        this.view = view;
        dataPersistence = new ModelPersistenceService<>(view.getContext(), "vehicles");
    }

    public void addNewVehicle(String license, String brand, String type, String color, LatLng lastKnownLocation){
        if(isStringNullOrEmpty(license) || isStringNullOrEmpty(brand) || isStringNullOrEmpty(type) || isStringNullOrEmpty(color) || lastKnownLocation == null){
            String message = "Please fill in all fields and select a last known location to post the vehicle.";
            this.view.showToast(message);
        }
        else {
            Vehicle vehicle = new Vehicle(type, brand, color, license, lastKnownLocation.longitude, lastKnownLocation.latitude);

            dataPersistence.Insert(Vehicle.class, vehicle).AddHandler(new DataPersistenceActionEventHandler<Vehicle>() {
                @Override
                public void OnResult(Vehicle result) {
                    String message = String.format("License %s has been reported as missing vehicle ", result.getLicensePlate());
                    view.showToast(message);
                }
            });

            Intent intent = new Intent(view.getContext(), MainActivity.class);
            // TODO: Find a way to reload vehicles after intent
            view.getContext().startActivity(intent);
        }

    }

    public interface View {
        Context getContext();
        void showToast(String message);
    }

    private boolean isStringNullOrEmpty(String value){
        return value == null || value.equals("");
    }
}
