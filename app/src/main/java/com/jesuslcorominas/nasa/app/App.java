package com.jesuslcorominas.nasa.app;

import android.app.Application;

import com.jesuslcorominas.nasa.app.di.component.DaggerDetailComponent;
import com.jesuslcorominas.nasa.app.di.component.DaggerMainComponent;
import com.jesuslcorominas.nasa.app.di.component.DetailComponent;
import com.jesuslcorominas.nasa.app.di.component.MainComponent;
import com.jesuslcorominas.nasa.data.di.module.NetModule;

import org.androidannotations.annotations.EApplication;

/**
 * @author Jesús López Corominas
 */
@EApplication
public class App extends Application {

    MainComponent mainComponent;
    DetailComponent detailComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        String endPoint = BuildConfig.END_POINT;

        NetModule netModule = NetModule.getInstance(endPoint);

        mainComponent = DaggerMainComponent.builder().netModule(netModule).build();
        detailComponent = DaggerDetailComponent.builder().build();
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }

    public DetailComponent getDetailComponent() {
        return detailComponent;
    }
}
