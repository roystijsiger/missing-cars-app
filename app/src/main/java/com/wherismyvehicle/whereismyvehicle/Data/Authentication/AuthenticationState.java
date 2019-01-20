package com.wherismyvehicle.whereismyvehicle.Data.Authentication;

import android.content.Context;

import com.wherismyvehicle.whereismyvehicle.Models.User;

// Singleton
public class AuthenticationState {
    private AuthenticationService authenticationService;
    private User user;

    private static AuthenticationState instance;

    private Runnable onRegisteredHandler;
    private Runnable onRegistrationFailedHandler;
    private Runnable onLoggedInHandler;
    private Runnable onLoginFailedHandler;
    private Runnable onLogoutHandler;


    public AuthenticationState(Context context) {
        authenticationService = new AuthenticationService(context);
    }

    public static AuthenticationState getInstance() {
        return instance;
    }

    public static void instantiate(Context context) {
        instance = new AuthenticationState(context);
    }

    public boolean isAuthenticated() {
        return user != null;
    }

    public String getToken() {
        if(user == null) return null;
        if(user.getToken().isEmpty()) return null;

        return user.getToken();
    }

    public void login(String email, String password){
        this.authenticationService.login(email, password);
    }

    public void register(String email, String password) {
        this.authenticationService.register(email, password);
    }

    public void logout(){
        user = null;
        this.invokeOnLogoutHandler();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void invokeOnRegisteredHandler() {
        if(onRegisteredHandler != null) {
            onRegisteredHandler.run();
        }
    }

    public AuthenticationState setOnRegisteredHandler(Runnable onRegisteredHandler) {
        this.onRegisteredHandler = onRegisteredHandler;
        return this;
    }

    public void invokeOnRegistrationFailedHandler() {
        if (onRegistrationFailedHandler != null) {
            onRegistrationFailedHandler.run();
        }
    }

    public AuthenticationState setOnRegistrationFailedHandler(Runnable onRegistrationFailedHandler) {
        this.onRegistrationFailedHandler = onRegistrationFailedHandler;
        return this;
    }

    public void invokeOnLoggedInHandler() {
        if (onLoggedInHandler != null) {
            onLoggedInHandler.run();
        }
    }
     public AuthenticationState setOnLoggedInHandler(Runnable onLoggedInHandler) {
        this.onLoggedInHandler = onLoggedInHandler;
        return this;
    }

    public void invokeOnLoginFailedHandler() {
        if (onLoginFailedHandler != null) {
            onLoginFailedHandler.run();
        }
    }

    public AuthenticationState setOnLoginFailedHandler(Runnable onLoginFailedHandler) {
        this.onLoginFailedHandler = onLoginFailedHandler;
        return this;
    }

    public void invokeOnLogoutHandler() {
        if (onLogoutHandler != null) {
            onLogoutHandler.run();
        }
    }

    public AuthenticationState setOnLogoutHandler(Runnable onLogoutHandler) {
        this.onLogoutHandler = onLogoutHandler;
        return this;
    }
}
