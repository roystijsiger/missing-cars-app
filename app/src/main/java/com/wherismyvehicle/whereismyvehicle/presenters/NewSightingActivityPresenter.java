package com.wherismyvehicle.whereismyvehicle.presenters;


import android.content.Context;
import android.location.Location;

import com.wherismyvehicle.whereismyvehicle.data.DataPersistence;
import com.wherismyvehicle.whereismyvehicle.data.DataPersistenceActionEventHandler;
import com.wherismyvehicle.whereismyvehicle.data.webPersistence.ModelPersistenceService;
import com.wherismyvehicle.whereismyvehicle.models.Sighting;

public class NewSightingActivityPresenter {
    private View view;
    private DataPersistence<Sighting> dataPersistence;

    public NewSightingActivityPresenter(View view){
        this.view = view;
        dataPersistence = new ModelPersistenceService<>(view.getContext(),String.format("vehicles/%d/sightings", view.getVehicleId()));
    }

    public void addNewSightings(Location location, String photo){
        if(isStringNullOrEmpty(photo) || location == null){
            String message = "Either you haven't taken a picture or your location wasnt provided.";
            view.showToastMsg(message);
        }
        else{
            Sighting sighting = new Sighting(location, photo);
            try {
                dataPersistence.Insert(Sighting.class, sighting).AddHandler(new DataPersistenceActionEventHandler<Sighting>() {
                    @Override
                    public void OnResult(Sighting result) {
                        String message = String.format("Sighting for vehicle %s has been posted", view.getVehicleId() );
                        view.showToastMsg(message);
                    }
                });
            }
            catch(Exception e){
                String message = String.format("De app kon geen sighting toevoegen probeer het op een later tijdstip nog eens.");
                view.showToastMsg(message);
            }
        }

    }

    public interface View{
        Context getContext();
        int getVehicleId();
        void showToastMsg(String message);

    }

    private boolean isStringNullOrEmpty(String string){
        if(string == "" || string == null){
            return true;
        }
        return false;
    }
}
