package com.wherismyvehicle.whereismyvehicle.Presenters;

import android.content.Context;

import com.wherismyvehicle.whereismyvehicle.Data.CachedDataPersistence;
import com.wherismyvehicle.whereismyvehicle.Data.DataPersistence;
import com.wherismyvehicle.whereismyvehicle.Data.DataPersistenceTask;
import com.wherismyvehicle.whereismyvehicle.Models.Sighting;

import java.util.ArrayList;

public class SightingsFragmentPresenter {
    private ArrayList<Sighting> sightings;
    private View view;
    private DataPersistence<Sighting> dataPersistence;

    public SightingsFragmentPresenter(View view) {
        this.view = view;

        dataPersistence = new CachedDataPersistence<>(view.GetContext());
    }

    public void LoadSightings() {
        DataPersistenceTask<ArrayList<Sighting>> sightings = dataPersistence.FetchAll();

        // TODO: Handle event
        //sightings.onAddEventHandler();
        //view.ShowSightings(sightings);
    }

    public interface View {
        Context GetContext();
        void ShowSightings(ArrayList<Sighting> sightings);
    }
}
