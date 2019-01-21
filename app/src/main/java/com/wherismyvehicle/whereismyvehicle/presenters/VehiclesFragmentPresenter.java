package com.wherismyvehicle.whereismyvehicle.presenters;

import android.content.Context;
import android.content.Intent;

import com.wherismyvehicle.whereismyvehicle.data.DataPersistence;
import com.wherismyvehicle.whereismyvehicle.data.DataPersistenceActionEventHandler;
import com.wherismyvehicle.whereismyvehicle.data.webPersistence.ModelPersistenceService;
import com.wherismyvehicle.whereismyvehicle.models.Vehicle;
import com.wherismyvehicle.whereismyvehicle.views.NewVehicleActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class VehiclesFragmentPresenter {
    private View view;
    private DataPersistence<Vehicle> dataPersistence;

    public VehiclesFragmentPresenter(View view) {
        this.view = view;

        dataPersistence = new ModelPersistenceService<>(view.getContext(), "vehicles");
    }

    public void loadVehicles(){
        dataPersistence.FetchAll(Vehicle[].class).AddHandler(new DataPersistenceActionEventHandler<Vehicle[]>(){
            @Override
            public void OnResult(Vehicle[] result) {
                view.showVehicles(new ArrayList<>(Arrays.asList(result)));
            }
        });
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
