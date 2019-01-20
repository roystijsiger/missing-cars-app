package com.wherismyvehicle.whereismyvehicle.Presenters;

import android.util.Patterns;

import com.wherismyvehicle.whereismyvehicle.Data.Authentication.AuthenticationState;
import com.wherismyvehicle.whereismyvehicle.Views.RegisterFragment;

public class RegisterFragmentPresenter {
    private RegisterFragment fragment;

    public RegisterFragmentPresenter(RegisterFragment registerFragment) {
        this.fragment = registerFragment;

        AuthenticationState.getInstance().addOnUserChangedHandler(new Runnable() {
            @Override
            public void run() {
                fragment.onRegistered();
            }
        });
    }

    public void register(String email, String password, String passwordConfirm) {
        if(!Patterns.EMAIL_ADDRESS.matcher(email.toLowerCase().trim()).matches()){
            fragment.invalidEmail();
            return;
        }

        if(password.length() < 6) {
            fragment.failedPasswordRequirements();
            return;
        }

        if(!password.equals(passwordConfirm)){
            fragment.failedPasswordConfirm();
            return;
        }

        AuthenticationState.getInstance().register(email, password);
    }

    public interface View {
        void invalidEmail();
        void failedPasswordRequirements();
        void failedPasswordConfirm();
        void onRegistered();
    }
}
