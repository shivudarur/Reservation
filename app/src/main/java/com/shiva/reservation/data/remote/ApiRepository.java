package com.shiva.reservation.data.remote;

import javax.inject.Inject;
import javax.inject.Named;

import static com.shiva.reservation.util.Constants.BASE_URL_DEPENDENCY;

/**
 * Created by shivananda.darura on 23/08/17.
 */

public class ApiRepository {

    @Inject
    public ApiRepository(ServiceGenerator serviceGenerator,
                         @Named(BASE_URL_DEPENDENCY) String baseUrl){

    }
}
