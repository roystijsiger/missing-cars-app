package com.wherismyvehicle.whereismyvehicle.Data;

import java.util.ArrayList;

public interface DataPersinstance <T> {
    T Fetch(Object id);
    ArrayList<T> FetchAll();
    T Insert(T object);
    T Update(T object);
    void Delete(T object);
}
