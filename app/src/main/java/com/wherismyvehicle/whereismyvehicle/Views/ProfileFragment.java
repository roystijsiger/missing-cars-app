package com.wherismyvehicle.whereismyvehicle.Views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wherismyvehicle.whereismyvehicle.Data.Authentication.AuthenticationSingleton;
import com.wherismyvehicle.whereismyvehicle.Presenters.ProfileFragmentPresenter;

public class ProfileFragment extends Fragment implements ProfileFragmentPresenter.View {

    private ProfileFragmentPresenter presenter;
    private Runnable onLoggedOut;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new ProfileFragmentPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);

        Button logoutButton = view.findViewById(R.id.btn_logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthenticationSingleton.getInstance().logout();

                onLoggedOut.run();
            }
        });

        return view;
    }

    public void onLoggedOut(Runnable runnable) {
        onLoggedOut = runnable;
    }
}
