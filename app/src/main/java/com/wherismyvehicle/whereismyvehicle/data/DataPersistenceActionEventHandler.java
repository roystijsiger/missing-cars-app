package com.wherismyvehicle.whereismyvehicle.data;

public interface DataPersistenceActionEventHandler<T> {
    void OnResult(T result);
}
