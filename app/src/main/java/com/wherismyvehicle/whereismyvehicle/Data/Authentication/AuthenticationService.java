package com.wherismyvehicle.whereismyvehicle.Data.Authentication;

import android.content.Context;

import com.wherismyvehicle.whereismyvehicle.Data.WebService;

public class AuthenticationService extends WebService {
    public AuthenticationService(Context context) {
        super(context);
    }

    public boolean Authenticate(String email, String password) {
        Credentials credentials = new Credentials(email, password);

        // TODO: Request

        return true;
    }
}
