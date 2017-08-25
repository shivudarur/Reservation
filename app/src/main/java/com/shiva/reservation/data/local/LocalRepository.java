package com.shiva.reservation.data.local;

import android.app.Application;
import android.support.annotation.NonNull;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.shiva.reservation.data.ResponseWrapper;
import com.shiva.reservation.data.local.db.DatabaseHelper;
import com.shiva.reservation.model.Customer;
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
}
