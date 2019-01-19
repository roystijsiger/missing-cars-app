package com.wherismyvehicle.whereismyvehicle.Presenters;

import android.content.Context;
import android.content.Intent;

import com.wherismyvehicle.whereismyvehicle.Data.CachedDataPersistence;
import com.wherismyvehicle.whereismyvehicle.Data.DataPersistence;
import com.wherismyvehicle.whereismyvehicle.Data.DataPersistenceEventHandler;
import com.wherismyvehicle.whereismyvehicle.Models.Vehicle;
import com.wherismyvehicle.whereismyvehicle.Views.NewVehicleActivity;

import java.util.ArrayList;

public class VehiclesFragmentPresenter {
    private ArrayList<Vehicle> vehicles;
    private View view;
    private DataPersistence<Vehicle> dataPersistence;

    public VehiclesFragmentPresenter(View view) {
        this.view = view;

        dataPersistence = new CachedDataPersistence<>(view.getContext(), Vehicle.class);
        vehicles = new ArrayList<>();
    }

    public void loadVehicles(){
        dataPersistence.FetchAll().AddHandler(new DataPersistenceEventHandler<ArrayList<Vehicle>>(){
            @Override
            public void OnResult(ArrayList<Vehicle> result) {
                view.showVehicles(result);
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
