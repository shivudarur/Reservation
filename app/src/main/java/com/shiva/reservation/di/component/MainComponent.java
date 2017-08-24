package com.shiva.reservation.di.component;

import com.shiva.reservation.di.module.AppModule;
import com.shiva.reservation.di.module.MainModule;
import com.shiva.reservation.ui.component.splash.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by shivananda.darura on 23/08/17.
 */
@Singleton
@Component(modules = {AppModule.class, MainModule.class})
public interface MainComponent {

    void inject(SplashActivity activity);
}
