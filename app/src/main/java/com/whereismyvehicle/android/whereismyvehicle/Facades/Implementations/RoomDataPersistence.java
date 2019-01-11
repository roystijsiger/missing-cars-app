package com.whereismyvehicle.android.whereismyvehicle.Facades.Implementations;

import com.whereismyvehicle.android.whereismyvehicle.Facades.DataPersistenceFacade;
import java.util.ArrayList;

public class RoomDataPersistence<T> implements DataPersistenceFacade<T> {

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
