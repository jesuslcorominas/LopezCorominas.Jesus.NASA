package com.jesuslcorominas.nasa.model.repository;

import com.jesuslcorominas.nasa.common.GenericCallback;
import com.jesuslcorominas.nasa.common.VoidCallback;

/**
 * @author Jesús López Corominas
 */
public interface PreferencesRepository {

    void setApiKey(String apiKey, SetApiKeyCallback callback);

    void getApiKey(GetApiKeyCallback callback);

    interface SetApiKeyCallback extends VoidCallback {

    }

    interface GetApiKeyCallback extends GenericCallback<String> {

    }
}
