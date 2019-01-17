package com.wherismyvehicle.whereismyvehicle.Views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wherismyvehicle.whereismyvehicle.Models.Sighting;
import com.wherismyvehicle.whereismyvehicle.Presenters.SightingsFragmentPresenter;

import java.util.ArrayList;

public class SightingsFragment extends Fragment implements SightingsFragmentPresenter.View {
    private SightingsFragmentPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = new SightingsFragmentPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter.LoadSightings();

        return inflater.inflate(R.layout.fragment_sightings, container, false);
    }

    @Override
    public void ShowSightings(ArrayList<Sighting> sightings) {
       // TODO Roy: Add this arraylist to an listview.
    }
}
