package com.jesuslcorominas.nasa.data.di.module;

import com.google.gson.Gson;
import com.jesuslcorominas.nasa.data.net.client.PhotoClient;
import com.jesuslcorominas.nasa.data.net.client.impl.PhotoClientImpl;
import com.jesuslcorominas.nasa.data.net.interceptor.ApiKeyInterceptor;
import com.jesuslcorominas.nasa.model.preferences.PreferencesHelper;

import org.modelmapper.ModelMapper;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Modulo de Dagger 2 que provee de los objetos necesarios para realizar las peticiones remotas.
 * Configurado como un singleton para evitar mas de una instanciacion. Necesita para su construccion
 * la ruta del servidor donde se ubican los servicios Rest
 *
 * @author Jesús López Corominas
 * @see <a href="http://square.github.io/retrofit/">Retrofit</a>
 */
@Module(includes = {GsonModule.class, MapperModule.class})
public class NetModule {

    /**
     * Tiempo maximo, en segundos, que esperaran las peticiones remotas para completarse
     */
    private static final int READ_TIMEOUT = 60;

    /**
     * Tiempo maximo, en segundos, que esperara una peticion remota para conectarse al servidor
     */
    private static final int CONNECT_TIMEOUT = 5;

    /**
     * Instancia unica de la clase
     */
    private static NetModule instance;

    /**
     * URL del servidor donde se alojan los servicios Rest
     */
    private String endPoint;

    /**
     * Constructor unico privado y parametrizado para implementar el patron singleton. Necesita
     * de la URL del servidor donde se alojan los servicios Rest
     *
     * @param endPoint La URL del servidor donde se alojan los servicios Rest
     */
    private NetModule(String endPoint) {
        this.endPoint = endPoint;
    }

    /**
     * Obtiene la instancia unica de la clase. La primera vez que se llame la inicializara antes de
     * devolverla
     *
     * @param endPoint La URL del servidor donde se alojan los servicios Rest
     * @return La instancia unica de la clase
     */
    public static NetModule getInstance(String endPoint) {
        if (instance == null) {
            instance = new NetModule(endPoint);
        }

        return instance;
    }

    /**
     * Provee del interceptor de Log para retrofit
     *
     * @return El interceptor de log para retrofit
     */
    @Singleton
    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return interceptor;
    }

    @Singleton
    @Provides
    ApiKeyInterceptor provideApiKeyInterceptor(PreferencesHelper preferencesHelper) {
        return new ApiKeyInterceptor(preferencesHelper);
    }

    /**
     * Provee del cliente HTTP con el log configurado
     *
     * @param loggingInterceptor El interceptor del log
     * @return El cliente HTTP
     */
    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor, ApiKeyInterceptor apiKeyInterceptor) {
        return new OkHttpClient.Builder().
                readTimeout(READ_TIMEOUT, TimeUnit.SECONDS).
                connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS).
                addInterceptor(loggingInterceptor).
                addInterceptor(apiKeyInterceptor).
                build();
    }

    /**
     * Provee del objeto Retrofit que se encargara de instanciar las interfaces que construyen
     * las peticiones remotasl
     *
     * @param okHttpClient El cliente HTTP
     * @param gson         El objeto gson para serializar y deserializar la informacion a transmitir
     * @return El objeto Retrofit
     * @see <a href="http://square.github.io/retrofit/">Retrofit</a>
     */
    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder().
                baseUrl(endPoint).
                addConverterFactory(GsonConverterFactory.create(gson)).
                client(okHttpClient).
                build();
    }

    /**
     * Provee de la Api construir las llamadas a los servicios Rest
     *
     * @param retrofit Instancia de retrofit para construir la Api
     * @return La api para construir las llamadas
     */
    @Provides
    PhotoClient.Api providePhotoClientApi(Retrofit retrofit) {
        return retrofit.create(PhotoClient.Api.class);
    }

    /**
     * Provee del cliente Rest para realizar las llamadas remotas y procesar la respuesta
     *
     * @param api    La Api para realizar las llamadas
     * @param mapper El conversor entre clases
     * @return el cliente Rest para realizar las llamadas
     */
    @Provides
    PhotoClient providePhotoClient(PhotoClient.Api api, ModelMapper mapper) {
        return new PhotoClientImpl(api, mapper);
    }
}
