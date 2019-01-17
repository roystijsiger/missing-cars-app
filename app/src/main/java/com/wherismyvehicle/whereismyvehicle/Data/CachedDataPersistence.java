package com.wherismyvehicle.whereismyvehicle.Data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;

public class CachedDataPersistence<T> implements DataPersistence<T> {
    private final Context context;
    private WebServiceDataPersistence<T> realDataPersistence;
    private RoomDataPersistence<T> roomDataPersistence;

    public CachedDataPersistence(Context context) {
        this.context = context;
        this.realDataPersistence = new WebServiceDataPersistence<>();
        this.roomDataPersistence = new RoomDataPersistence<>();
    }

    @Override
    public DataPersistenceTask<T> Fetch(Object id) {
        if(ConnectionAvailable()) return realDataPersistence.Fetch(id);

        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceTask<ArrayList<T>> FetchAll() {
        // If connection is available. Download all and override all in Room.
        if(ConnectionAvailable()) {
            DataPersistenceTask<ArrayList<T>> objects = realDataPersistence.FetchAll();

            // TODO: Handle event and insert into room...
            // roomDataPersistence.DeleteAll();
            // roomDataPersistence.Insert(objects);
            return objects;
        }

        return roomDataPersistence.FetchAll();
    }

    @Override
    public DataPersistenceTask<T> Insert(T object) {
        if(ConnectionAvailable()) return realDataPersistence.Insert(object);

        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceTask<T> Insert(ArrayList<T> object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceTask<T> Update(T object) {
        if(ConnectionAvailable()) return realDataPersistence.Update(object);

        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceTask Delete(T object) {
        if(ConnectionAvailable()) {
            realDataPersistence.Delete(object);
            return null;
        }

        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceTask DeleteAll() {
        throw new UnsupportedOperationException();
    }

    private boolean ConnectionAvailable(){
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
