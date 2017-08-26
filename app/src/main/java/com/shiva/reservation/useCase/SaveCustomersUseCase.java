package com.shiva.reservation.useCase;

import android.support.annotation.NonNull;

import com.shiva.reservation.data.DataRepository;
import com.shiva.reservation.model.Customer;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by shivananda.darura on 24/08/17.
 */

public class SaveCustomersUseCase {

    private final DataRepository dataRepository;

    @Inject
    public SaveCustomersUseCase(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public void saveCustomers(@NonNull List<Customer> customers) {
        dataRepository.saveCustomers(customers);
    }
}
