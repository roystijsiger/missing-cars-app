package com.wherismyvehicle.whereismyvehicle.Presenters;

import android.content.Context;
import android.content.Intent;

import com.wherismyvehicle.whereismyvehicle.Data.DataPersistence;
import com.wherismyvehicle.whereismyvehicle.Data.DataPersistenceActionEventHandler;
import com.wherismyvehicle.whereismyvehicle.Data.WebServiceDataPersistence;
import com.wherismyvehicle.whereismyvehicle.Models.Vehicle;
import com.wherismyvehicle.whereismyvehicle.Views.NewVehicleActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class VehiclesFragmentPresenter {
    private ArrayList<Vehicle> vehicles;
    private View view;
    private DataPersistence<Vehicle> dataPersistence;

    public VehiclesFragmentPresenter(View view) {
        this.view = view;

        dataPersistence = new WebServiceDataPersistence<>(view.getContext(), "vehicles");
        vehicles = new ArrayList<>();
    }

    public void loadVehicles(){
        dataPersistence.FetchAll(Vehicle[].class).AddHandler(new DataPersistenceActionEventHandler<Vehicle[]>(){
            @Override
            public void OnResult(Vehicle[] result) {
                view.showVehicles(new ArrayList<>(Arrays.asList(result)));
            }
        } );
    }

    public void openNewVehicleForm(){
        Intent intent = new Intent(view.getContext(), NewVehicleActivity.class);
        view.getContext().startActivity(intent);
    }

    public interface View {
        void showVehicles(ArrayList<Vehicle> vehicles);
        Context getContext();
    }
}
