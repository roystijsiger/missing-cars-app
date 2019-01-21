package com.wherismyvehicle.whereismyvehicle.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wherismyvehicle.whereismyvehicle.presenters.LoginFragmentPresenter;


public class LoginFragment extends Fragment implements LoginFragmentPresenter.View {
    private LoginFragmentPresenter presenter;
    private View view;

    private Runnable onRegister;
    private Runnable onLogin;

    private EditText emailEditText;
    private EditText passwordEditText;
    private TextView errorTextView;


    public LoginFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new LoginFragmentPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);

        Button loginButton = view.findViewById(R.id.btn_login);
        Button registerButton = view.findViewById(R.id.btn_register);

        emailEditText = view.findViewById(R.id.txt_email);
        passwordEditText = view.findViewById(R.id.txt_password);

        errorTextView = view.findViewById(R.id.lbl_error);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                presenter.login(emailEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                emailEditText.setText(null);
                passwordEditText.setText(null);
                onRegister.run();
            }
        });

        return view;
    }

    public void setOnRegister(Runnable runnable) {
        onRegister = runnable;
    }

    public void setOnLogin(Runnable runnable) {
        onLogin = runnable;
    }

    public String getEmail(){
        return emailEditText.getText().toString();
    }

    @Override
    public void invalidEmail() {
        emailEditText.setError("Email has an invalid format");
    }

    @Override
    public void onLoggedIn() {
        emailEditText.setText(null);
        passwordEditText.setText(null);
        onLogin.run();
    }

    @Override
    public void onLoginFailed() {
        String error = String.format("Error: %s", "Logging in did not work. Are you're credentials correct?");
        errorTextView.setText(error);
    }
}
