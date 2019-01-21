package com.wherismyvehicle.whereismyvehicle.presenters;

import android.util.Patterns;

import com.wherismyvehicle.whereismyvehicle.data.authentication.Singleton;

public class LoginFragmentPresenter {

    private View fragment;

    public LoginFragmentPresenter(final View fragment) {
        this.fragment = fragment;

        Singleton.getInstance().setOnLoggedInHandler(new Runnable() {
            @Override
            public void run() {
                fragment.onLoggedIn();
            }
        });
        Singleton.getInstance().setOnLoginFailedHandler(new Runnable() {
            @Override
            public void run() {
                fragment.onLoginFailed();
            }
        });
    }

    public void login(final String email, final String password){
        if(!Patterns.EMAIL_ADDRESS.matcher(email.toLowerCase().trim()).matches()){
            fragment.invalidEmail();
            return;
        }

        Singleton.getInstance().setOnLogoutHandler(new Runnable() {
            @Override
            public void run() {
                Singleton.getInstance().login(email, password);
            }
        }).logout();
    }

    public interface View {
        void invalidEmail();
        void onLoggedIn();
        void onLoginFailed();
    }
}
