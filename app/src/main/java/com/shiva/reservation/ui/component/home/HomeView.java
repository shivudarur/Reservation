package com.shiva.reservation.ui.component.home;

import com.shiva.reservation.model.Customer;
import com.shiva.reservation.ui.base.ErrorView;
import com.shiva.reservation.ui.base.Presenter;
import com.shiva.reservation.ui.base.ProgressView;
import com.shiva.reservation.ui.base.listeners.RecyclerItemListener;

import java.util.List;

/**
 * Created by shivananda.darura on 02/02/17.
 */

public interface HomeView extends Presenter.View, ProgressView, ErrorView {
    void openTableSelectionScreen();

    void showCustomers(List<Customer> customerList, RecyclerItemListener recyclerItemListener);
}
