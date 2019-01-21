package com.wherismyvehicle.whereismyvehicle.Presenters;

import android.util.Patterns;

import com.wherismyvehicle.whereismyvehicle.Data.Authentication.AuthenticationSingleton;
import com.wherismyvehicle.whereismyvehicle.Views.RegisterFragment;

public class RegisterFragmentPresenter {
    private RegisterFragment fragment;

    public RegisterFragmentPresenter(RegisterFragment registerFragment) {
        this.fragment = registerFragment;

        AuthenticationSingleton.getInstance().setOnRegisteredHandler(new Runnable() {
            @Override
            public void run() {
                fragment.onRegistered();
            }
        });
        AuthenticationSingleton.getInstance().setOnRegistrationFailedHandler(new Runnable() {
            @Override
            public void run() {
                fragment.onRegisteredFailed();
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

        AuthenticationSingleton.getInstance().logout();

        AuthenticationSingleton.getInstance().register(email, password);
    }

    public interface View {
        void invalidEmail();
        void failedPasswordRequirements();
        void failedPasswordConfirm();
        void onRegistered();
        void onRegisteredFailed();

    }
}
