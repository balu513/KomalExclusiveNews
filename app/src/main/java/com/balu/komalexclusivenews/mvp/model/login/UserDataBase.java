package com.balu.komalexclusivenews.mvp.model.login;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = User.class, version = 1, exportSchema = false)
public abstract class UserDataBase extends RoomDatabase {

    public static final String DB_NAME = "user_db";

    public abstract UserDao getUserDAO();

}
