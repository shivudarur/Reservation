package com.shiva.reservation.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shivananda.darura on 24/08/17.
 */

public class Customer {

    @SerializedName("id")
    private int id;
    @SerializedName("customerFirstName")
    private String customerFirstName;
    @SerializedName("customerLastName")
    private String customerLastName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }
}
