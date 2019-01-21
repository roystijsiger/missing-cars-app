package com.wherismyvehicle.whereismyvehicle.data.webPersistence;

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

    @Override public void onFailure(Call call, IOException e) {
        throw new RuntimeException(e);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        Object result = gson.fromJson(response.body().charStream(), serializationClass);

        Invoke((T) result);
    }
}
