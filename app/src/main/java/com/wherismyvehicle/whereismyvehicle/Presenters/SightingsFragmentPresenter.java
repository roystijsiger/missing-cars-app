package com.wherismyvehicle.whereismyvehicle.Presenters;

import com.wherismyvehicle.whereismyvehicle.Data.CachedDataPersistance;
import com.wherismyvehicle.whereismyvehicle.Data.DataPersinstance;
import com.wherismyvehicle.whereismyvehicle.Models.Sighting;

import java.util.ArrayList;

public class SightingsFragmentPresenter {
    private ArrayList<Sighting> sightings;
    private View view;
    private DataPersinstance<Sighting> dataPersinstance;

    public SightingsFragmentPresenter(View view) {
        this.view = view;

        dataPersinstance = new CachedDataPersistance();
    }

    public void LoadSightings() {
        ArrayList<Sighting> sightings = dataPersinstance.FetchAll();
        view.ShowSightings(sightings);
    }

    public interface View {
        void ShowSightings(ArrayList<Sighting> sightings);
    }
}
