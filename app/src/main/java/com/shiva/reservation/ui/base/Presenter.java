package com.shiva.reservation.ui.base;

import android.os.Bundle;

/**
 * Created by shivananda.darura on 23/08/17.
 */

public class Presenter<T extends Presenter.View> {

    protected T view;

    public interface View {
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
