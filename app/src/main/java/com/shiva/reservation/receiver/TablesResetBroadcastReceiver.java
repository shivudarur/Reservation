package com.shiva.reservation.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.shiva.reservation.App;
import com.shiva.reservation.useCase.SaveTableMapsUseCase;

import javax.inject.Inject;

/**
 * Created by shivananda.darura on 27/08/17.
 */

public class TablesResetBroadcastReceiver extends BroadcastReceiver {

    @Inject
    SaveTableMapsUseCase saveTableMapsUseCase;

    @Override
    public void onReceive(Context context, Intent intent) {
        ((App) context.getApplicationContext()).getMainComponent().inject(this);
        saveTableMapsUseCase.resetTableMaps();
    }
}