package com.shiva.reservation.ui.component.home;

import android.os.Bundle;

import com.shiva.reservation.R;
import com.shiva.reservation.model.Customer;
import com.shiva.reservation.ui.base.RxPresenter;
import com.shiva.reservation.ui.base.listeners.RecyclerItemListener;
import com.shiva.reservation.useCase.base.GetCustomersUseCase;
import com.shiva.reservation.useCase.base.RxTransformer;
import com.shiva.reservation.util.ErrorUtils;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by shivananda.darura on 02/02/17.
 */

public class HomePresenter extends RxPresenter<HomeView> {

    private final GetCustomersUseCase getCustomersUseCase;
    private final ErrorUtils errorUtils;
    private final RxTransformer rxTransformer;

    @Inject
    public HomePresenter(GetCustomersUseCase getCustomersUseCase, ErrorUtils errorUtils, RxTransformer rxTransformer) {
        this.getCustomersUseCase = getCustomersUseCase;
        this.errorUtils = errorUtils;
        this.rxTransformer = rxTransformer;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        getCustomers();
    }

    private void getCustomers() {
        view.showProgress("");
        compositeDisposable.add(getCustomersUseCase.getCustomers()
            .compose(rxTransformer.applyIoScheduler())
            .subscribe((listResponseWrapper) -> {
                    view.hideProgress();
                    final List<Customer> customerList = listResponseWrapper.getResponse();
                    if (!errorUtils.isEmptyCollection(customerList)) {
                        view.showCustomers(customerList, position -> {

                        });
                    }
                },
                throwable -> {
                    view.hideProgress();
                    view.showError(R.string.data_download_error);
                }));
    }
}
