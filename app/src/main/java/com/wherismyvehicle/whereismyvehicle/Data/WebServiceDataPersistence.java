package com.wherismyvehicle.whereismyvehicle.Data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class WebServiceDataPersistence<T> implements DataPersistence<T> {

    public static final MediaType JSON_CONTENT_TYPE = MediaType.parse("application/json; charset=utf-8");

    private final OkHttpClient client;
    private final String baseUrl;
    private final Context context;

    public WebServiceDataPersistence(Context context, String endpoint) {
        this.context = context;
        this.client = new OkHttpClient();
        this.baseUrl = "http://whereismyvehicle.azurewebsites.net/".concat(endpoint);
    }

    @Override
    public DataPersistenceAction<T> Fetch(Class serializationClass, Object id) {
        Request request = createRequest(baseUrl.concat("/").concat(id.toString()))
            .build();

        DataPersistenceHttpAction task = new DataPersistenceHttpAction(serializationClass);

        client.newCall(request).enqueue(task);

        return task;
    }

    @Override
    public DataPersistenceAction<T[]> FetchAll(Class serializationClass) {
        Request request = createRequest(baseUrl)
            .build();

        DataPersistenceHttpAction task = new DataPersistenceHttpAction(serializationClass);

        client.newCall(request).enqueue(task);

        return task;
    }

    @Override
    public DataPersistenceAction<T> Insert(Class serializationClass, T object) {
        String json = new Gson().toJson(object);

        Request request = createRequest(baseUrl)
            .post(RequestBody.create(JSON_CONTENT_TYPE, json))
            .build();

        DataPersistenceHttpAction task = new DataPersistenceHttpAction(serializationClass);

        client.newCall(request).enqueue(task);

        return task;
    }

    @Override
    public DataPersistenceAction<T[]> Insert(Class serializationClass, T[] object) {
        String json = new Gson().toJson(object);

        Request request = createRequest(baseUrl)
                .post(RequestBody.create(JSON_CONTENT_TYPE, json))
                .build();

        DataPersistenceHttpAction task = new DataPersistenceHttpAction(serializationClass);

        client.newCall(request).enqueue(task);

        return task;
    }

    @Override
    public DataPersistenceAction<T> Update(Class serializationClass, T object) {
        String json = new Gson().toJson(object);

        Request request = createRequest(baseUrl)
                .patch(RequestBody.create(JSON_CONTENT_TYPE, json))
                .build();

        DataPersistenceHttpAction task = new DataPersistenceHttpAction(serializationClass);

        client.newCall(request).enqueue(task);

        return task;
    }

    @Override
    public DataPersistenceAction Delete(Class serializationClass, Object id) {
        Request request = createRequest(baseUrl.concat(id.toString()))
                .build();

        DataPersistenceHttpAction task = new DataPersistenceHttpAction(serializationClass);

        client.newCall(request).enqueue(task);

        return task;
    }

    @Override
    public DataPersistenceAction DeleteAll(Class serializationClass) {
        throw new UnsupportedOperationException("Deleting all entities is not supported on the webservice");
    }

    private Request.Builder createRequest(String endpoint) {
        return new Request.Builder()
            .url(endpoint)
            .addHeader("Authentication", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6InN0cmluZ0BleGFtcGxlLmNvbSIsIm5iZiI6MTU0NzkzNjQzMywiZXhwIjoxNTc5NDcyNDMzLCJpYXQiOjE1NDc5MzY0MzN9.1YINBnLk4S_kv434UjCLIogrDNAMqAOgczhReytz2Os");
    }

    private boolean connectionAvailable(){
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}