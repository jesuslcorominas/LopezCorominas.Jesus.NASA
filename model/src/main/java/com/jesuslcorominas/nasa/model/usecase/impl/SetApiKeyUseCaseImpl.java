package com.jesuslcorominas.nasa.model.usecase.impl;

import com.jesuslcorominas.nasa.common.Result;
import com.jesuslcorominas.nasa.model.repository.PreferencesRepository;
import com.jesuslcorominas.nasa.model.usecase.SetApiKeyUseCase;

/**
 * @author Jesús López Corominas
 */
public class SetApiKeyUseCaseImpl implements SetApiKeyUseCase {

    private final PreferencesRepository preferencesRepository;

    public SetApiKeyUseCaseImpl(PreferencesRepository preferencesRepository) {
        this.preferencesRepository = preferencesRepository;
    }

    @Override
    public void setApiKey(String apiKey, final SetApiKeyCallback callback) {
        preferencesRepository.setApiKey(apiKey, new PreferencesRepository.SetApiKeyCallback() {
            @Override
            public void onResult(Result<Void> result) {
                callback.onResult(result);
            }
        });
    }
}
