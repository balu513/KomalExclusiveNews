package com.balu.komalexclusivenews.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context getContext() {
        return context;
    }

    Context context;
}
