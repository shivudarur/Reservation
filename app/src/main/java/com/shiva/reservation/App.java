package com.shiva.reservation;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;

import com.shiva.reservation.di.component.DaggerMainComponent;
import com.shiva.reservation.di.component.MainComponent;
import com.shiva.reservation.di.module.AppModule;
import com.shiva.reservation.di.module.NetModule;
import com.shiva.reservation.receiver.TablesResetBroadcastReceiver;

/**
 * Created by shivananda.darura on 23/08/17.
 */

public class App extends Application {

    private MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mainComponent = DaggerMainComponent.builder()
            .appModule(new AppModule(this))
            .netModule(new NetModule())
            .build();

        scheduleAlarm();
    }

    private void scheduleAlarm() {
        Intent intent = new Intent(this, TablesResetBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 0,
            intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
            10 * 1000, pendingIntent); //Set every 10 minutes.

    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }

}