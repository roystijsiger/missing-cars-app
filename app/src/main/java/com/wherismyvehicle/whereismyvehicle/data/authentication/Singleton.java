package com.wherismyvehicle.whereismyvehicle.Data.Authentication;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.wherismyvehicle.whereismyvehicle.Data.LocalPersistence.AppDatabase;
import com.wherismyvehicle.whereismyvehicle.Models.User;

// Singleton
public class AuthenticationSingleton {
    private static AuthenticationSingleton instance;
    private static AppDatabase db;

    private Service service;
    private Runnable onRegisteredHandler;
    private Runnable onRegistrationFailedHandler;
    private Runnable onLoggedInHandler;
    private Runnable onLoginFailedHandler;
    private Runnable onLogoutHandler;


    public AuthenticationSingleton(Context context) {
        service = new Service(context);
    }

    public static AuthenticationSingleton getInstance() {
        return instance;
    }

    public static void instantiate(Context context) {
        instance = new AuthenticationSingleton(context);
        db = Room.databaseBuilder(context, AppDatabase.class, "whereismyvehicle-db").allowMainThreadQueries().build();
    }

    public boolean isAuthenticated() {
        return getUser() != null;
    }

    public String getEmail(){
        User user = getUser();
        if(user != null) {
            return user.getEmail();
        }
        else{
            return null;
        }
    }

    public String getToken() {
        User user = getUser();

        if(user == null) return null;
        if(user.getToken().isEmpty()) return null;

        return user.getToken();
    }

    public void login(String email, String password){
        this.service.login(email, password);
    }

    public void register(String email, String password) {
        this.service.register(email, password);
    }

    public void logout(){
        db.userDao().deleteUser();

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
        return db.userDao().getUser();
    }

    public void setUser(User user) {
        db.userDao().deleteUser();
        db.userDao().insertUser(user);
    }
}
