package com.shiva.reservation.ui.component.tableReservation;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.shiva.reservation.App;
import com.shiva.reservation.R;
import com.shiva.reservation.model.TableMap;
import com.shiva.reservation.ui.base.BaseActivity;
import com.shiva.reservation.ui.base.listeners.RecyclerItemListener;
import com.shiva.reservation.util.ErrorUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by shivananda.darura on 23/08/17.
 */

public class TableReservationActivity extends BaseActivity implements TableReservationView {

    @Inject
    TableReservationPresenter tableReservationPresenter;
    @Inject
    ErrorUtils errorUtils;

    @Bind(R.id.cl_parent)
    CoordinatorLayout clParent;
    @Bind(R.id.rv_table_maps)
    RecyclerView rvTableMaps;
    @Bind(R.id.pb_loader)
    ProgressBar pbLoader;

    private TableMapsAdapter tableMapsAdapter;

    public static void open(Context context) {
        Intent intent = new Intent(context, TableReservationActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void showTableMaps(List<TableMap> tableMapList, RecyclerItemListener recyclerItemListener) {
        if (errorUtils.isEmptyCollection(tableMapList)) {
            showError(R.string.data_loading_error);
            return;
        }
        rvTableMaps.setVisibility(View.VISIBLE);
        tableMapsAdapter = new TableMapsAdapter(tableMapList, recyclerItemListener);
        rvTableMaps.setLayoutManager(new GridLayoutManager(this, 2));
        rvTableMaps.setAdapter(tableMapsAdapter);
    }

    @Override
    public void notifyItemChanged(int position) {
        tableMapsAdapter.notifyItemChanged(position);
    }

    //Component contracts.
    @Override
    protected void initializeDagger() {
        App app = (App) getApplication();
        app.getMainComponent().inject(this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = tableReservationPresenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_table_reservation;
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
