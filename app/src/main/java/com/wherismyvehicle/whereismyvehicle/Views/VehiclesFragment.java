package com.wherismyvehicle.whereismyvehicle.Views;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.wherismyvehicle.whereismyvehicle.Models.Sighting;
import com.wherismyvehicle.whereismyvehicle.Models.Vehicle;
import com.wherismyvehicle.whereismyvehicle.Models.VehicleType;
import com.wherismyvehicle.whereismyvehicle.Presenters.VehiclesFragmentPresenter;
import com.wherismyvehicle.whereismyvehicle.Views.Adapters.VehiclesAdapter;

import java.util.ArrayList;

public class VehiclesFragment extends Fragment implements VehiclesFragmentPresenter.View {
    private VehiclesFragmentPresenter presenter;
    private View fragmentView;
    private ListView missingVehicles;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new VehiclesFragmentPresenter(this);


    }

    private void setMissingVehicles(){
        ArrayList<Vehicle> arrayList = new ArrayList<>();
        Vehicle vehicle = new Vehicle(1, VehicleType.Car,"Audi","Purple","28-28-ab", new Location("service Provider"),new ArrayList<Sighting>());
        arrayList.add(vehicle);

        Vehicle vehicle2 = new Vehicle(2, VehicleType.Bicycle,"Mercedes","Green","none", new Location("service Provider"),new ArrayList<Sighting>());
        arrayList.add(vehicle2);

        Vehicle vehicle3 = new Vehicle(3, VehicleType.MotorCycle,"Volvo","Purple","28-ab-ab", new Location("service Provider"),new ArrayList<Sighting>());
        arrayList.add(vehicle3);

        VehiclesAdapter vehicleAdapter = new VehiclesAdapter(getActivity(), arrayList);
        this.missingVehicles.setAdapter(vehicleAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.fragmentView = inflater.inflate(R.layout.fragment_vehicles, container, false);
        this.missingVehicles = this.fragmentView.findViewById(R.id.listview_vehicles);

        this.setMissingVehicles();
        return this.fragmentView;
    }
}
