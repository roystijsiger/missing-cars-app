package com.wherismyvehicle.whereismyvehicle.Data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;

public class CachedDataPersistence<T> implements DataPersistence<T> {
    private final Context context;
    private final Class objectClass;
    private WebServiceDataPersistence<T> realDataPersistence;
    private RoomDataPersistence<T> roomDataPersistence;

    public CachedDataPersistence(Context context, Class objectClass) {
        this.context = context;
        this.objectClass = objectClass;
        this.realDataPersistence = new WebServiceDataPersistence<>(objectClass);
        this.roomDataPersistence = new RoomDataPersistence<>(objectClass);
    }

    @Override
    public DataPersistenceAction<T> Fetch(final Object id) {
        // If connection is available. Download all and override all in Room.
        if(ConnectionAvailable()) {
            DataPersistenceAction<T> realDataPersistenceAction = realDataPersistence.Fetch(id);

            realDataPersistenceAction.AddHandler(new DataPersistenceEventHandler<T>() {
                @Override
                public void OnResult(final T result) {
                    roomDataPersistence.Delete(id).AddHandler(new DataPersistenceEventHandler() {
                        @Override
                        public void OnResult(Object args) {
                            roomDataPersistence.Insert(result);
                        }
                    });
                }
            });

            return realDataPersistenceAction;
        }

        // Without connection return the 'fake' data action
        return roomDataPersistence.Fetch(id);
    }

    @Override
    public DataPersistenceAction<ArrayList<T>> FetchAll() {
        // If connection is available. Download all and override all in Room.
        if(ConnectionAvailable()) {
            DataPersistenceAction<ArrayList<T>> realDataPersistenceAction = realDataPersistence.FetchAll();

            realDataPersistenceAction.AddHandler(new DataPersistenceEventHandler<ArrayList<T>>() {
                @Override
                public void OnResult(final ArrayList<T> result) {
                    roomDataPersistence.DeleteAll().AddHandler(new DataPersistenceEventHandler() {
                        @Override
                        public void OnResult(Object args) {
                            roomDataPersistence.Insert(result);
                        }
                    });
                }
            });

            return realDataPersistenceAction;
        }

        // Without connection return the 'fake' data action
        return roomDataPersistence.FetchAll();
    }

    @Override
    public DataPersistenceAction<T> Insert(T object) {
        if(ConnectionAvailable()) return realDataPersistence.Insert(object);

        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceAction<ArrayList<T>> Insert(ArrayList<T> object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceAction<T> Update(T object) {
        if(ConnectionAvailable()) return realDataPersistence.Update(object);

        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceAction Delete(Object id) {
        if(ConnectionAvailable()) {
            realDataPersistence.Delete(id);
            return null;
        }

        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceAction DeleteAll() {
        throw new UnsupportedOperationException();
    }

    private boolean ConnectionAvailable(){
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
