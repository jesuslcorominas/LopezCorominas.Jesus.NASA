package com.jesuslcorominas.nasa.data.di.module;

import com.google.gson.Gson;
import com.jesuslcorominas.nasa.data.net.client.PhotoClient;
import com.jesuslcorominas.nasa.data.net.client.impl.PhotoClientImpl;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Jesús López Corominas
 */
@Module(includes = {GsonModule.class})
public class NetModule {

    private static final int READ_TIMEOUT = 60;
    private static final int CONNECT_TIMEOUT = 5;

    private static NetModule instance;

    private String endPoint;

    private NetModule(String endPoint) {
        this.endPoint = endPoint;
    }

    public static NetModule getInstance(String endPoint) {
        if (instance == null) {
            instance = new NetModule(endPoint);
        }

        return instance;
    }

    @Singleton
    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return interceptor;
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder().
                readTimeout(READ_TIMEOUT, TimeUnit.SECONDS).
                connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS).
                addInterceptor(loggingInterceptor).
                build();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder().
                baseUrl(endPoint).
                addConverterFactory(GsonConverterFactory.create(gson)).
                client(okHttpClient).
                build();
    }

    @Provides
    public PhotoClient.Api providePhotoClientApi(Retrofit retrofit) {
        return retrofit.create(PhotoClient.Api.class);
    }

    @Provides
    public PhotoClient providePhotoClient(PhotoClient.Api api) {
        return new PhotoClientImpl(api);
    }
}
