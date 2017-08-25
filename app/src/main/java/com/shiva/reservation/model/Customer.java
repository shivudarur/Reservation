package com.shiva.reservation.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

/**
 * Created by shivananda.darura on 24/08/17.
 */

public class Customer {

    @SerializedName("id")
    @DatabaseField(id = true)
    private int id;
    @SerializedName("customerFirstName")
    @DatabaseField
    private String customerFirstName;
    @SerializedName("customerLastName")
    @DatabaseField
    private String customerLastName;

    public Customer() {
    }

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
