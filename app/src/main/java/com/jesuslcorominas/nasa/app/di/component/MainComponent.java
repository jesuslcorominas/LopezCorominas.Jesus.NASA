package com.jesuslcorominas.nasa.app.di.component;

import com.jesuslcorominas.nasa.app.di.module.MainModule;
import com.jesuslcorominas.nasa.app.view.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Componente de Dagger2 para las inyecciones necesarias en {@link MainActivity}
 *
 * @author Jesús López Corominas
 * @see <a href="https://google.github.io/dagger/">Dagger2</a>
 */
@Singleton
@Component(modules = {MainModule.class})
public interface MainComponent {

    void inject(MainActivity mainActivity);
}
