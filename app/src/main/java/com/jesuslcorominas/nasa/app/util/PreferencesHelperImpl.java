package com.jesuslcorominas.nasa.app.util;

import com.jesuslcorominas.nasa.model.preferences.PreferencesHelper;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.sharedpreferences.Pref;

/**
 * @author Jesús López Corominas
 */
@EBean
public class PreferencesHelperImpl implements PreferencesHelper {

    @Pref
    Preferences_ preferences;

    @Override
    public String getApiKey() {
        return preferences.apiKey().get();
    }

    @Override
    public void setApiKey(String apiKey) {
        preferences.apiKey().put(apiKey);
    }
}
