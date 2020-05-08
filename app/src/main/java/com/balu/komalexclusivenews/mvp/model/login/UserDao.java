package com.balu.komalexclusivenews.mvp.model.login;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createUser(User user);

    @Update
    void update(User user);

    @Query("SELECT * FROM usertable")
    LiveData<List<User>> getUsers();

    @Query("SELECT * FROM usertable WHERE name = :name AND password = :password")
    Single<User> getUser(String name, String password);


}
