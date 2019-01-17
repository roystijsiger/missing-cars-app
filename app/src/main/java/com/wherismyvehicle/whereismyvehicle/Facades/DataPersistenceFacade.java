package com.wherismyvehicle.whereismyvehicle.Facades;

import com.wherismyvehicle.whereismyvehicle.Models.Vehicle;

import java.util.ArrayList;

public interface DataPersistenceFacade <T> {
    void Insert(T object);
    ArrayList<T> GetAll();
    T Get(int id);
    void Delete(T object);
}
