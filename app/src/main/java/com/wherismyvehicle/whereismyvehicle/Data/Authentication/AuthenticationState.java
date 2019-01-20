package com.wherismyvehicle.whereismyvehicle.Data.Authentication;

// Singleton
public class AuthenticationState {
    private boolean authenticated;
    private String token;
    private static final AuthenticationState instance = new AuthenticationState();

    public static AuthenticationState getInstance() {
        return instance;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public String getToken() {
        return token;
    }

    public void authenticate(String token){
        this.token = token;
        this.authenticated = true;
    }

    public void logout(){
        authenticated = false;
        token = null;
    }
}
