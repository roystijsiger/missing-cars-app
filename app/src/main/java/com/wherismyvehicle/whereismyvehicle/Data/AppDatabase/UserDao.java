package com.wherismyvehicle.whereismyvehicle.Data.AppDatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.wherismyvehicle.whereismyvehicle.Models.User;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    User getUser();

    @Insert
    void insertUser(User user);

    @Query("DELETE FROM user")
    void deleteUser();
}
