package com.shiva.reservation.ui.component.home;

import android.os.Bundle;

import com.shiva.reservation.R;
import com.shiva.reservation.model.Customer;
import com.shiva.reservation.ui.base.RxPresenter;
import com.shiva.reservation.useCase.base.GetCustomersUseCase;
import com.shiva.reservation.useCase.base.RxTransformer;
import com.shiva.reservation.useCase.base.SaveCustomersUseCase;
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
    private final SaveCustomersUseCase saveCustomersUseCase;

    @Inject
    public HomePresenter(GetCustomersUseCase getCustomersUseCase, ErrorUtils errorUtils,
                         RxTransformer rxTransformer, SaveCustomersUseCase saveCustomersUseCase) {
        this.getCustomersUseCase = getCustomersUseCase;
        this.errorUtils = errorUtils;
        this.rxTransformer = rxTransformer;
        this.saveCustomersUseCase = saveCustomersUseCase;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        view.setTitle(R.string.customers);
        getCustomers();
    }

    private void getCustomers() {
        view.showProgress("");
        compositeDisposable.add(getCustomersUseCase.getCustomers(false)
            .compose(rxTransformer.applyComputationScheduler())
            .subscribe((listResponseWrapper) -> {
                    final List<Customer> customerList = listResponseWrapper.getResponse();
                    if (!errorUtils.isEmptyCollection(customerList)) {
                        view.hideProgress();
                        view.showCustomers(customerList, position -> {

                        });
                    } else {
                        //There are no data in the local database.
                        //Try to get from remote
                        getCustomersFromBackend();
                    }
                },
                throwable -> {
                    processDataLoadingError();
                }));
    }

    private void getCustomersFromBackend() {
        compositeDisposable.add(getCustomersUseCase.getCustomers(true)
            .compose(rxTransformer.applyIoScheduler())
            .subscribe((listResponseWrapper) -> {
                    view.hideProgress();
                    final List<Customer> customerList = listResponseWrapper.getResponse();
                    if (!errorUtils.isEmptyCollection(customerList)) {
                        view.showCustomers(customerList, position -> {

                        });
                        //Save Customer list in local Db
                        saveCustomersUseCase.saveCustomers(customerList);
                    }
                },
                throwable -> {
                    processDataLoadingError();
                }));
    }

    private void processDataLoadingError() {
        view.hideProgress();
        view.showError(R.string.data_loading_error);
    }
}
