package com.wherismyvehicle.whereismyvehicle.Data;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class WebServiceDataPersistence<T> implements DataPersistence<T> {

    private final OkHttpClient client;
    private final String baseUrl;

    public WebServiceDataPersistence(String endpoint) {
        this.client = new OkHttpClient();
        this.baseUrl = "http://whereismyvehicle.azurewebsites.net/".concat(endpoint);
    }

    @Override
    public DataPersistenceAction<T> Fetch(Class serializationClass, Object id) {
        Request request = new Request.Builder()
                .url(baseUrl.concat("/").concat(id.toString()))
                .build();

        DataPersistenceHttpAction task = new DataPersistenceHttpAction(serializationClass);

        client.newCall(request).enqueue(task);

        return task;
    }

    @Override
    public DataPersistenceAction<T[]> FetchAll(Class serializationClass) {
        Request request = new Request.Builder()
                .url(baseUrl)
                .build();

        DataPersistenceHttpAction task = new DataPersistenceHttpAction(serializationClass);

        client.newCall(request).enqueue(task);

        return task;
    }

    @Override
    public DataPersistenceAction<T> Insert(Class serializationClass, T object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceAction<T[]> Insert(Class serializationClass, T[] object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceAction<T> Update(Class serializationClass, T object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceAction Delete(Class serializationClass, Object id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceAction DeleteAll(Class serializationClass) {
        throw new UnsupportedOperationException("Deleting all entities is not supported on the webservice");
    }
}