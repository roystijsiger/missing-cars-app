package com.wherismyvehicle.whereismyvehicle.presenters;

import android.util.Patterns;

import com.wherismyvehicle.whereismyvehicle.data.authentication.Singleton;
import com.wherismyvehicle.whereismyvehicle.views.RegisterFragment;

public class RegisterFragmentPresenter {
    private RegisterFragment fragment;

    public RegisterFragmentPresenter(RegisterFragment registerFragment) {
        this.fragment = registerFragment;

        Singleton.getInstance().setOnRegisteredHandler(new Runnable() {
            @Override
            public void run() {
                fragment.onRegistered();
            }
        });
        Singleton.getInstance().setOnRegistrationFailedHandler(new Runnable() {
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

        Singleton.getInstance().logout();

        Singleton.getInstance().register(email, password);
    }

    public interface View {
        void invalidEmail();
        void failedPasswordRequirements();
        void failedPasswordConfirm();
        void onRegistered();
        void onRegisteredFailed();

    }
}
