package com.wherismyvehicle.whereismyvehicle.Data;

public interface DataPersistenceEventHandler<T> {
    void OnResult(T result);
}
