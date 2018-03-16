package com.jesuslcorominas.nasa.model.usecase;

import com.jesuslcorominas.nasa.common.VoidCallback;

/**
 * @author Jesús López Corominas
 */
public interface SetApiKeyUseCase extends UseCase {

    void setApiKey(String apiKey, SetApiKeyCallback callback);

    interface SetApiKeyCallback extends VoidCallback {

    }
}
