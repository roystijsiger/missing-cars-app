package com.wherismyvehicle.whereismyvehicle.Presenters;

import android.content.Context;

import com.wherismyvehicle.whereismyvehicle.Data.DataPersistence;
import com.wherismyvehicle.whereismyvehicle.Data.DataPersistenceActionEventHandler;
import com.wherismyvehicle.whereismyvehicle.Data.ModelPersistenceService;
import com.wherismyvehicle.whereismyvehicle.Models.Sighting;

import java.util.ArrayList;
import java.util.Arrays;

public class SightingsActivityPresenter {
    private View view;
    private DataPersistence<Sighting>  dataPersistence;

    public SightingsActivityPresenter(View view, int vehicleId){
        this.view = view;
        dataPersistence = new ModelPersistenceService<>(view.getContext(), String.format("vehicles/%d/sightings",vehicleId));
    }

    public void loadSightings(){
        dataPersistence.FetchAll(Sighting[].class).AddHandler(new DataPersistenceActionEventHandler<Sighting[]>() {
            @Override
            public void OnResult(Sighting[] result) {
                view.setListView(new ArrayList<>(Arrays.asList(result)));
            }
        });
    }

    public interface View{
        Context getContext();
        void setListView(ArrayList arrayList);
    }
}
