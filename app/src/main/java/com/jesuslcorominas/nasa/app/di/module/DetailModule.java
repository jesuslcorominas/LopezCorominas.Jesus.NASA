package com.jesuslcorominas.nasa.app.di.module;

import com.jesuslcorominas.nasa.app.presenter.DetailPresenter;
import com.jesuslcorominas.nasa.app.presenter.impl.DetailPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * @author Jesús López Corominas
 */
@Module
public class DetailModule {

    @Provides
    DetailPresenter provideDetailPresenter() {
        return new DetailPresenterImpl();
    }
}
