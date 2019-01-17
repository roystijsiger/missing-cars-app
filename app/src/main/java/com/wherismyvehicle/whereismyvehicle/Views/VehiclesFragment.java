package com.wherismyvehicle.whereismyvehicle.Views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.wherismyvehicle.whereismyvehicle.Presenters.VehiclesFragmentPresenter;

public class VehiclesFragment extends Fragment implements VehiclesFragmentPresenter.View {
    private VehiclesFragmentPresenter presenter;
    private ListView missingVehicles;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        presenter = new VehiclesFragmentPresenter(this);

        
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vehicles, container, false);
    }
}
