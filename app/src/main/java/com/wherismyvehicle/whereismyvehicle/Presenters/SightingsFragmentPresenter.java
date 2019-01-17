package com.wherismyvehicle.whereismyvehicle.Presenters;

import com.wherismyvehicle.whereismyvehicle.Models.Sighting;

import java.util.ArrayList;

public class SightingsFragmentPresenter {
    private ArrayList<Sighting> sightings;
    private View view;

    public SightingsFragmentPresenter(View view) {
        this.view = view;

        // TODO: Get sightings from api/storage
        sightings = new ArrayList<>();
    }

    public interface View {

    }
}
