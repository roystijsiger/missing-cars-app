package com.wherismyvehicle.whereismyvehicle.data.authentication;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.wherismyvehicle.whereismyvehicle.data.localPersistence.AppDatabase;
import com.wherismyvehicle.whereismyvehicle.models.User;

// Singleton
public class Singleton {
    private static Singleton instance;
    private static AppDatabase db;

    private Service service;
    private Runnable onRegisteredHandler;
    private Runnable onRegistrationFailedHandler;
    private Runnable onLoggedInHandler;
    private Runnable onLoginFailedHandler;
    private Runnable onLogoutHandler;


    private Singleton(Context context) {
        service = new Service(context);
    }

    public static Singleton getInstance() {
        return instance;
    }

    public static void instantiate(Context context) {
        instance = new Singleton(context);
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

    void invokeOnRegisteredHandler() {
        if(onRegisteredHandler != null) {
            onRegisteredHandler.run();
        }
    }

    public Singleton setOnRegisteredHandler(Runnable onRegisteredHandler) {
        this.onRegisteredHandler = onRegisteredHandler;
        return this;
    }

    void invokeOnRegistrationFailedHandler() {
        if (onRegistrationFailedHandler != null) {
            onRegistrationFailedHandler.run();
        }
    }

    public Singleton setOnRegistrationFailedHandler(Runnable onRegistrationFailedHandler) {
        this.onRegistrationFailedHandler = onRegistrationFailedHandler;
        return this;
    }

    void invokeOnLoggedInHandler() {
        if (onLoggedInHandler != null) {
            onLoggedInHandler.run();
        }
    }

     public Singleton setOnLoggedInHandler(Runnable onLoggedInHandler) {
        this.onLoggedInHandler = onLoggedInHandler;
        return this;
    }

    void invokeOnLoginFailedHandler() {
        if (onLoginFailedHandler != null) {
            onLoginFailedHandler.run();
        }
    }

    public Singleton setOnLoginFailedHandler(Runnable onLoginFailedHandler) {
        this.onLoginFailedHandler = onLoginFailedHandler;
        return this;
    }

    private void invokeOnLogoutHandler() {
        if (onLogoutHandler != null) {
            onLogoutHandler.run();
        }
    }

    public Singleton setOnLogoutHandler(Runnable onLogoutHandler) {
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
