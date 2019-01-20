package com.wherismyvehicle.whereismyvehicle.Data.Authentication;

import android.content.Context;

import com.wherismyvehicle.whereismyvehicle.Data.AppDatabase.AppDatabase;
import com.wherismyvehicle.whereismyvehicle.Data.AppDatabase.AppDatabaseSingleton;
import com.wherismyvehicle.whereismyvehicle.Models.User;

// Singleton
public class AuthenticationSingleton {
    private static AuthenticationSingleton instance;
    private AuthenticationService authenticationService;
    private Runnable onRegisteredHandler;
    private Runnable onRegistrationFailedHandler;
    private Runnable onLoggedInHandler;
    private Runnable onLoginFailedHandler;
    private Runnable onLogoutHandler;


    public AuthenticationSingleton(Context context) {
        authenticationService = new AuthenticationService(context);
    }

    public static AuthenticationSingleton getInstance() {
        return instance;
    }

    public static void instantiate(Context context) {
        instance = new AuthenticationSingleton(context);
    }

    public boolean isAuthenticated() {
        return getUser() != null;
    }

    public String getToken() {
        User user = getUser();

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
        AppDatabaseSingleton.getInstance().getAppDb().userDao().deleteUser();

        this.invokeOnLogoutHandler();
    }

    public void invokeOnRegisteredHandler() {
        if(onRegisteredHandler != null) {
            onRegisteredHandler.run();
        }
    }

    public AuthenticationSingleton setOnRegisteredHandler(Runnable onRegisteredHandler) {
        this.onRegisteredHandler = onRegisteredHandler;
        return this;
    }

    public void invokeOnRegistrationFailedHandler() {
        if (onRegistrationFailedHandler != null) {
            onRegistrationFailedHandler.run();
        }
    }

    public AuthenticationSingleton setOnRegistrationFailedHandler(Runnable onRegistrationFailedHandler) {
        this.onRegistrationFailedHandler = onRegistrationFailedHandler;
        return this;
    }

    public void invokeOnLoggedInHandler() {
        if (onLoggedInHandler != null) {
            onLoggedInHandler.run();
        }
    }

     public AuthenticationSingleton setOnLoggedInHandler(Runnable onLoggedInHandler) {
        this.onLoggedInHandler = onLoggedInHandler;
        return this;
    }

    public void invokeOnLoginFailedHandler() {
        if (onLoginFailedHandler != null) {
            onLoginFailedHandler.run();
        }
    }

    public AuthenticationSingleton setOnLoginFailedHandler(Runnable onLoginFailedHandler) {
        this.onLoginFailedHandler = onLoginFailedHandler;
        return this;
    }

    public void invokeOnLogoutHandler() {
        if (onLogoutHandler != null) {
            onLogoutHandler.run();
        }
    }

    public AuthenticationSingleton setOnLogoutHandler(Runnable onLogoutHandler) {
        this.onLogoutHandler = onLogoutHandler;
        return this;
    }

    private User getUser(){
        return AppDatabaseSingleton.getInstance().getAppDb().userDao().getUser();
    }

    public void setUser(User user) {
        AppDatabaseSingleton.getInstance().getAppDb().userDao().deleteUser();
        AppDatabaseSingleton.getInstance().getAppDb().userDao().insertUser(user);
    }
}
