package com.wherismyvehicle.whereismyvehicle.data.authentication;

import android.content.Context;

import com.google.gson.Gson;
import com.wherismyvehicle.whereismyvehicle.data.DataPersistenceActionEventHandler;
import com.wherismyvehicle.whereismyvehicle.data.webPersistence.DataPersistenceHttpAction;
import com.wherismyvehicle.whereismyvehicle.data.webPersistence.WebService;
import com.wherismyvehicle.whereismyvehicle.models.User;

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
                Singleton.getInstance().invokeOnRegistrationFailedHandler();
            }
        };

        task.AddHandler(new DataPersistenceActionEventHandler<User>() {
            @Override
            public void OnResult(User result) {
                Singleton.getInstance().setUser(result);
                Singleton.getInstance().invokeOnRegisteredHandler();
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
                Singleton.getInstance().invokeOnLoginFailedHandler();
            }
        };

        task.AddHandler(new DataPersistenceActionEventHandler<User>() {
            @Override
            public void OnResult(User result) {
                Singleton.getInstance().setUser(result);
                Singleton.getInstance().invokeOnLoggedInHandler();
            }
        });

        client.newCall(request).enqueue(task);
    }
}
