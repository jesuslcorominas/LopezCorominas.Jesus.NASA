package com.jesuslcorominas.nasa.data.net.interceptor;

import com.jesuslcorominas.nasa.data.net.util.NetKeys;
import com.jesuslcorominas.nasa.model.preferences.PreferencesHelper;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Jesús López Corominas
 */
public class ApiKeyInterceptor implements Interceptor {

    /**
     * Helper para acceder a las preferencias de la aplicacion
     */
    private final PreferencesHelper preferencesHelper;

    public ApiKeyInterceptor(PreferencesHelper preferencesHelper) {
        this.preferencesHelper = preferencesHelper;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter(NetKeys.PARAM_API_KEY, preferencesHelper.getApiKey())
                .build();

        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
