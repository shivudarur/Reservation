package com.shiva.reservation.ui.component.splash;

import com.shiva.reservation.App;
import com.shiva.reservation.R;
import com.shiva.reservation.ui.base.BaseActivity;
import com.shiva.reservation.ui.component.home.HomeActivity;

import javax.inject.Inject;

/**
 * Created by shivananda.darura on 23/08/17.
 */

public class SplashActivity extends BaseActivity implements SplashView {

    @Inject
    SplashPresenter splashPresenter;

    @Override
    public void openHomeScreen() {
        HomeActivity.open(getApplicationContext());
    }

    //Component contracts.
    @Override
    protected void initializeDagger() {
        App app = (App) getApplication();
        app.getMainScreenComponent().inject(this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = splashPresenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }
}
