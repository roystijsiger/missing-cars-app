package com.wherismyvehicle.whereismyvehicle.Data;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class RoomDataPersistence<T> implements DataPersistence<T> {
    public RoomDataPersistence(Class objectClass) {
    }

    @Override
    public DataPersistenceAction<T> Fetch(Object id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceAction<ArrayList<T>> FetchAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceAction<T> Insert(T object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceAction<ArrayList<T>> Insert(ArrayList<T> object) {
        // throw new UnsupportedOperationException();

        // TODO: Actually delete all
        return new DataPersistenceAction().Invoke(object);
    }

    @Override
    public DataPersistenceAction<T> Update(T object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceAction Delete(Object id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceAction DeleteAll() {
        // throw new UnsupportedOperationException();

        // TODO: Actually delete all
        return new DataPersistenceAction().Invoke(true);
    }
}
