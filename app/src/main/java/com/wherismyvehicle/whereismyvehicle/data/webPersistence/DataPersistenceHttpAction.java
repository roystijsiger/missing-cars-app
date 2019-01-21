package com.wherismyvehicle.whereismyvehicle.data.webPersistence;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.wherismyvehicle.whereismyvehicle.data.DataPersistenceAction;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DataPersistenceHttpAction<T> extends DataPersistenceAction<T> implements Callback {

    private Gson gson;
    private Class serializationClass;

    public DataPersistenceHttpAction(Class serializationClass) {
        this.gson = new Gson();
        this.serializationClass = serializationClass;
    }

    @Override public void onFailure(@NonNull Call call, @NonNull IOException e) {
        throw new RuntimeException(e);
    }

    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        assert response.body() != null;
        Object result = gson.fromJson(response.body().charStream(), serializationClass);

        Invoke((T) result);
    }
}
