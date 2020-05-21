package com.example.mytesting.di.component;

import android.app.Application;

import com.example.mytesting.MyApplication;
import com.example.mytesting.di.module.ActivityModule;
import com.example.mytesting.di.module.ApiModule;
import com.example.mytesting.di.module.AppModule;
import com.example.mytesting.di.module.DatabaseModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityModule.class, ApiModule.class, DatabaseModule.class})
public interface AppComponent {

    void inject(MyApplication application);

    void inject(AppModule appModule);

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);

        @BindsInstance
        Builder appModule(AppModule module);

        @BindsInstance
        Builder apiModule(ApiModule module);

        @BindsInstance
        Builder databaseModule(DatabaseModule module);

        AppComponent build();
    }
}
