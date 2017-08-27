package com.shiva.reservation;

import com.shiva.reservation.model.Customer;
import com.shiva.reservation.model.TableMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shivananda.darura on 18/08/17.
 */

public class TestHelper {
    public String getAnyString() {
        return "anyString";
    }

    public List<Customer> getCustomersList() {
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            customers.add(new Customer());
        }
        return customers;
    }

    public List<TableMap> getTableMapsList() {
        List<TableMap> customers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            customers.add(new TableMap());
        }
        return customers;
    }
}
