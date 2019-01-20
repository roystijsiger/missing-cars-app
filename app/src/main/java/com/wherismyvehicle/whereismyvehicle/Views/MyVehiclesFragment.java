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

import com.wherismyvehicle.whereismyvehicle.Models.Vehicle;
import com.wherismyvehicle.whereismyvehicle.Presenters.MyVehiclesFragmentPresenter;
import com.wherismyvehicle.whereismyvehicle.Views.Adapters.VehiclesAdapter;

import java.util.ArrayList;

public class MyVehiclesFragment extends Fragment implements MyVehiclesFragmentPresenter.View {
    private MyVehiclesFragmentPresenter presenter;
    private View fragmentView;
    private ListView listViewVehicles;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.presenter = new MyVehiclesFragmentPresenter(this);
    }

    //show vehicles in list
    public void showVehicles(final ArrayList<Vehicle> vehicles){
        //create a new adapter to fill the arrayList.
        getActivity().runOnUiThread(new Runnable(){
            @Override
            public void run(){
                VehiclesAdapter vehicleAdapter = new VehiclesAdapter(getActivity(), vehicles, true);
                listViewVehicles.setAdapter(vehicleAdapter);
            }
        });

    }

    public Context getContext(){
        return getActivity();
    }

    public void test(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_my_vehicles, container, false);
        listViewVehicles = fragmentView.findViewById(R.id.listview_vehicles);
        presenter.loadVehicles();

        fragmentView.findViewById(R.id.img_add_vehicle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openNewVehicleForm();
            }
        });
        return this.fragmentView;
    }
}
