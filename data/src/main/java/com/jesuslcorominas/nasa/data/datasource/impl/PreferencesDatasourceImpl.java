package com.jesuslcorominas.nasa.data.datasource.impl;

import com.jesuslcorominas.nasa.common.Error;
import com.jesuslcorominas.nasa.common.Result;
import com.jesuslcorominas.nasa.data.datasource.PreferencesDatasource;
import com.jesuslcorominas.nasa.model.preferences.PreferencesHelper;

/**
 * @author Jesús López Corominas
 */
public class PreferencesDatasourceImpl implements PreferencesDatasource {

    private final PreferencesHelper preferencesHelper;

    public PreferencesDatasourceImpl(PreferencesHelper preferencesHelper) {
        this.preferencesHelper = preferencesHelper;
    }

    @Override
    public void setApiKey(String apiKey, SetApiKeyCallback callback) {
        try {
            preferencesHelper.setApiKey(apiKey);

            callback.onResult(new Result<Void>());
        } catch (Exception e) {
            callback.onResult(new Result<Void>(Error.PREFERENCES_ERROR, "Error al guardar en preferencias el apiKey: " + e.getMessage()));
        }
    }

    @Override
    public void getApiKey(GetApiKeyCallback callback) {
        try {
            callback.onResult(new Result<>(preferencesHelper.getApiKey()));
        } catch (Exception e) {
            callback.onResult(new Result<String>(Error.PREFERENCES_ERROR, "Error al obtener de preferencias el apiKey: " + e.getMessage()));
        }
    }
}
