package com.wherismyvehicle.whereismyvehicle.Data;

public interface DataPersistenceActionEventHandler<T> {
    void OnResult(T result);
}
