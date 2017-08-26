package com.shiva.reservation.ui.component.tableReservation;

import com.shiva.reservation.model.TableMap;
import com.shiva.reservation.ui.base.ErrorView;
import com.shiva.reservation.ui.base.Presenter;
import com.shiva.reservation.ui.base.ProgressView;
import com.shiva.reservation.ui.base.listeners.RecyclerItemListener;

import java.util.List;

/**
 * Created by shivananda.darura on 02/02/17.
 */

public interface TableReservationView extends Presenter.View, ProgressView, ErrorView {
    void showTableMaps(List<TableMap> tableMapList, RecyclerItemListener recyclerItemListener);

    void notifyItemChanged(int position);
}
