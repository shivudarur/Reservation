package com.shiva.reservation;

import android.app.Application;

import com.shiva.reservation.di.component.DaggerMainComponent;
import com.shiva.reservation.di.component.MainComponent;
import com.shiva.reservation.di.module.AppModule;
import com.shiva.reservation.di.module.NetModule;

/**
 * Created by shivananda.darura on 23/08/17.
 */

public class App extends Application {

    private MainComponent mainScreenComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mainScreenComponent = DaggerMainComponent.builder()
            .appModule(new AppModule(this))
            .netModule(new NetModule())
            .build();
    }

    public MainComponent getMainScreenComponent() {
        return mainScreenComponent;
    }

}