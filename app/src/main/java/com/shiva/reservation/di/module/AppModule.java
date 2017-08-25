package com.shiva.reservation.di.module;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shivananda.darura on 23/08/17.
 */

@Module
public class AppModule {

    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    public Handler provideHandler() {
        return new Handler(Looper.getMainLooper());
    }

    @Provides
    public Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }
}
