package com.shiva.reservation;

import com.shiva.reservation.model.Customer;
import com.shiva.reservation.model.TableMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shivananda.darura on 18/08/17.
 */

public class TestDataGenerator {
    public String getAnyString() {
        return "anyString";
    }

    public List<Customer> getCustomersList() {
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final Customer customer = new Customer();
            customer.setCustomerFirstName("First Name" + i);
            customer.setCustomerLastName("Last Name" + i);
            customers.add(customer);
        }
        return customers;
    }

    public List<TableMap> getTableMapsList() {
        List<TableMap> customers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final TableMap tableMap = new TableMap();
            tableMap.setTableReserved(1 % 2 == 0);
            customers.add(tableMap);
        }
        return customers;
    }

    public List<Customer> getEmptyCustomersList() {
        return new ArrayList<>();
    }

    public List<TableMap> getEmptyTableMapsList() {
        return new ArrayList<>();
    }
}
