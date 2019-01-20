package com.wherismyvehicle.whereismyvehicle.Presenters;

import android.util.Patterns;

import com.wherismyvehicle.whereismyvehicle.Data.Authentication.AuthenticationSingleton;

public class LoginFragmentPresenter {

    private View fragment;

    public LoginFragmentPresenter(final View fragment) {
        this.fragment = fragment;

        AuthenticationSingleton.getInstance().setOnLoggedInHandler(new Runnable() {
            @Override
            public void run() {
                fragment.onLoggedIn();
            }
        });
        AuthenticationSingleton.getInstance().setOnLoginFailedHandler(new Runnable() {
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

        AuthenticationSingleton.getInstance().logout();
        AuthenticationSingleton.getInstance().login(email, password);
    }

    public interface View {
        void invalidEmail();
        void onLoggedIn();
        void onLoginFailed();
    }
}
