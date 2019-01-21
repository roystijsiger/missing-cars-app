package com.wherismyvehicle.whereismyvehicle.data.webPersistence;

import android.content.Context;

import com.google.gson.Gson;
import com.wherismyvehicle.whereismyvehicle.data.DataPersistence;
import com.wherismyvehicle.whereismyvehicle.data.DataPersistenceAction;

import okhttp3.Request;
import okhttp3.RequestBody;

public class ModelPersistenceService<T> extends WebService implements DataPersistence<T> {
    private final String baseUrl;

    public ModelPersistenceService(Context context, String endpoint) {
        super(context);
        this.baseUrl = endpoint;
    }

    @Override
    public DataPersistenceAction<T> Fetch(Class serializationClass, Object id) {
        checkConnection();

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
}