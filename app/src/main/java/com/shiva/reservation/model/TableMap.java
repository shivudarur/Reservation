package com.shiva.reservation.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by shivananda.darura on 24/08/17.
 */

@DatabaseTable
public class TableMap {

    @DatabaseField(id = true)
    private int id;
    @DatabaseField
    private Boolean isTableReserved;

    public TableMap() {
    }

    public TableMap(Boolean isTableReserved) {
        this.isTableReserved = isTableReserved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getTableReserved() {
        return isTableReserved;
    }

    public void setTableReserved(Boolean tableReserved) {
        isTableReserved = tableReserved;
    }
}
