package com.wherismyvehicle.whereismyvehicle;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.wherismyvehicle.whereismyvehicle.data.localPersistence.AppDatabase;
import com.wherismyvehicle.whereismyvehicle.models.User;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class AppDatabaseTest {
    private final String testUserEmail = "test@example.com";
    private final String testUserPassword = "admin123";
    private final String testUserToken = "dsajidosajwqoenodxjdzjdasojeiwaokdsadsokapk";

    private AppDatabase db;

    public AppDatabaseTest() {
    }

    @Before
    public void setUp() throws Exception {
        // Arrange
        Context context = InstrumentationRegistry.getContext();
        RoomDatabase.Builder<AppDatabase> databaseBuilder = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries();

        // Act
        db = databaseBuilder.build();

        // Assert no exception
    }

    @Test
    public void validateContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.wherismyvehicle.whereismyvehicle", appContext.getPackageName());
    }

    @Test
    public void buildUser(){
        // Arrange
        User user = new User();

        // Act
        user.setEmail(testUserEmail);
        user.setPassword(testUserPassword);
        user.setToken(testUserToken);

        // Assert
        Assert.assertEquals(user.getEmail(), testUserEmail);
        Assert.assertEquals(user.getPassword(), testUserPassword);
        Assert.assertEquals(user.getToken(), testUserToken);
    }

    @Test
    public void insertAndGetUser() {
        // Arrange
        User user = new User();
        user.setEmail(testUserEmail);
        user.setPassword(testUserPassword);
        user.setToken(testUserToken);

        // Act insertUser
        db.userDao().insertUser(user);

        // Act getUser
        User validationUser = db.userDao().getUser();

        // Assert get and insert
        Assert.assertEquals(validationUser.getEmail(), user.getEmail());

        // Password should not be stored
        Assert.assertNull(validationUser.getPassword());
        Assert.assertEquals(validationUser.getToken(), user.getToken());
    }

    @Test
    public void deleteUser(){
        // Arrange
        User user = new User();
        user.setEmail(testUserEmail);
        user.setPassword(testUserPassword);
        user.setToken(testUserToken);
        db.userDao().insertUser(user);

        // Act
        db.userDao().deleteUser();

        // Assert
        User validationUser = db.userDao().getUser();
        Assert.assertNull(validationUser);
    }

    @After
    public void tearDown() throws Exception {
        // Act
        db.close();

        // Assert no exception
    }
}