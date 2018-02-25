package com.jesuslcorominas.nasa.app.di.module;

import com.jesuslcorominas.nasa.app.presenter.MainPresenter;
import com.jesuslcorominas.nasa.app.presenter.impl.MainPresenterImpl;
import com.jesuslcorominas.nasa.model.di.module.UseCaseModule;
import com.jesuslcorominas.nasa.model.usecase.GetPhotosUseCase;

import dagger.Module;
import dagger.Provides;

/**
 * Modulo de Dagger2 que provee de las dependencias de {@link com.jesuslcorominas.nasa.app.view.activity.MainActivity}
 *
 * @author Jesús López Corominas
 * @see <a href="https://google.github.io/dagger/">Dagger2</a>
 */
@Module(includes = {UseCaseModule.class})
public class MainModule {

    @Provides
    MainPresenter provideMainPresenter(GetPhotosUseCase getPhotosUseCase) {
        return new MainPresenterImpl(getPhotosUseCase);
    }
}
