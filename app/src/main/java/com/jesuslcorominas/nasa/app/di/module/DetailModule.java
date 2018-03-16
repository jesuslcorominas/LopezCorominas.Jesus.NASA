package com.jesuslcorominas.nasa.app.di.module;

import com.jesuslcorominas.nasa.app.presenter.DetailPresenter;
import com.jesuslcorominas.nasa.app.presenter.impl.DetailPresenterImpl;
import com.jesuslcorominas.nasa.app.view.activity.DetailActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Modulo de Dagger2 que provee de las dependencias de {@link DetailActivity}
 *
 * @author Jesús López Corominas
 * @see <a href="https://google.github.io/dagger/">Dagger2</a>
 */
@Module
public class DetailModule {

    @Provides
    DetailPresenter provideDetailPresenter() {
        return new DetailPresenterImpl();
    }
}
