package com.shiva.reservation.ui.component.home;

import android.os.Bundle;

import com.shiva.reservation.TestHelper;
import com.shiva.reservation.data.ResponseWrapper;
import com.shiva.reservation.model.Customer;
import com.shiva.reservation.ui.base.listeners.RecyclerItemListener;
import com.shiva.reservation.useCase.GetCustomersUseCase;
import com.shiva.reservation.useCase.SaveCustomersUseCase;
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

public class HomePresenterTest {
    @Mock
    HomeView mockView;
    @Mock
    Bundle bundle;
    @Mock
    GetCustomersUseCase getCustomersUseCase;
    @Mock
    SaveCustomersUseCase saveCustomersUseCase;
    @Mock
    RxTransformer rxTransformer;
    @Mock
    SingleTransformer singleTransformer;
    @Mock
    SingleSource singleSource;
    @Mock
    ErrorUtils errorUtils;
    @Mock
    ResponseWrapper<List<Customer>> getCustomersResponse;

    private HomePresenter homePresenter;
    private TestHelper testHelper;

    @Before
    public void setUp() {
        initMocks(this);
        testHelper = new TestHelper();
        homePresenter = new HomePresenter(getCustomersUseCase,
            errorUtils, rxTransformer, saveCustomersUseCase);
        homePresenter.setView(mockView);
        when(singleTransformer.apply(any())).thenReturn(singleSource);
        when(rxTransformer.applyComputationScheduler()).thenReturn(singleTransformer);
    }

    @Test
    public void testGetCustomersFromDbFail() throws Exception {

        //Given
        when(getCustomersUseCase.getCustomers(false)).thenReturn(Single.just(getCustomersResponse));

        //When
        homePresenter.initialize(bundle);
        //then
        verify(mockView, never()).showCustomers(ArgumentMatchers.anyList(), any(RecyclerItemListener.class));
    }

    @Test
    public void testGetCustomersFromDbSuccess() throws Exception {
        final List<Customer> customerList = testHelper.getCustomersList();
        final Single<ResponseWrapper<List<Customer>>> responseWrapperSingle = Single.just(new
            ResponseWrapper<>(Constants.SUCCESS_RESPONSE, customerList));

        when(getCustomersUseCase.getCustomers(anyBoolean())).thenReturn(responseWrapperSingle);

        final TestObserver<ResponseWrapper<List<Customer>>> wrapperTestObserver = getCustomersUseCase.getCustomers(false).test();

        wrapperTestObserver.awaitTerminalEvent();
        wrapperTestObserver
            .assertNoErrors()
            .assertValue(l -> l.getResponse().size() == customerList.size());

        homePresenter.initialize(bundle);
    }

    @Test
    public void testGetCustomersFromBackendFail() throws Exception {

        //Given
        when(getCustomersUseCase.getCustomers(anyBoolean())).thenReturn(Single.just(getCustomersResponse));

        //When
        homePresenter.initialize(bundle);
        //then
        verify(mockView, never()).showCustomers(ArgumentMatchers.anyList(), any(RecyclerItemListener.class));
    }

    @Test
    public void testGetCustomersFromBackendSuccess() throws Exception {
        final List<Customer> customerList = testHelper.getCustomersList();
        final Single<ResponseWrapper<List<Customer>>> responseWrapperSingle = Single.just(new
            ResponseWrapper<>(Constants.SUCCESS_RESPONSE, customerList));

        when(getCustomersUseCase.getCustomers(anyBoolean())).thenReturn(responseWrapperSingle);

        final TestObserver<ResponseWrapper<List<Customer>>> wrapperTestObserver =
            getCustomersUseCase.getCustomers(true).test();

        wrapperTestObserver.awaitTerminalEvent();
        wrapperTestObserver
            .assertNoErrors()
            .assertValue(responseWrapper -> responseWrapper.getResponse().size() == customerList.size());

        homePresenter.initialize(bundle);
    }

}
