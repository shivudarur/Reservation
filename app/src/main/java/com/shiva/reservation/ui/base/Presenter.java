package com.shiva.reservation.ui.base;

import android.os.Bundle;

/**
 * Created by shivananda.darura on 23/08/17.
 */

public class Presenter<T extends Presenter.View> {

    protected T view;

    public void onStart() {

    }

    public void onStop() {

    }

    public interface View extends ActionBarView {

    }

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

    public void initialize(Bundle extras) {
    }
}
