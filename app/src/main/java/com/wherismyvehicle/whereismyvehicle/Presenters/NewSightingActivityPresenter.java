package com.wherismyvehicle.whereismyvehicle.Presenters;


import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.wherismyvehicle.whereismyvehicle.Data.DataPersistence;
import com.wherismyvehicle.whereismyvehicle.Data.DataPersistenceActionEventHandler;
import com.wherismyvehicle.whereismyvehicle.Data.ModelPersistenceService;
import com.wherismyvehicle.whereismyvehicle.Models.Sighting;

public class NewSightingActivityPresenter {
    private View view;
    private DataPersistence<Sighting> dataPersistence;

    public NewSightingActivityPresenter(View view){
        this.view = view;
        dataPersistence = new ModelPersistenceService<>(view.getContext(),String.format("vehicles/%d/sightings", view.getVehicleId()));
    }

    public void addNewSightings(Location location, String photo){

        Sighting sighting = new Sighting(location, photo);
        try {
            dataPersistence.Insert(Sighting.class, sighting).AddHandler(new DataPersistenceActionEventHandler<Sighting>() {
                @Override
                public void OnResult(Sighting result) {
                    String message = String.format("Sighting for vehicle %s has been posted", view.getVehicleId() );
                    view.showSuccessMessage(message);
                }
            });
        }
        catch(Exception e){
            // TODO: 1/20/2019 throw exception
        }
    }

    public interface View{
        Context getContext();
        int getVehicleId();
        void showSuccessMessage(String message);

    }
}
