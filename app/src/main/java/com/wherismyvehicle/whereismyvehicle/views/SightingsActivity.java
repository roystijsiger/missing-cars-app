package com.wherismyvehicle.whereismyvehicle.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.wherismyvehicle.whereismyvehicle.presenters.SightingsActivityPresenter;
import com.wherismyvehicle.whereismyvehicle.views.adapters.SightingsAdapter;

import java.util.ArrayList;

public class SightingsActivity extends AppCompatActivity implements SightingsActivityPresenter.View {
    private SightingsActivityPresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sightings);

        int vehicleId = getIntent().getIntExtra("vehicle_id", 1);

        presenter = new SightingsActivityPresenter(this, vehicleId);

        TextView title = findViewById(R.id.txt_title_sightings);
        title.setText(String.format("Vehicle sightings %s", getIntent().getStringExtra("vehicle_license")));


        presenter.loadSightings();
    }

    public Context getContext(){
        return this;
    }

    public void setListView(final ArrayList arrayList){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ListView sightingsListView = findViewById(R.id.listview_sightings);
                SightingsAdapter sightingsAdapter = new SightingsAdapter(getContext(), arrayList);
                sightingsListView.setAdapter(sightingsAdapter);
            }
        });

    }
}
