package com.shiva.reservation.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shiva.reservation.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shivananda.darura on 23/08/17.
 */

public abstract class BaseActivity extends AppCompatActivity implements Presenter.View, ActionBarView {

    protected Presenter presenter;

    @Nullable
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    protected abstract void initializeDagger();

    protected abstract void initializePresenter();

    public abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initializeButterKnife();
        initializeDagger();
        initializePresenter();
        initializeToolbar();

        if (presenter != null) {
            presenter.initialize(getIntent().getExtras());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    private void initializeButterKnife() {
        ButterKnife.bind(this);
    }

    protected void initializeToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("");
        }
    }

    @Override
    public void setTitle(String title) {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            TextView titleTextView = ButterKnife.findById(this, R.id.txt_toolbar_title);
            if (titleTextView != null) {
                titleTextView.setText(title);
            }
        }
    }
}
