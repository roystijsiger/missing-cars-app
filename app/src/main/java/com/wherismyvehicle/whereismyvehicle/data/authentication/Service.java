package com.wherismyvehicle.whereismyvehicle.Data.Authentication;

import android.content.Context;

import com.google.gson.Gson;
import com.wherismyvehicle.whereismyvehicle.Data.DataPersistenceActionEventHandler;
import com.wherismyvehicle.whereismyvehicle.Data.WebPersistence.DataPersistenceHttpAction;
import com.wherismyvehicle.whereismyvehicle.Data.WebPersistence.WebService;
import com.wherismyvehicle.whereismyvehicle.Models.User;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.RequestBody;

public class Service extends WebService {
    Service(Context context) {
        super(context);
    }

    void register(String email, String password) {
        Credentials credentials = new Credentials(email, password);

        String json = new Gson().toJson(credentials);

        Request request = createRequest("users")
                .post(RequestBody.create(JSON_CONTENT_TYPE, json))
                .build();

        DataPersistenceHttpAction<User> task = new DataPersistenceHttpAction<User>(User.class){
            @Override
            public void onFailure(Call call, IOException e) {
                AuthenticationSingleton.getInstance().invokeOnRegistrationFailedHandler();
            }
        };

        task.AddHandler(new DataPersistenceActionEventHandler<User>() {
            @Override
            public void OnResult(User result) {
                AuthenticationSingleton.getInstance().setUser(result);
                AuthenticationSingleton.getInstance().invokeOnRegisteredHandler();
            }
        });

        client.newCall(request).enqueue(task);
    }

    void login(String email, String password) {
        Credentials credentials = new Credentials(email, password);

        String json = new Gson().toJson(credentials);

        Request request = createRequest("users/authenticate")
                .post(RequestBody.create(JSON_CONTENT_TYPE, json))
                .build();

        DataPersistenceHttpAction<User> task = new DataPersistenceHttpAction<User>(User.class){
            @Override
            public void onFailure(Call call, IOException e) {
                AuthenticationSingleton.getInstance().invokeOnLoginFailedHandler();
            }
        };

        task.AddHandler(new DataPersistenceActionEventHandler<User>() {
            @Override
            public void OnResult(User result) {
                AuthenticationSingleton.getInstance().setUser(result);
                AuthenticationSingleton.getInstance().invokeOnLoggedInHandler();
            }
        });

        client.newCall(request).enqueue(task);
    }
}
