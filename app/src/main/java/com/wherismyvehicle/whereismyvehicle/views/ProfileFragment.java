package com.wherismyvehicle.whereismyvehicle.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wherismyvehicle.whereismyvehicle.data.authentication.Singleton;
import com.wherismyvehicle.whereismyvehicle.presenters.ProfileFragmentPresenter;

public class ProfileFragment extends Fragment implements ProfileFragmentPresenter.View {

    private ProfileFragmentPresenter presenter;
    private Runnable onLoggedOut;
    private TextView emailTextView;
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new ProfileFragmentPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_profile, container, false);

        emailTextView = view.findViewById(R.id.txt_email_logged_in);
        emailTextView.setText(Singleton.getInstance().getEmail());
        Button logoutButton = view.findViewById(R.id.btn_logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Singleton.getInstance().logout();

                onLoggedOut.run();
            }
        });

        Button myVehiclesButton = view.findViewById(R.id.btn_my_vehicles);
        myVehiclesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment myVehiclesFragment = new MyVehiclesFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, myVehiclesFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

    public void onLoggedOut(Runnable runnable) {
        onLoggedOut = runnable;
    }
}
