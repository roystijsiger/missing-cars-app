package com.wherismyvehicle.whereismyvehicle.Data;

import java.util.ArrayList;

public interface DataPersistence <T> {
    DataPersistenceTask<T> Fetch(Object id);
    DataPersistenceTask<ArrayList<T>> FetchAll();
    DataPersistenceTask<T> Insert(T object);
    DataPersistenceTask<T> Insert(ArrayList<T> object);
    DataPersistenceTask<T> Update(T object);
    DataPersistenceTask Delete(T object);
    DataPersistenceTask DeleteAll();
}
