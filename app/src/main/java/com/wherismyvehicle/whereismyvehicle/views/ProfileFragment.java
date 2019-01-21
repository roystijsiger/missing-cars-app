package com.wherismyvehicle.whereismyvehicle.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wherismyvehicle.whereismyvehicle.data.authentication.Singleton;

public class ProfileFragment extends Fragment {

    private Runnable onLoggedOut;
    private Runnable onOpenMissingVehicles;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView emailTextView = view.findViewById(R.id.txt_email_logged_in);
        emailTextView.setText(Singleton.getInstance().getEmail());
        Button logoutButton = view.findViewById(R.id.btn_logout);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Singleton.getInstance().setOnLogoutHandler(onLoggedOut).logout();
            }
        });

        Button myVehiclesButton = view.findViewById(R.id.btn_my_vehicles);
        myVehiclesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOpenMissingVehicles.run();
            }
        });

        return view;
    }

    public void onLoggedOut(Runnable runnable) {
        onLoggedOut = runnable;
    }

    public void setOnOpenMissingVehicles(Runnable runnable) {
        this.onOpenMissingVehicles = runnable;
    }
}
