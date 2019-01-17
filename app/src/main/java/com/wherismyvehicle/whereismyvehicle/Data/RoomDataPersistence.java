package com.wherismyvehicle.whereismyvehicle.Data;

import java.util.ArrayList;

public class RoomDataPersistence<T> implements DataPersistence<T> {
    @Override
    public DataPersistenceTask<T> Fetch(Object id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceTask<ArrayList<T>> FetchAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceTask<T> Insert(T object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceTask<T> Insert(ArrayList<T> object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceTask<T> Update(T object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceTask Delete(T object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceTask DeleteAll() {
        throw new UnsupportedOperationException();
    }
}
