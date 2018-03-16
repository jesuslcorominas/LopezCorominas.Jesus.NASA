package com.jesuslcorominas.nasa.data.repository.impl;

import com.jesuslcorominas.nasa.common.Result;
import com.jesuslcorominas.nasa.data.datasource.PreferencesDatasource;
import com.jesuslcorominas.nasa.model.repository.PreferencesRepository;

/**
 * @author Jesús López Corominas
 */
public class PreferencesRepositoryImpl implements PreferencesRepository {

    private final PreferencesDatasource preferencesDatasource;

    public PreferencesRepositoryImpl(PreferencesDatasource preferencesDatasource) {
        this.preferencesDatasource = preferencesDatasource;
    }

    @Override
    public void setApiKey(String apiKey, final SetApiKeyCallback callback) {
        preferencesDatasource.setApiKey(apiKey, new PreferencesDatasource.SetApiKeyCallback() {
            @Override
            public void onResult(Result<Void> result) {
                callback.onResult(result);
            }
        });
    }

    @Override
    public void getApiKey(final GetApiKeyCallback callback) {
        preferencesDatasource.getApiKey(new PreferencesDatasource.GetApiKeyCallback() {
            @Override
            public void onResult(Result<String> result) {
                callback.onResult(result);
            }
        });

    }
}
