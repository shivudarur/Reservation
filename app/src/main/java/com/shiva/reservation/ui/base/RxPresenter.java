package com.shiva.reservation.ui.base;

import android.os.Bundle;

import com.shiva.reservation.BuildConfig;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.internal.functions.Functions;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * Created by daniel.leal on 15/11/16.
 */

public abstract class RxPresenter<V extends Presenter.View> extends Presenter {

    protected V view;
    protected CompositeDisposable compositeDisposable;

    @Override
    public void initialize(Bundle extras) {
        compositeDisposable = new CompositeDisposable();
        if (!BuildConfig.DEBUG) {
            RxJavaPlugins.setErrorHandler(Functions.emptyConsumer());
        }
        super.initialize(extras);
    }

    @Override
    public void setView(View view) {
        super.setView(view);
        this.view = (V) view;
    }

    public void onStart() {
        super.onStart();
        if (compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
    }

    public void onStop() {
        super.onStop();
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}