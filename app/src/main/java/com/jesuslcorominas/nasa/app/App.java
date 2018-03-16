package com.jesuslcorominas.nasa.app;

import android.app.Application;

import com.jesuslcorominas.nasa.app.di.component.DaggerDetailComponent;
import com.jesuslcorominas.nasa.app.di.component.DaggerMainComponent;
import com.jesuslcorominas.nasa.app.di.component.DetailComponent;
import com.jesuslcorominas.nasa.app.di.component.MainComponent;
import com.jesuslcorominas.nasa.app.di.module.PreferencesModule;
import com.jesuslcorominas.nasa.app.view.activity.DetailActivity;
import com.jesuslcorominas.nasa.app.view.activity.MainActivity;
import com.jesuslcorominas.nasa.data.di.module.DatabaseModule;
import com.jesuslcorominas.nasa.data.di.module.NetModule;

import org.androidannotations.annotations.EApplication;

/**
 * Punto de entrada a la aplicacion. Configuramos los {@link dagger.Component} de Dagger que
 * inyectaran las dependencias en toda la aplicacion y expone metodos para que sean accesibles
 * desde las {@link android.app.Activity}
 *
 * @author Jesús López Corominas
 * @see <a href="https://github.com/androidannotations/androidannotations/wiki/Enhancing-the-Application-class">EApplication</a>
 */
@SuppressWarnings("registered")
@EApplication
public class App extends Application {

    MainComponent mainComponent;
    DetailComponent detailComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        String endPoint = BuildConfig.END_POINT;

        NetModule netModule = NetModule.getInstance(endPoint);
        PreferencesModule preferencesModule = new PreferencesModule(this);
        DatabaseModule dbModule = DatabaseModule.getInstance(getFilesDir());

        mainComponent = DaggerMainComponent.builder().
                netModule(netModule).
                preferencesModule(preferencesModule).
                databaseModule(dbModule).
                build();
        detailComponent = DaggerDetailComponent.builder().build();
    }

    /**
     * Obtiene el {@link dagger.Component} de la {@link MainActivity}
     *
     * @return el {@link dagger.Component} de la {@link MainActivity}
     */
    public MainComponent getMainComponent() {
        return mainComponent;
    }

    /**
     * Obtiene el {@link dagger.Component} de la {@link DetailActivity}
     *
     * @return el {@link dagger.Component} de la {@link DetailActivity}
     */
    public DetailComponent getDetailComponent() {
        return detailComponent;
    }
}
