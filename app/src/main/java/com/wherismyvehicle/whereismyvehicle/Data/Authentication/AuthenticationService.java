package com.wherismyvehicle.whereismyvehicle.Data.Authentication;

import android.content.Context;

import com.google.gson.Gson;
import com.wherismyvehicle.whereismyvehicle.Data.DataPersistenceActionEventHandler;
import com.wherismyvehicle.whereismyvehicle.Data.DataPersistenceHttpAction;
import com.wherismyvehicle.whereismyvehicle.Data.WebService;
import com.wherismyvehicle.whereismyvehicle.Models.User;

import okhttp3.Request;
import okhttp3.RequestBody;

public class AuthenticationService extends WebService {
    private Runnable onRegistered;
    private Runnable onAuthenticated;

    public AuthenticationService(Context context) {
        super(context);
    }

    public void register(String email, String password) {
        Credentials credentials = new Credentials(email, password);

        String json = new Gson().toJson(credentials);

        Request request = createRequest("users")
                .post(RequestBody.create(JSON_CONTENT_TYPE, json))
                .build();

        DataPersistenceHttpAction<User> task = new DataPersistenceHttpAction<>(User.class);
        task.AddHandler(new DataPersistenceActionEventHandler<User>() {
            @Override
            public void OnResult(User result) {
                AuthenticationState.getInstance().setUser(result);
            }
        });


        client.newCall(request).enqueue(task);

        return;
    }
    public void onRegistered(Runnable runnable) {
        this.onRegistered = onRegistered;
    }

    public void authenticate(String email, String password) {
        Credentials credentials = new Credentials(email, password);

        String json = new Gson().toJson(credentials);

        Request request = createRequest("users/authenticate")
                .post(RequestBody.create(JSON_CONTENT_TYPE, json))
                .build();

        DataPersistenceHttpAction task = new DataPersistenceHttpAction(User.class);
        client.newCall(request).enqueue(task);

        return;
    }

    public void onAuthenticated(Runnable runnable) {
        this.onAuthenticated = runnable;
    }


}
