package com.example.mytesting.di.module;

import com.example.mytesting.ui.mainscreen.MainActivity;
import com.example.mytesting.ui.splashscreen.SplashScreenActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract SplashScreenActivity contributeSplashScreenActivity();

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();
}
