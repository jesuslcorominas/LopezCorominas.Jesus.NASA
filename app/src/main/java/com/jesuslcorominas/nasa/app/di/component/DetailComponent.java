package com.jesuslcorominas.nasa.app.di.component;

import com.jesuslcorominas.nasa.app.di.module.DetailModule;
import com.jesuslcorominas.nasa.app.view.activity.DetailActivity;

import dagger.Component;

/**
 * Componente de Dagger2 para las inyecciones necesarias en {@link DetailActivity}
 *
 * @author Jesús López Corominas
 * @see <a href="https://google.github.io/dagger/">Dagger2</a>
 */
@Component(modules = {DetailModule.class})
public interface DetailComponent {

    void inject(DetailActivity detailActivity);
}
