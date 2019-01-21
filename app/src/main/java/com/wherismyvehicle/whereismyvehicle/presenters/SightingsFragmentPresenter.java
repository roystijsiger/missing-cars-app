package com.wherismyvehicle.whereismyvehicle.presenters;

import android.content.Context;

import com.wherismyvehicle.whereismyvehicle.data.DataPersistence;
import com.wherismyvehicle.whereismyvehicle.data.DataPersistenceActionEventHandler;
import com.wherismyvehicle.whereismyvehicle.data.webPersistence.ModelPersistenceService;
import com.wherismyvehicle.whereismyvehicle.models.Sighting;

import java.util.ArrayList;
import java.util.Arrays;

public class SightingsFragmentPresenter {
    private View view;
    private DataPersistence<Sighting> dataPersistence;

    public SightingsFragmentPresenter(View view) {
        this.view = view;

        dataPersistence = new ModelPersistenceService<>(view.GetContext(), "sightings");
    }

    public void LoadSightings() {
        dataPersistence.FetchAll(Sighting[].class).AddHandler(new DataPersistenceActionEventHandler<Sighting[]>() {
            @Override
            public void OnResult(Sighting[] result) {
                view.ShowSightings(new ArrayList<>(Arrays.asList(result)));
            }
        });

        dataPersistence.Fetch(Sighting.class, 1).AddHandler(new DataPersistenceActionEventHandler<Sighting>() {
            @Override
            public void OnResult(Sighting result) {
                System.out.println(1);
            }
        });
    }

    public interface View {
        Context GetContext();
        void ShowSightings(ArrayList<Sighting> sightings);
    }
}
