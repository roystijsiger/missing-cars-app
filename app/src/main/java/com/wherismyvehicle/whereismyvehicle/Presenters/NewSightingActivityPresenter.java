package com.wherismyvehicle.whereismyvehicle.Presenters;


import android.content.Context;

import com.wherismyvehicle.whereismyvehicle.Data.DataPersistence;
import com.wherismyvehicle.whereismyvehicle.Data.ModelPersistenceService;
import com.wherismyvehicle.whereismyvehicle.Models.Sighting;

public class NewSightingActivityPresenter {
    private View view;
    private DataPersistence<Sighting> dataPersistence;

    NewSightingActivityPresenter(View view){
        this.view = view;
        dataPersistence = new ModelPersistenceService<>(view.getContext(), "sightings");
    }

    public interface View{
        Context getContext();
    }
}
