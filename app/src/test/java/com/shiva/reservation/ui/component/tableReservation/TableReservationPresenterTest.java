package com.shiva.reservation.ui.component.tableReservation;

import android.os.Bundle;

import com.shiva.reservation.TestHelper;
import com.shiva.reservation.data.ResponseWrapper;
import com.shiva.reservation.model.TableMap;
import com.shiva.reservation.ui.base.listeners.RecyclerItemListener;
import com.shiva.reservation.useCase.GetTableMapsUseCase;
import com.shiva.reservation.useCase.SaveTableMapsUseCase;
import com.shiva.reservation.useCase.base.RxTransformer;
import com.shiva.reservation.util.Constants;
import com.shiva.reservation.util.ErrorUtils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.observers.TestObserver;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by shivananda.darura on 21/08/17.
 */

public class TableReservationPresenterTest {
    @Mock
    TableReservationView mockView;
    @Mock
    Bundle bundle;
    @Mock
    GetTableMapsUseCase getTableMapsUseCase;
    @Mock
    SaveTableMapsUseCase saveTableMapsUseCase;
    @Mock
    RxTransformer rxTransformer;
    @Mock
    SingleTransformer singleTransformer;
    @Mock
    SingleSource singleSource;
    @Mock
    ErrorUtils errorUtils;
    @Mock
    ResponseWrapper<List<TableMap>> getTableMapsResponse;

    private TableReservationPresenter tableReservationPresenter;
    private TestHelper testHelper;

    @Before
    public void setUp() {
        initMocks(this);
        testHelper = new TestHelper();
        tableReservationPresenter = new TableReservationPresenter(getTableMapsUseCase,
            errorUtils, rxTransformer, saveTableMapsUseCase);
        tableReservationPresenter.setView(mockView);
        when(singleTransformer.apply(any())).thenReturn(singleSource);
        when(rxTransformer.applyComputationScheduler()).thenReturn(singleTransformer);
    }

    @Test
    public void testGetTableMapsFromDbFail() throws Exception {

        //Given
        when(getTableMapsUseCase.getTableMaps(false)).thenReturn(Single.just(getTableMapsResponse));

        //When
        tableReservationPresenter.initialize(bundle);
        //then
        verify(mockView, never()).showTableMaps(ArgumentMatchers.anyList(), any(RecyclerItemListener.class));
    }

    @Test
    public void testGetTableMapsFromDbSuccess() throws Exception {
        final List<TableMap> tableMapList = testHelper.getTableMapsList();
        final Single<ResponseWrapper<List<TableMap>>> responseWrapperSingle = Single.just(new
            ResponseWrapper<>(Constants.SUCCESS_RESPONSE, tableMapList));

        when(getTableMapsUseCase.getTableMaps(anyBoolean())).thenReturn(responseWrapperSingle);

        final TestObserver<ResponseWrapper<List<TableMap>>> wrapperTestObserver = getTableMapsUseCase.getTableMaps(false).test();

        wrapperTestObserver.awaitTerminalEvent();
        wrapperTestObserver
            .assertNoErrors()
            .assertValue(responseWrapper -> responseWrapper.getResponse().size() == tableMapList.size());

        tableReservationPresenter.initialize(bundle);
    }

    @Test
    public void testGetTableMapsFromBackendFail() throws Exception {

        //Given
        when(getTableMapsUseCase.getTableMaps(anyBoolean())).thenReturn(Single.just(getTableMapsResponse));

        //When
        tableReservationPresenter.initialize(bundle);
        //then
        verify(mockView, never()).showTableMaps(ArgumentMatchers.anyList(), any(RecyclerItemListener.class));
    }

    @Test
    public void testGetTableMapsFromBackendSuccess() throws Exception {
        final List<TableMap> tableMapList = testHelper.getTableMapsList();
        final Single<ResponseWrapper<List<TableMap>>> responseWrapperSingle = Single.just(new
            ResponseWrapper<>(Constants.SUCCESS_RESPONSE, tableMapList));

        when(getTableMapsUseCase.getTableMaps(anyBoolean())).thenReturn(responseWrapperSingle);

        final TestObserver<ResponseWrapper<List<TableMap>>> wrapperTestObserver = 
            getTableMapsUseCase.getTableMaps(true).test();

        wrapperTestObserver.awaitTerminalEvent();
        wrapperTestObserver
            .assertNoErrors()
            .assertValue(responseWrapper -> responseWrapper.getResponse().size() == tableMapList.size());

        tableReservationPresenter.initialize(bundle);
    }

}
