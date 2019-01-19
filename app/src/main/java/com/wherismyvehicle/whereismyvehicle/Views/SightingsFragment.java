package com.wherismyvehicle.whereismyvehicle.Views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.wherismyvehicle.whereismyvehicle.Models.Sighting;
import com.wherismyvehicle.whereismyvehicle.Presenters.SightingsFragmentPresenter;
import com.wherismyvehicle.whereismyvehicle.Views.Adapters.SightingsAdapter;

import java.util.ArrayList;

public class SightingsFragment extends Fragment implements SightingsFragmentPresenter.View {
    private SightingsFragmentPresenter presenter;
    private ListView listViewSightings;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = new SightingsFragmentPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.listViewSightings = getView().findViewById(R.id.listview_sightings);
        presenter.LoadSightings();

        return inflater.inflate(R.layout.fragment_sightings, container, false);
    }

    @Override
    public Context GetContext() {
        return getActivity();
    }

    @Override
    public void ShowSightings(final ArrayList<Sighting> sightings) {
       // TODO Roy: Add this arraylist to an listview.
        getActivity().runOnUiThread(new Runnable(){
            @Override
            public void run(){
                SightingsAdapter sightingsAdapter = new SightingsAdapter(getActivity(), sightings);
                listViewSightings.setAdapter(sightingsAdapter);
            }
        });
    }
}
