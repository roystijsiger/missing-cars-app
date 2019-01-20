package com.wherismyvehicle.whereismyvehicle.Data.AppDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.wherismyvehicle.whereismyvehicle.Models.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}