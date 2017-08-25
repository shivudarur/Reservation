package com.shiva.reservation.useCase.base;

import android.support.annotation.NonNull;

import com.shiva.reservation.data.DataRepository;
import com.shiva.reservation.data.ResponseWrapper;
import com.shiva.reservation.model.Customer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by shivananda.darura on 24/08/17.
 */

public class GetCustomersUseCase {

    private final DataRepository dataRepository;

    @Inject
    public GetCustomersUseCase(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @NonNull
    public Single<ResponseWrapper<List<Customer>>> getCustomers() {
        return dataRepository.getCustomers();
    }
}
