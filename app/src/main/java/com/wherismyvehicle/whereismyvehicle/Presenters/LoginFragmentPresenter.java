package com.wherismyvehicle.whereismyvehicle.Presenters;

import android.util.Patterns;

import com.wherismyvehicle.whereismyvehicle.Data.Authentication.AuthenticationState;

public class LoginFragmentPresenter {

    private View fragment;

    public LoginFragmentPresenter(final View fragment) {
        this.fragment = fragment;

        AuthenticationState.getInstance().setOnLoggedInHandler(new Runnable() {
            @Override
            public void run() {
                fragment.onLoggedIn();
            }
        });
        AuthenticationState.getInstance().setOnLoginFailedHandler(new Runnable() {
            @Override
            public void run() {
                fragment.onLoginFailed();
            }
        });
    }

    public void login(String email, String password){
        if(!Patterns.EMAIL_ADDRESS.matcher(email.toLowerCase().trim()).matches()){
            fragment.invalidEmail();
            return;
        }

        AuthenticationState.getInstance().logout();
        AuthenticationState.getInstance().login(email, password);
    }

    public interface View {
        void invalidEmail();
        void onLoggedIn();
        void onLoginFailed();
    }
}
