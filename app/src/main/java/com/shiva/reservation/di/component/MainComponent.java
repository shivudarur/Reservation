package com.shiva.reservation.di.component;

import com.shiva.reservation.di.module.AppModule;
import com.shiva.reservation.di.module.NetModule;
import com.shiva.reservation.ui.component.home.HomeActivity;
import com.shiva.reservation.ui.component.splash.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by shivananda.darura on 23/08/17.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface MainComponent {

    void inject(SplashActivity activity);

    void inject(HomeActivity activity);
}
