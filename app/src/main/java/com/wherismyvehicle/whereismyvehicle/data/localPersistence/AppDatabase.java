package com.wherismyvehicle.whereismyvehicle.data.localPersistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.wherismyvehicle.whereismyvehicle.models.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}