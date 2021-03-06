package com.shiva.reservation.ui.component.home;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.shiva.reservation.App;
import com.shiva.reservation.R;
import com.shiva.reservation.model.Customer;
import com.shiva.reservation.ui.base.BaseActivity;
import com.shiva.reservation.ui.base.listeners.RecyclerItemListener;
import com.shiva.reservation.ui.component.tableReservation.TableReservationActivity;
import com.shiva.reservation.util.ErrorUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by shivananda.darura on 23/08/17.
 */

public class HomeActivity extends BaseActivity implements HomeView {

    @Inject
    HomePresenter homePresenter;
    @Inject
    ErrorUtils errorUtils;

    @Bind(R.id.cl_parent)
    CoordinatorLayout clParent;
    @Bind(R.id.rv_customers)
    RecyclerView rvCustomers;
    @Bind(R.id.pb_loader)
    ProgressBar pbLoader;

    @Override
    public void openTableSelectionScreen() {
        TableReservationActivity.open(getApplicationContext());
    }

    @Override
    public void showCustomers(List<Customer> customerList, RecyclerItemListener recyclerItemListener) {
        if (errorUtils.isEmptyCollection(customerList)) {
            showError(R.string.data_loading_error);
            return;
        }
        rvCustomers.setVisibility(View.VISIBLE);
        CustomersAdapter customersAdapter = new CustomersAdapter(customerList, recyclerItemListener);
        rvCustomers.setLayoutManager(new LinearLayoutManager(this));
        rvCustomers.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvCustomers.setAdapter(customersAdapter);
    }

    //Component contracts.
    @Override
    protected void initializeDagger() {
        App app = (App) getApplication();
        app.getMainComponent().inject(this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = homePresenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void showProgress(String message) {
        pbLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLoader.setVisibility(View.GONE);
    }

    @Override
    public void showError(int errorMsgResourceId) {
        Snackbar.make(clParent, getString(errorMsgResourceId), Snackbar.LENGTH_LONG).show();
    }
}
