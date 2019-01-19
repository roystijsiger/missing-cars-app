package com.wherismyvehicle.whereismyvehicle.Data;

import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WebServiceDataPersistence<T> implements DataPersistence<T> {

    private final OkHttpClient client;
    private final Class objectClass;
    private final String baseUrl;

    public WebServiceDataPersistence(Class objectClass) {
        this.client = new OkHttpClient();
        this.objectClass = objectClass;
        this.baseUrl = "http://whereismyvehicle.azurewebsites.net/"
                .concat(objectClass.getSimpleName().toLowerCase())
                .concat("s");
    }

    @Override
    public DataPersistenceAction<T> Fetch(Object id) {
        Request request = new Request.Builder()
                .url(baseUrl.concat("/").concat(id.toString()))
                .build();

        DataPersistenceHttpAction task = new DataPersistenceHttpAction();

        client.newCall(request).enqueue(task);

        return task;
    }

    @Override
    public DataPersistenceAction<ArrayList<T>> FetchAll() {
        Request request = new Request.Builder()
                .url(baseUrl)
                .build();

        DataPersistenceHttpAction task = new DataPersistenceHttpAction();

        client.newCall(request).enqueue(task);

        return task;
    }

    @Override
    public DataPersistenceAction<T> Insert(T object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceAction<ArrayList<T>> Insert(ArrayList<T> object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceAction<T> Update(T object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceAction Delete(Object id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceAction DeleteAll() {
        throw new UnsupportedOperationException("Deleting all entities is not supported on the webservice");
    }
}