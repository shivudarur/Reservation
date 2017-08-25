package com.shiva.reservation.data.remote;

import android.support.annotation.NonNull;

import com.shiva.reservation.data.ResponseWrapper;
import com.shiva.reservation.data.remote.service.ReservationInfoService;
import com.shiva.reservation.model.Customer;
import com.shiva.reservation.util.Constants;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;

import static com.shiva.reservation.util.Constants.BASE_URL_DEPENDENCY;

/**
 * Created by shivananda.darura on 23/08/17.
 */

public class ApiRepository {

    private final ServiceGenerator serviceGenerator;
    private final String baseUrl;

    @Inject
    public ApiRepository(ServiceGenerator serviceGenerator,
                         @Named(BASE_URL_DEPENDENCY) String baseUrl) {
        this.serviceGenerator = serviceGenerator;
        this.baseUrl = baseUrl;
    }

    public Single<ResponseWrapper<List<Customer>>> getCustomers() {
        final ReservationInfoService infoService = serviceGenerator.createService(ReservationInfoService.class, baseUrl);
        final Single<List<Customer>> responseSingle = infoService.getCustomers();
        return processApiStream(responseSingle);
    }

    @NonNull
    private <T> Single<ResponseWrapper<T>> processApiStream(Single<T> flowableApiStream) {
        return flowableApiStream
            .flatMap(this::processResponse);
    }

    private <T> Single<ResponseWrapper<T>> processResponse(T response) {
        return Single.just(new ResponseWrapper<>(response == null ? Constants.ERROR_UNDEFINED :
            Constants.SUCCESS_RESPONSE, response));
    }
}
