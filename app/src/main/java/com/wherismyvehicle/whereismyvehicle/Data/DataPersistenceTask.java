package com.wherismyvehicle.whereismyvehicle.Data;

public interface DataPersistenceTask<T> {
    void onResult (T object);
}
