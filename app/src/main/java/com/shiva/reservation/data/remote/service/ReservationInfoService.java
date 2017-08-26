package com.shiva.reservation.data.remote.service;

import com.shiva.reservation.model.Customer;
import com.shiva.reservation.model.TableMap;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by shivananda.darura on 25/08/17.
 */

public interface ReservationInfoService {

    @GET("quandoo-assessment/customer-list.json")
    Single<List<Customer>> getCustomers();

    @GET("quandoo-assessment/table-map.json")
    Single<List<TableMap>> getTableMap();
}
