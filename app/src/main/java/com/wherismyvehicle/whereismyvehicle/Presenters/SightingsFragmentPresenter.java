package com.wherismyvehicle.whereismyvehicle.Presenters;

import android.content.Context;

import com.wherismyvehicle.whereismyvehicle.Data.CachedDataPersistence;
import com.wherismyvehicle.whereismyvehicle.Data.DataPersistence;
import com.wherismyvehicle.whereismyvehicle.Data.DataPersistenceEventHandler;
import com.wherismyvehicle.whereismyvehicle.Models.Sighting;

import java.util.ArrayList;
import java.util.Arrays;

public class SightingsFragmentPresenter {
    private ArrayList<Sighting> sightings;
    private View view;
    private DataPersistence<Sighting> dataPersistence;

    public SightingsFragmentPresenter(View view) {
        this.view = view;

        dataPersistence = new CachedDataPersistence<>(view.GetContext(), "sightings");
    }

    public void LoadSightings() {
        dataPersistence.FetchAll(Sighting[].class).AddHandler(new DataPersistenceEventHandler<Sighting[]>() {
            @Override
            public void OnResult(Sighting[] result) {
                view.ShowSightings(new ArrayList<>(Arrays.asList(result)));
            }
        });

        dataPersistence.Fetch(Sighting.class, 1).AddHandler(new DataPersistenceEventHandler<Sighting>() {
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
