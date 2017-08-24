package com.shiva.reservation.useCase.base;

import com.shiva.reservation.BuildConfig;

import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxTransformer {

    public <T> SingleTransformer<T, T> applyIoScheduler() {
        return single -> single
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError(throwable -> {
                if (BuildConfig.DEBUG) {
                    throwable.printStackTrace();
                }

                //TODO
                //Add crashlytics support
                //Crashlytics.logException(throwable);
            });
    }

    public <T> SingleTransformer<T, T> applyComputationScheduler() {
        return single -> single
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError(throwable -> {
                if (BuildConfig.DEBUG) {
                    throwable.printStackTrace();
                }
                //TODO
                //Add crashlytics support
                //Crashlytics.logException(throwable);
            });
    }
}
