package com.wherismyvehicle.whereismyvehicle.Views;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.wherismyvehicle.whereismyvehicle.Presenters.RegisterFragmentPresenter;


public class RegisterFragment extends Fragment implements RegisterFragmentPresenter.View {
    private RegisterFragmentPresenter presenter;

    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText passwordConfirmEditText;

    private Runnable onClose;

    public RegisterFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.presenter = new RegisterFragmentPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        emailEditText = view.findViewById(R.id.txt_email);
        passwordEditText = view.findViewById(R.id.txt_password);
        passwordConfirmEditText = view.findViewById(R.id.txt_password_confirm);

        Button registerButton = view.findViewById(R.id.btn_register);
        Button loginButton = view.findViewById(R.id.btn_login);

        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                presenter.register(emailEditText.getText().toString(), passwordEditText.getText().toString(), passwordConfirmEditText.getText().toString());
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailEditText.setText(null);
                passwordEditText.setText(null);
                passwordConfirmEditText.setText(null);
                onClose.run();
            }
        });

        return view;
    }

    public void onClose(Runnable runnable) {
        onClose = runnable;
    }

    @Override
    public void invalidEmail() {
        emailEditText.setError("Invalid email format");
    }


    @Override
    public void failedPasswordRequirements() {
        passwordEditText.setError("Password should be 6 or more characters");
    }


    @Override
    public void failedPasswordConfirm() {
        passwordConfirmEditText.setError("Password confirmation did not match");
    }

    @Override
    public void onRegistered() {
        emailEditText.setText(null);
        passwordEditText.setText(null);
        passwordConfirmEditText.setText(null);
        onClose.run();
    }
}
