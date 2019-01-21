package com.wherismyvehicle.whereismyvehicle.presenters;

import android.util.Patterns;
import com.wherismyvehicle.whereismyvehicle.data.authentication.Singleton;

public class RegisterFragmentPresenter {
    private View fragment;

    public RegisterFragmentPresenter(View registerFragment) {
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

    public void register(final String email, final String password, String passwordConfirm) {
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

        Singleton.getInstance().setOnLogoutHandler(new Runnable() {
            @Override
            public void run() {
                Singleton.getInstance().register(email, password);
            }
        }).logout();
    }

    public interface View {
        void invalidEmail();
        void failedPasswordRequirements();
        void failedPasswordConfirm();
        void onRegistered();
        void onRegisteredFailed();

    }
}
