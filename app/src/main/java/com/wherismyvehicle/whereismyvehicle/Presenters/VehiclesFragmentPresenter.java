package com.wherismyvehicle.whereismyvehicle.Presenters;

import android.content.Context;

import com.wherismyvehicle.whereismyvehicle.Data.CachedDataPersistence;
import com.wherismyvehicle.whereismyvehicle.Data.DataPersistence;
import com.wherismyvehicle.whereismyvehicle.Data.DataPersistenceEventHandler;
import com.wherismyvehicle.whereismyvehicle.Models.Vehicle;

import java.util.ArrayList;
import java.util.Arrays;

public class VehiclesFragmentPresenter {
    private ArrayList<Vehicle> vehicles;
    private View view;
    private DataPersistence<Vehicle> dataPersistence;

    public VehiclesFragmentPresenter(View view) {
        this.view = view;

        dataPersistence = new CachedDataPersistence<>(view.getContext(), "vehicles");
        vehicles = new ArrayList<>();
    }

    public void loadVehicles(){
        dataPersistence.FetchAll(Vehicle[].class).AddHandler(new DataPersistenceEventHandler<Vehicle[]>(){
            @Override
            public void OnResult(Vehicle[] result) {
                view.showVehicles(new ArrayList<>(Arrays.asList(result)));
            }
        } );

    }

     public interface View {
        void showVehicles(ArrayList<Vehicle> vehicles);
        Context getContext();
    }
}
