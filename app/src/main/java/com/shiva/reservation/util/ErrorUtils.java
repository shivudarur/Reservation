package com.shiva.reservation.util;

import com.shiva.reservation.data.ResponseWrapper;

import java.util.Collection;

import javax.inject.Inject;

/**
 * Created by shivananda.darura on 24/08/17.
 */

public class ErrorUtils {

    @Inject
    public ErrorUtils() {
    }

    public boolean isSuccessResponse(ResponseWrapper responseWrapper) {
        return responseWrapper != null &&
            (responseWrapper.getCode() / Constants.VALUE_100) == Constants.GROUP_200;
    }

    public boolean isEmptyCollection(Collection collection) {
        return collection == null || collection.size() <= 0;
    }

}
