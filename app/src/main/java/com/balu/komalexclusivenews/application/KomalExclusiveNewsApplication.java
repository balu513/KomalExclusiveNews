package com.balu.komalexclusivenews.application;

import android.app.Application;

import com.balu.komalexclusivenews.component.DaggerKomalNewsComponent;
import com.balu.komalexclusivenews.component.KomalNewsComponent;
import com.balu.komalexclusivenews.module.AppModule;

public class KomalExclusiveNewsApplication extends Application {


    private static KomalNewsComponent komalNewsComponent;

    @Override
    public void onCreate() {
        super.onCreate();

         komalNewsComponent = DaggerKomalNewsComponent.builder().appModule(new AppModule(getApplicationContext())).build();

    }

    public static KomalNewsComponent getKomalNewsComponent() {
        return komalNewsComponent;
    }
}


