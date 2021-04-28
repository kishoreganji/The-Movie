package com.android.themovie.dagger;


import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Application application;
    AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplicatio() {
        return application;
    }
}


