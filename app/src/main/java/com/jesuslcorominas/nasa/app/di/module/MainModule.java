package com.jesuslcorominas.nasa.app.di.module;

import com.jesuslcorominas.nasa.app.presenter.MainPresenter;
import com.jesuslcorominas.nasa.app.presenter.impl.MainPresenterImpl;
import com.jesuslcorominas.nasa.model.di.module.UseCaseModule;
import com.jesuslcorominas.nasa.model.usecase.GetPhotosUseCase;

import dagger.Module;
import dagger.Provides;

/**
 * @author Jesús López Corominas
 */
@Module(includes = {UseCaseModule.class})
public class MainModule {

    @Provides
    MainPresenter provideMainPresenter(GetPhotosUseCase getPhotosUseCase) {
        return new MainPresenterImpl(getPhotosUseCase);
    }
}
