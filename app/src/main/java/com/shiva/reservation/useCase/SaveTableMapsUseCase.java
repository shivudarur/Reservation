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

public class SaveTableMapsUseCase {

    private final DataRepository dataRepository;

    @Inject
    public SaveTableMapsUseCase(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public void saveTableMaps(@NonNull List<TableMap> tableMaps) {
        dataRepository.saveTableMaps(tableMaps);
    }

    public Single<ResponseWrapper<Boolean>> updateTableMap(TableMap tableMap) {
        return dataRepository.updateTableMap(tableMap);
    }
}
