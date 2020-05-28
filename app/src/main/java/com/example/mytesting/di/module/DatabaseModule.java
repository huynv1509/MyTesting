package com.example.mytesting.di.module;

import android.content.Context;

import androidx.room.Room;

import com.example.mytesting.constants.Constants;
import com.example.mytesting.data.local.dao.RegisterDao;
import com.example.mytesting.data.local.dao.UserDao;
import com.example.mytesting.data.local.db.AppDatabase;
import com.example.mytesting.di.DatabaseInfo;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
    @Singleton
    @Provides
    AppDatabase provideAppDatabase(@NotNull Context context, @DatabaseInfo String databaseName) {
        return Room.databaseBuilder(context, AppDatabase.class, databaseName).build();
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return Constants.DB_NAME;
    }

    @Singleton
    @Provides
    UserDao provideUserDao(@NotNull AppDatabase appDatabase) {
        return appDatabase.userDao();
    }

    @Singleton
    @Provides
    RegisterDao provideRegisterDao(@NotNull AppDatabase appDatabase) {
        return appDatabase.registerDao();
    }
}
