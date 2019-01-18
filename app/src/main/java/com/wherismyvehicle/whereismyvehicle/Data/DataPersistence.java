package com.wherismyvehicle.whereismyvehicle.Data;

import java.lang.reflect.Type;
import java.util.ArrayList;

public interface DataPersistence <T> {


    DataPersistenceAction<T> Fetch(Object id);
    DataPersistenceAction<ArrayList<T>> FetchAll();
    DataPersistenceAction<T> Insert(T object);
    DataPersistenceAction<ArrayList<T>> Insert(ArrayList<T> object);
    DataPersistenceAction<T> Update(T object);
    DataPersistenceAction Delete(Object id);
    DataPersistenceAction DeleteAll();
}
