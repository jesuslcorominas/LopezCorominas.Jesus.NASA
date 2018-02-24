package com.jesuslcorominas.nasa.app.di.component;

import com.jesuslcorominas.nasa.app.di.module.MainModule;
import com.jesuslcorominas.nasa.app.view.activity.MainActivity;
import com.jesuslcorominas.nasa.data.di.module.RepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Jesús López Corominas
 */
@Singleton
@Component(modules = {MainModule.class, RepositoryModule.class})
public interface MainComponent {

    void inject(MainActivity mainActivity);
}
