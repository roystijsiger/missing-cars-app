package com.wherismyvehicle.whereismyvehicle.Data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

class DataPersistenceHttpAction<T> extends DataPersistenceAction<T> implements Callback {

    private Type type;
    private Gson gson;

    public DataPersistenceHttpAction() {
        type = new TypeToken<T>() {}.getType();
        gson = new Gson();
    }

    @Override public void onFailure(Call call, IOException e) {
        throw new RuntimeException(e);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        T result = gson.fromJson(response.body().charStream(), type);
        Invoke(result);
    }
}
