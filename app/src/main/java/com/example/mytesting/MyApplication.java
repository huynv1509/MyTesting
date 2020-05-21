package com.example.mytesting;

import android.app.Activity;
import android.app.Application;

import androidx.fragment.app.Fragment;

import com.example.mytesting.di.component.DaggerAppComponent;
import com.example.mytesting.di.module.ApiModule;
import com.example.mytesting.di.module.AppModule;
import com.example.mytesting.di.module.DatabaseModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MyApplication extends Application implements HasActivityInjector, HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder().application(this).appModule(new AppModule()).apiModule(new ApiModule()).databaseModule(new DatabaseModule()).build().inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
