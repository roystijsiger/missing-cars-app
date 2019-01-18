package com.wherismyvehicle.whereismyvehicle.Data;

import java.util.ArrayList;

public class DataPersistenceAction<T> {
    private ArrayList<DataPersistenceEventHandler<T>> handlers = new ArrayList<>();
    private boolean invoked = false;
    private T object;

    public DataPersistenceAction<T> AddHandler (DataPersistenceEventHandler<T> handler){
        // If the action is already invoked, execute the handler immediately
        if(invoked)  {
            handler.OnResult(object);
        }
        else{
            handlers.add(handler);
        }

        return this;
    }

    DataPersistenceAction<T> Invoke(T object) {
        invoked = true;
        this.object = object;

        for (DataPersistenceEventHandler<T> handler : handlers) {
            handler.OnResult(object);
        }

        return this;
    }
}
