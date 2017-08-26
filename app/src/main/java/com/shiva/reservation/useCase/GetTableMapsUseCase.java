package com.shiva.reservation.useCase;

import android.support.annotation.NonNull;

import com.shiva.reservation.data.DataRepository;
import com.shiva.reservation.data.ResponseWrapper;
import com.shiva.reservation.model.TableMap;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by shivananda.darura on 24/08/17.
 */

public class GetTableMapsUseCase {

    private final DataRepository dataRepository;

    @Inject
    public GetTableMapsUseCase(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @NonNull
    public Single<ResponseWrapper<List<TableMap>>> getTableMaps(boolean getFromBackend) {
        return dataRepository.getTableMaps(getFromBackend);
    }
}
