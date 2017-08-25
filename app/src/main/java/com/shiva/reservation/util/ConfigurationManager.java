package com.shiva.reservation.util;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Patterns;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.shiva.reservation.BuildConfig.BASE_URL;

/**
 * Created by shivananda.darura on 23/08/17.
 */

@Singleton
public class ConfigurationManager {

    private final SharedPreferences sharedPreferences;
    private final String BASE_URL_KEY = "BaseURL";

    @Inject
    public ConfigurationManager(Application application) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application);
    }

    @NonNull
    public String getBaseURL() {
        return sharedPreferences.getString(BASE_URL_KEY, BASE_URL);
    }

    public void setBaseURL(@NonNull String baseUrl) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (Patterns.WEB_URL.matcher(baseUrl).matches()) {
            editor.putString(BASE_URL_KEY, baseUrl);
        }
        editor.apply();
    }
}
