package com.wherismyvehicle.whereismyvehicle.Data;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

class DataPersistenceHttpTask<T> implements DataPersistenceTask<T>, Callback {
    @Override public void onFailure(Call call, IOException e) {
        throw new RuntimeException(e);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {

    }

    @Override
    public void onResult(T object) {
        throw new UnsupportedOperationException("Please override the onResult method of DataPersistenceTask.");
    }
}
