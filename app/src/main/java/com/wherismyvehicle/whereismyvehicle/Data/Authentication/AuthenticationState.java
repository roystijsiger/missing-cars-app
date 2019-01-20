package com.wherismyvehicle.whereismyvehicle.Data.Authentication;

import android.content.Context;

import com.wherismyvehicle.whereismyvehicle.Models.User;
import com.wherismyvehicle.whereismyvehicle.Views.MainActivity;

import java.util.ArrayList;

// Singleton
public class AuthenticationState {
    private AuthenticationService authenticationService;
    private User user;

    private static AuthenticationState instance;
    private ArrayList<Runnable> onUserChangedHandlers = new ArrayList<>();

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

        return user.getToken();
    }

    public void login(String username, String password){
        this.authenticationService.authenticate(username, password);
    }

    public void register(String email, String password) {
        this.authenticationService.register(email, password);
    }

    public void logout(){
        user = null;
    }

    public void setUser(User user) {
        this.user = user;

        for (Runnable runnable: onUserChangedHandlers) {
            runnable.run();
        }
    }

    public void addOnUserChangedHandler(Runnable runnable){
        this.onUserChangedHandlers.add(runnable);
    }
}
