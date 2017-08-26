package com.shiva.reservation.ui.component.tableReservation;

import android.os.Bundle;

import com.shiva.reservation.R;
import com.shiva.reservation.model.TableMap;
import com.shiva.reservation.ui.base.RxPresenter;
import com.shiva.reservation.useCase.GetTableMapsUseCase;
import com.shiva.reservation.useCase.SaveTableMapsUseCase;
import com.shiva.reservation.useCase.base.RxTransformer;
import com.shiva.reservation.util.ErrorUtils;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by shivananda.darura on 02/02/17.
 */

public class TableReservationPresenter extends RxPresenter<TableReservationView> {

    private final GetTableMapsUseCase getTableMapsUseCase;
    private final ErrorUtils errorUtils;
    private final RxTransformer rxTransformer;
    private final SaveTableMapsUseCase saveTableMapsUseCase;
    private List<TableMap> tableMapList;

    @Inject
    public TableReservationPresenter(GetTableMapsUseCase getTableMapsUseCase, ErrorUtils errorUtils,
                                     RxTransformer rxTransformer, SaveTableMapsUseCase saveTableMapsUseCase) {
        this.getTableMapsUseCase = getTableMapsUseCase;
        this.errorUtils = errorUtils;
        this.rxTransformer = rxTransformer;
        this.saveTableMapsUseCase = saveTableMapsUseCase;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        view.setTitle(R.string.reserve_table);
        getTableMaps();
    }

    private void getTableMaps() {
        view.showProgress("");
        compositeDisposable.add(getTableMapsUseCase.getTableMaps(false)
            .compose(rxTransformer.applyComputationScheduler())
            .subscribe((listResponseWrapper) -> {
                    final List<TableMap> tableMapList = listResponseWrapper.getResponse();
                    if (!errorUtils.isEmptyCollection(tableMapList)) {
                        TableReservationPresenter.this.tableMapList = tableMapList;
                        view.hideProgress();
                        view.showTableMaps(tableMapList, this::reserveTable);
                    } else {
                        //There are no data in the local database.
                        //Try to get from remote
                        getTableMapsFromBackend();
                    }
                },
                throwable -> processDataLoadingError()));
    }

    private void getTableMapsFromBackend() {
        compositeDisposable.add(getTableMapsUseCase.getTableMaps(true)
            .compose(rxTransformer.applyIoScheduler())
            .subscribe((listResponseWrapper) -> {
                    tableMapList = listResponseWrapper.getResponse();
                    if (!errorUtils.isEmptyCollection(tableMapList)) {
                        view.hideProgress();
                        //Update table maps with ids.
                        for (int i = 0; i < tableMapList.size(); i++) {
                            tableMapList.get(i).setId(i);
                        }
                        view.showTableMaps(tableMapList, this::reserveTable);
                        //Save TableMap list in local Db
                        saveTableMapsUseCase.saveTableMaps(tableMapList);
                    } else {
                        processDataLoadingError();
                    }
                },
                throwable -> processDataLoadingError()));
    }

    private void reserveTable(int position) {
        view.showProgress("");
        final TableMap tableMap = tableMapList.get(position);
        tableMap.setTableReserved(true);
        compositeDisposable.add(saveTableMapsUseCase.updateTableMap(tableMap)
            .compose(rxTransformer.applyIoScheduler())
            .subscribe((listResponseWrapper) -> {
                    final Boolean isDataUpdated = listResponseWrapper.getResponse();
                    if (isDataUpdated != null && isDataUpdated) {
                        view.hideProgress();
                        view.notifyItemChanged(position);
                    } else {
                        processDataLoadingError();
                    }
                },
                throwable -> processDataLoadingError()));
    }

    private void processDataLoadingError() {
        view.hideProgress();
        view.showError(R.string.data_loading_error);
    }
}
