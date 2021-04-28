package com.android.themovie;

import android.app.Application;

public class MovieApplication extends Application {

//    private ApiComponent apiComponent;
    @Override
    public void onCreate() {
        super.onCreate();

//        mApiComponent = DaggerApplicationComponent.builder()
//                .appModule(new AppModule(this))
//                .apiModule(new ApiModule("url"))
//                .build();
    }

//    public ApiComponent getNetComponent() {
//        return apiComponent;
//    }
}
