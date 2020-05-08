package com.balu.komalexclusivenews.module;

import android.content.Context;

import com.balu.komalexclusivenews.mvp.model.login.UserDao;
import com.balu.komalexclusivenews.mvp.model.login.UserDataBase;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {

    @Provides
    public UserDao provideUserDao(UserDataBase userDataBase){
        return userDataBase.getUserDAO();
    }

    @Provides
    public UserDataBase provideUserDataBase(Context context){
         return Room.databaseBuilder(context, UserDataBase.class, UserDataBase.DB_NAME).build();

    }


}
