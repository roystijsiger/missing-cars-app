package com.whereismyvehicle.android.whereismyvehicle.Facades.Implementations;

import com.whereismyvehicle.android.whereismyvehicle.Facades.DataPersistenceFacade;
import com.whereismyvehicle.android.whereismyvehicle.Models.Vehicle;

import java.util.ArrayList;

public class WebPersistence<T> implements DataPersistenceFacade<T> {
    @Override
    public void Insert(T object) {

    }

    @Override
    public ArrayList<T> GetAll() {
        return null;
    }

    @Override
    public T Get(int id) {
        return null;
    }

    @Override
    public void Delete(T object) {

    }
}