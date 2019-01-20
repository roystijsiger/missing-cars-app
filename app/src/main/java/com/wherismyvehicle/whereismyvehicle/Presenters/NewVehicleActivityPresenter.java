package com.wherismyvehicle.whereismyvehicle.Presenters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;
import com.wherismyvehicle.whereismyvehicle.Data.DataPersistence;
import com.wherismyvehicle.whereismyvehicle.Data.DataPersistenceActionEventHandler;
import com.wherismyvehicle.whereismyvehicle.Data.ModelPersistenceService;
import com.wherismyvehicle.whereismyvehicle.Models.Vehicle;

import java.util.ArrayList;

public class NewVehicleActivityPresenter{
    private View view;
    private DataPersistence<Vehicle> dataPersistence;

    public NewVehicleActivityPresenter(View view){
        this.view = view;
        dataPersistence = new ModelPersistenceService<>(view.getContext(), "vehicles");
    }

    public void addNewVehicle(String license, String brand, String type, String color, LatLng lastKnownLocation){
        Vehicle vehicle = new Vehicle(type,brand,color,license,lastKnownLocation.longitude,lastKnownLocation.latitude);

        dataPersistence.Insert(Vehicle.class, vehicle).AddHandler(new DataPersistenceActionEventHandler<Vehicle>() {
            @Override
            public void OnResult(Vehicle result) {
                view.showToast(result.getLicensePlate());
            }
        });

    }

    public interface View {
        Context getContext();
        void showToast(String license);
    }
}
