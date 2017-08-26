package com.shiva.reservation.ui.component.tableReservation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shiva.reservation.R;
import com.shiva.reservation.model.TableMap;
import com.shiva.reservation.ui.base.listeners.RecyclerItemListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.view.LayoutInflater.from;

/**
 * Created by shivananda.darura on 24/08/17.
 */

public class TableMapsAdapter extends RecyclerView.Adapter<TableMapsAdapter.TableMapViewHolder> {

    private List<TableMap> tableMaps;
    private RecyclerItemListener recyclerItemListener;

    public TableMapsAdapter(List<TableMap> tableMaps, RecyclerItemListener recyclerItemListener) {
        this.tableMaps = tableMaps;
        this.recyclerItemListener = recyclerItemListener;
    }

    @Override
    public TableMapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = from(parent.getContext()).inflate(R.layout.list_item_table_map, parent, false);
        return new TableMapViewHolder(view, parent.getMeasuredWidth(), parent.getMeasuredHeight());
    }

    @Override
    public void onBindViewHolder(TableMapViewHolder holder, int position) {
        holder.bind(position, recyclerItemListener);
    }

    @Override
    public int getItemCount() {
        return tableMaps.size();
    }

    class TableMapViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.txt_status)
        TextView txtTableReservationStatus;

        TableMapViewHolder(View view, int parentWidth, int parentHeight) {
            super(view);
            ButterKnife.bind(this, view);
            txtTableReservationStatus.setMinWidth(parentWidth / 2);
            txtTableReservationStatus.setMinHeight(parentHeight / 2 - 50);
        }

        public void bind(final int position, RecyclerItemListener recyclerItemListener) {
            TableMap tableMap = tableMaps.get(position);
            final boolean isTableReserved = tableMap.getTableReserved();
            final Context context = txtTableReservationStatus.getContext();
            txtTableReservationStatus.setText(isTableReserved ? context.getString(R.string.reserved) :
                context.getString(R.string.tap_to_reserve));
            txtTableReservationStatus.setBackgroundResource(isTableReserved ?
                R.color.grey_disabled : R.color.blue_saturated);
            txtTableReservationStatus.setEnabled(!isTableReserved);
            txtTableReservationStatus.setOnClickListener(v -> recyclerItemListener.onItemSelected(position));
        }
    }
}

