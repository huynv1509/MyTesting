package com.example.mytesting.di.module;

import android.app.Application;
import android.content.Context;

import com.example.mytesting.utils.rxscheduler.AppScheduler;
import com.example.mytesting.utils.rxscheduler.SchedulerListener;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    @Singleton
    @Provides
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    SchedulerListener provideSchedulerListener() {
        return new AppScheduler();
    }
}
