package com.wherismyvehicle.whereismyvehicle.data.localPersistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.wherismyvehicle.whereismyvehicle.models.User;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    User getUser();

    @Insert
    void insertUser(User user);

    @Query("DELETE FROM user")
    void deleteUser();
}
