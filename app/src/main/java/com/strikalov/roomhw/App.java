package com.strikalov.roomhw;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.strikalov.roomhw.model.database.AppDatabase;

public class App extends Application {

    private static App instance;

    private AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "database").build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
