package ru.kvisaz.wotolenemer.dagger;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    Application mApplication;

    public AppModule(Application application){
        mApplication = application;
    }

    @Provides
    @Singleton
    Context providesApplicationContext(){
        return mApplication;
    }
}
