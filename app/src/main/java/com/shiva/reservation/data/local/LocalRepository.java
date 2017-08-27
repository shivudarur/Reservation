package com.shiva.reservation.data.local;

import android.app.Application;
import android.support.annotation.NonNull;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.shiva.reservation.data.ResponseWrapper;
import com.shiva.reservation.data.local.db.DatabaseHelper;
import com.shiva.reservation.model.Customer;
import com.shiva.reservation.model.TableMap;
import com.shiva.reservation.util.Constants;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by shivananda.darura on 23/08/17.
 */

public class LocalRepository {

    private final Application application;

    @Inject
    public LocalRepository(Application application) {
        this.application = application;
    }

    private <T> Single<ResponseWrapper<T>> processResponse(T response) {
        return Single.just(new ResponseWrapper<>(response == null ? Constants.ERROR_UNDEFINED :
            Constants.SUCCESS_RESPONSE, response));
    }

    private <T> Single<ResponseWrapper<T>> getWrappedDbSuccessResponse(T response) {
        return Single.just(new ResponseWrapper<>(Constants.SUCCESS_RESPONSE, response));
    }

    private <T> Single<ResponseWrapper<T>> getWrappedDbError(T response, Throwable throwable) {
        return Single.just(new ResponseWrapper<>(Constants.ERROR_DB, response, throwable));
    }

    @NonNull
    public Single<ResponseWrapper<List<Customer>>> getCustomers() {
        DatabaseHelper databaseHelper = OpenHelperManager.getHelper(application.getApplicationContext(), DatabaseHelper.class);
        final Dao<Customer, Integer> customerDao;
        try {
            customerDao = databaseHelper.getCustomerDao();
            final List<Customer> customers = customerDao.queryForAll();
            return processResponse(customers);
        } catch (SQLException e) {
            e.printStackTrace();
            return getWrappedDbError(null, e);
        } finally {
            OpenHelperManager.releaseHelper();
        }
    }

    public void saveCustomers(@NonNull List<Customer> customers) {
        DatabaseHelper databaseHelper = OpenHelperManager.getHelper(application.getApplicationContext(), DatabaseHelper.class);
        final Dao<Customer, Integer> customerDao;
        try {
            customerDao = databaseHelper.getCustomerDao();
            for (Customer customer : customers) {
                customerDao.createIfNotExists(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            OpenHelperManager.releaseHelper();
        }
    }

    @NonNull
    public Single<ResponseWrapper<List<TableMap>>> getTableMaps() {
        DatabaseHelper databaseHelper = OpenHelperManager.getHelper(application.getApplicationContext(), DatabaseHelper.class);
        final Dao<TableMap, Integer> tableMapDao;
        try {
            tableMapDao = databaseHelper.getTableMapDao();
            final List<TableMap> tableMaps = tableMapDao.queryForAll();
            return processResponse(tableMaps);
        } catch (SQLException e) {
            e.printStackTrace();
            return getWrappedDbError(null, e);
        } finally {
            OpenHelperManager.releaseHelper();
        }
    }

    public void saveTableMaps(@NonNull List<TableMap> tableMaps) {
        DatabaseHelper databaseHelper = OpenHelperManager.getHelper(application.getApplicationContext(), DatabaseHelper.class);
        final Dao<TableMap, Integer> tableMapDao;
        try {
            tableMapDao = databaseHelper.getTableMapDao();
            for (TableMap tableMap : tableMaps) {
                tableMapDao.createIfNotExists(tableMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            OpenHelperManager.releaseHelper();
        }
    }

    public Single<ResponseWrapper<Boolean>> updateTableMap(TableMap tableMap) {

        DatabaseHelper databaseHelper = OpenHelperManager.getHelper(application.getApplicationContext(), DatabaseHelper.class);
        final Dao<TableMap, Integer> tableMapDao;
        try {
            tableMapDao = databaseHelper.getTableMapDao();
            final int numberOfRowsUpdated = tableMapDao.update(tableMap);
            return numberOfRowsUpdated == 1 ? getWrappedDbSuccessResponse(true) : getWrappedDbError(false, null);
        } catch (SQLException e) {
            e.printStackTrace();
            return getWrappedDbError(null, e);
        } finally {
            OpenHelperManager.releaseHelper();
        }

    }

    public void resetTableMaps() {
        DatabaseHelper databaseHelper = OpenHelperManager.getHelper(application.getApplicationContext(), DatabaseHelper.class);
        final Dao<TableMap, Integer> tableMapDao;
        try {
            tableMapDao = databaseHelper.getTableMapDao();
            final UpdateBuilder<TableMap, Integer> updateBuilder = tableMapDao.updateBuilder();
            updateBuilder.where().eq("isTableReserved", true);
            updateBuilder.updateColumnValue("isTableReserved", false);
            updateBuilder.update();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            OpenHelperManager.releaseHelper();
        }
    }
}
