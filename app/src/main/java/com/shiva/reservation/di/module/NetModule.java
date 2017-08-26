package com.shiva.reservation.di.module;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shiva.reservation.model.TableMap;
import com.shiva.reservation.useCase.base.RxTransformer;
import com.shiva.reservation.util.ConfigurationManager;
import com.shiva.reservation.util.gson.TableMapDeserializer;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.shiva.reservation.util.Constants.BASE_URL_DEPENDENCY;
import static com.shiva.reservation.util.Constants.TIMEOUT_CONNECT;
import static com.shiva.reservation.util.Constants.TIMEOUT_READ;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by shivananda.darura on 23/08/17.
 */

@Module
public class NetModule {

    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(TableMap.class, new TableMapDeserializer())
            .create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient() {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.connectTimeout(TIMEOUT_CONNECT, SECONDS);
        okHttpBuilder.readTimeout(TIMEOUT_READ, SECONDS);
        return okHttpBuilder.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient, @Named(BASE_URL_DEPENDENCY) String baseUrl) {
        return new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
    }

    @Provides
    @Singleton
    public RxTransformer providesRxTransformer() {
        return new RxTransformer();
    }

    @Provides
    @Named(BASE_URL_DEPENDENCY)
    public String provideBaseUrl(ConfigurationManager configurationManager) {
        return configurationManager.getBaseURL();
    }
}
