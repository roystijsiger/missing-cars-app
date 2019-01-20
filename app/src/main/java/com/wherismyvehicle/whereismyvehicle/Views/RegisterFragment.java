package com.wherismyvehicle.whereismyvehicle.Views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class RegisterFragment extends Fragment {
    private Runnable onRegistered;

    public RegisterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        Button registerButton = view.findViewById(R.id.btn_register);
        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onRegistered.run();
            }
        });

        return view;
    }

    public void onRegistered(Runnable runnable) {
        onRegistered = runnable;
    }
}
