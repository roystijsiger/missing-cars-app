package com.wherismyvehicle.whereismyvehicle.Data;

import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WebServiceDataPersistence<T> implements DataPersistence<T> {

    private final OkHttpClient client;

    public WebServiceDataPersistence() {
        this.client = new OkHttpClient();
    }

    @Override
    public DataPersistenceTask<T> Fetch(Object id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceTask<ArrayList<T>> FetchAll() {
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();

        DataPersistenceHttpTask task = new DataPersistenceHttpTask() {
            @Override
            public void onResponse(Call call, Response response) {
                // TODO: https://github.com/square/okhttp/wiki/Recipes#parse-a-json-response-with-moshi
                // Then call result with result object

                // onResult(new Object());
            }
        };

        client.newCall(request).enqueue(task);

        return task;
    }

    @Override
    public DataPersistenceTask<T> Insert(T object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceTask<T> Insert(ArrayList<T> object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceTask<T> Update(T object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceTask Delete(T object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceTask DeleteAll() {
        throw new UnsupportedOperationException("Deleting all entities is not supported on the webservice");
    }
}