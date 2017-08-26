package com.shiva.reservation.data.local.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.shiva.reservation.model.Customer;
import com.shiva.reservation.model.TableMap;

import java.sql.SQLException;

/**
 * Created by shivananda.darura on 25/08/17.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "reservation.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Customer, Integer> customerDao = null;
    private Dao<TableMap, Integer> tableMapDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Customer.class);
            TableUtils.createTable(connectionSource, TableMap.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Customer.class, true);
            TableUtils.dropTable(connectionSource, TableMap.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /* Customer */
    public Dao<Customer, Integer> getCustomerDao() throws SQLException {
        if (customerDao == null) {
            customerDao = getDao(Customer.class);
        }

        return customerDao;
    }

    public Dao<TableMap, Integer> getTableMapDao() throws SQLException {
        if (tableMapDao == null) {
            tableMapDao = getDao(TableMap.class);
        }
        return tableMapDao;
    }

    @Override
    public void close() {
        customerDao = null;

        super.close();
    }
}