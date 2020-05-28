package com.example.mytesting.di.module;

import com.example.mytesting.ui.detailscreen.activity.DetailActivity;
import com.example.mytesting.ui.mainscreen.activity.MainActivity;
import com.example.mytesting.ui.registerscreen.activity.RegisterActivity;
import com.example.mytesting.ui.splashscreen.SplashScreenActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract SplashScreenActivity contributeSplashScreenActivity();

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector
    abstract RegisterActivity contributeRegisterActivity();

    @ContributesAndroidInjector
    abstract DetailActivity contributeDetailActivity();
}
