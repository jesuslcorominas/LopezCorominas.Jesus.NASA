package com.jesuslcorominas.nasa.data.datasource;

import com.jesuslcorominas.nasa.common.GenericCallback;
import com.jesuslcorominas.nasa.common.VoidCallback;

/**
 * @author Jesús López Corominas
 */
public interface PreferencesDatasource extends Datasource {

    void setApiKey(String apiKey, SetApiKeyCallback callback);

    void getApiKey(GetApiKeyCallback callback);

    interface SetApiKeyCallback extends VoidCallback {

    }

    interface GetApiKeyCallback extends GenericCallback<String> {

    }
}
