package com.wherismyvehicle.whereismyvehicle.Views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wherismyvehicle.whereismyvehicle.Data.Authentication.AuthenticationState;


public class LoginFragment extends Fragment {

    private View fragmentView;
    private Runnable onRegister;
    private Runnable onLogin;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_login, container, false);

        Button loginButton = fragmentView.findViewById(R.id.btn_login);
        Button registerButton = fragmentView.findViewById(R.id.btn_register);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // TODO: Get token and get token


                onLogin.run();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onRegister.run();
            }
        });

        return fragmentView;
    }

    public void setOnRegister(Runnable runnable) {
        onRegister = runnable;
    }

    public void setOnLogin(Runnable runnable) {
        onLogin = runnable;
    }
}
