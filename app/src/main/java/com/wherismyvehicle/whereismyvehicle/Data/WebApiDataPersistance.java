package com.wherismyvehicle.whereismyvehicle.Data;

import java.util.ArrayList;

public class WebApiDataPersistance<T> implements DataPersinstance<T> {

    @Override
    public T Fetch(Object id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<T> FetchAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T Insert(T object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T Update(T object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void Delete(T object) {
        throw new UnsupportedOperationException();
    }
}