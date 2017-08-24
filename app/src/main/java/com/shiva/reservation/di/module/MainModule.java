package com.shiva.reservation.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shiva.reservation.App;
import com.shiva.reservation.util.ConfigurationManager;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.shiva.reservation.util.Constants.BASE_URL_DEPENDENCY;

/**
 * Created by shivananda.darura on 23/08/17.
 */
@Module
public class MainModule {

    @Provides
    @Singleton
    public Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
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

//    @Provides
//    public DataRepository provideDataRepository(ApiRepository apiRepository, LocalRepository localRepository) {
//        return new DataRepository(apiRepository, localRepository);
//    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }
}
