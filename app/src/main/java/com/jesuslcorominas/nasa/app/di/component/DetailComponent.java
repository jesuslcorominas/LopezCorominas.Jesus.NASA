package com.jesuslcorominas.nasa.app.di.component;

import com.jesuslcorominas.nasa.app.di.module.DetailModule;
import com.jesuslcorominas.nasa.app.view.activity.DetailActivity;

import dagger.Component;

/**
 * @author Jesús López Corominas
 */
@Component (modules = {DetailModule.class})
public interface DetailComponent {

    void inject(DetailActivity detailActivity);
}
