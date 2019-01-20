package com.wherismyvehicle.whereismyvehicle.Data.AppDatabase;

import android.arch.persistence.room.Room;
import android.content.Context;

public class AppDatabaseSingleton {
    private static AppDatabaseSingleton instance;
    private AppDatabase db;

    private AppDatabaseSingleton(Context context) {
        this.db = Room.databaseBuilder(context, AppDatabase.class, "whereismyvehicle-db").allowMainThreadQueries().build();
    }

    public static void instantiate(Context context){
        instance = new AppDatabaseSingleton(context);
    }

    public static AppDatabaseSingleton getInstance(){
        return instance;
    }

    public AppDatabase getAppDb(){
        return db;
    }
}
