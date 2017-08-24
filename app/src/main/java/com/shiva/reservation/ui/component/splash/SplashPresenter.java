package com.shiva.reservation.ui.component.splash;

import android.os.Bundle;

import com.shiva.reservation.ui.base.Presenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by shivananda.darura on 02/02/17.
 */

public class SplashPresenter extends Presenter<SplashView> {

    @Inject
    public SplashPresenter() {
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        pauseWhileANdNavigate();
    }

    private void pauseWhileANdNavigate() {
        Completable.timer(5, TimeUnit.SECONDS)
            .subscribe(() -> view.openHomeScreen());
    }
}
