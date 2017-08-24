package com.shiva.reservation.data.remote;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;

@Singleton
public class ServiceGenerator {

    private final Retrofit retrofit;

    @Inject
    public ServiceGenerator(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public <S> S createService(Class<S> serviceClass, String baseUrl) {
        return retrofit.create(serviceClass);
    }
}
