package com.whereismyvehicle.android.whereismyvehicle.Facades;

import com.whereismyvehicle.android.whereismyvehicle.Models.Vehicle;

import java.util.ArrayList;

public interface DataPersistenceFacade <T> {
    void Insert(T object);
    ArrayList<T> GetAll();
    T Get(int id);
    void Delete(T object);
}
