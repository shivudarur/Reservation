package com.shiva.reservation.ui.component.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shiva.reservation.R;
import com.shiva.reservation.model.Customer;
import com.shiva.reservation.ui.base.listeners.RecyclerItemListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.view.LayoutInflater.from;

/**
 * Created by shivananda.darura on 24/08/17.
 */

public class CustomersAdapter extends RecyclerView.Adapter<CustomersAdapter.CustomerViewHolder> {

    private List<Customer> customers;
    private RecyclerItemListener recyclerItemListener;

    public CustomersAdapter(List<Customer> customers, RecyclerItemListener recyclerItemListener) {
        this.customers = customers;
        this.recyclerItemListener = recyclerItemListener;
    }

    @Override
    public CustomerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = from(parent.getContext()).inflate(R.layout.list_item_customer, parent, false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomerViewHolder holder, int position) {
        holder.bind(position, recyclerItemListener);
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    class CustomerViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.txt_customer_name)
        TextView txtCustomerName;

        CustomerViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(final int position, RecyclerItemListener recyclerItemListener) {
            Customer customer = customers.get(position);
            txtCustomerName.setText(customer.getCustomerFirstName() + " " + customer.getCustomerLastName());
            txtCustomerName.setOnClickListener(v -> {
                notifyDataSetChanged();
                recyclerItemListener.onItemSelected(position);
            });
        }
    }
}

