package com.shiva.reservation.data;

import com.shiva.reservation.data.local.LocalRepository;
import com.shiva.reservation.data.remote.ApiRepository;
import com.shiva.reservation.model.Customer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by shivananda.darura on 23/08/17.
 */

public class DataRepository {

    private final ApiRepository apiRepository;
    private final LocalRepository localRepository;

    @Inject
    public DataRepository(ApiRepository apiRepository, LocalRepository localRepository) {
        this.apiRepository = apiRepository;
        this.localRepository = localRepository;
    }

    public Single<ResponseWrapper<List<Customer>>> getCustomers() {
        return apiRepository.getCustomers();
    }
}
