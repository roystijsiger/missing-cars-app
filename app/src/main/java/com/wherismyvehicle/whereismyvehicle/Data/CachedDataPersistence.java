package com.wherismyvehicle.whereismyvehicle.Data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CachedDataPersistence<T> implements DataPersistence<T> {
    private final Context context;
    private WebServiceDataPersistence<T> realDataPersistence;

    public CachedDataPersistence(Context context, String endpoint) {
        this.context = context;
        this.realDataPersistence = new WebServiceDataPersistence<>(endpoint);
    }

    @Override
    public DataPersistenceAction<T> Fetch(Class serializationClass, final Object id) {


        return realDataPersistence.Fetch(serializationClass, id);
    }

    @Override
    public DataPersistenceAction<T[]> FetchAll(Class serializationClass) {
//        // If connection is available. Download all and override all in Room.
//        if(ConnectionAvailable()) {
//            DataPersistenceAction<ArrayList<T>> realDataPersistenceAction = realDataPersistence.FetchAll();
//
//            realDataPersistenceAction.AddHandler(new DataPersistenceEventHandler<ArrayList<T>>() {
//                @Override
//                public void OnResult(final ArrayList<T> result) {
//                    roomDataPersistence.DeleteAll().AddHandler(new DataPersistenceEventHandler() {
//                        @Override
//                        public void OnResult(Object args) {
//                            roomDataPersistence.Insert(result);
//                        }
//                    });
//                }
//            });
//
//            return realDataPersistenceAction;
//        }
//
//        // Without connection return the 'fake' data action
//        return roomDataPersistence.FetchAll();

        return realDataPersistence.FetchAll(serializationClass);
    }

    @Override
    public DataPersistenceAction<T> Insert(Class serializationClass, T object) {
        if(ConnectionAvailable()) return realDataPersistence.Insert(serializationClass, object);

        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceAction<T[]> Insert(Class serializationClass, T[] object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceAction<T> Update(Class serializationClass, T object) {
        if(ConnectionAvailable()) return realDataPersistence.Update(serializationClass, object);

        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceAction Delete(Class serializationClass, Object id) {
        if(ConnectionAvailable()) {
            realDataPersistence.Delete(serializationClass, id);
            return null;
        }

        throw new UnsupportedOperationException();
    }

    @Override
    public DataPersistenceAction DeleteAll(Class serializationClass) {
        throw new UnsupportedOperationException();
    }

    private boolean ConnectionAvailable(){
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
