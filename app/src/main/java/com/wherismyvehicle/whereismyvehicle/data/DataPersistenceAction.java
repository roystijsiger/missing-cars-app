package com.wherismyvehicle.whereismyvehicle.data;

import java.util.ArrayList;

public class DataPersistenceAction<T> {
    private ArrayList<DataPersistenceActionEventHandler<T>> handlers = new ArrayList<>();
    private boolean invoked = false;
    private T object;

    public DataPersistenceAction<T> AddHandler (DataPersistenceActionEventHandler<T> handler){
        // If the action is already invoked, execute the handler immediately
        if(invoked)  {
            handler.OnResult(object);
        }
        else{
            handlers.add(handler);
        }

        return this;
    }

    protected DataPersistenceAction<T> Invoke(T object) {
        invoked = true;
        this.object = object;

        for (DataPersistenceActionEventHandler<T> handler : handlers) {
            handler.OnResult(object);
        }

        return this;
    }
}
