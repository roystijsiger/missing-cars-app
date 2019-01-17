package com.wherismyvehicle.whereismyvehicle.Presenters;

import com.wherismyvehicle.whereismyvehicle.Models.Vehicle;

import java.util.ArrayList;

public class VehiclesFragmentPresenter {
    private ArrayList<Vehicle> vehicles;
    private View view;

    public VehiclesFragmentPresenter(View view) {
        this.view = view;

        // TODO: Get vehicles from api/storage
        this.vehicles = new ArrayList<>();
    }

    public interface View {

    }
}
