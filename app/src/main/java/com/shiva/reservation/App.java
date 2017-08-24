package com.shiva.reservation;

import android.app.Application;

import com.shiva.reservation.di.component.DaggerMainComponent;
import com.shiva.reservation.di.component.DaggerNetComponent;
import com.shiva.reservation.di.component.MainComponent;
import com.shiva.reservation.di.component.NetComponent;
import com.shiva.reservation.di.module.AppModule;
import com.shiva.reservation.di.module.NetModule;

/**
 * Created by shivananda.darura on 23/08/17.
 */

public class App extends Application {

    private MainComponent mainComponent;
    private NetComponent netComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        netComponent = DaggerNetComponent.builder()
            .appModule(new AppModule(this))
            .netModule(new NetModule())
            .build();

        mainComponent = DaggerMainComponent.builder()
            .appModule(new AppModule(this))
            .build();
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }

    public NetComponent getNetComponent() {
        return netComponent;
    }
}