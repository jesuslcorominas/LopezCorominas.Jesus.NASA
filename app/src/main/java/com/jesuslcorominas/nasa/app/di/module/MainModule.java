package com.jesuslcorominas.nasa.app.di.module;

import com.jesuslcorominas.nasa.app.presenter.MainPresenter;
import com.jesuslcorominas.nasa.app.presenter.impl.MainPresenterImpl;
import com.jesuslcorominas.nasa.app.view.activity.MainActivity;
import com.jesuslcorominas.nasa.data.di.module.RepositoryModule;
import com.jesuslcorominas.nasa.model.di.module.UseCaseModule;
import com.jesuslcorominas.nasa.model.usecase.GetPhotosByDateUseCase;
import com.jesuslcorominas.nasa.model.usecase.GetPhotosBySolUseCase;
import com.jesuslcorominas.nasa.model.usecase.SetApiKeyUseCase;

import dagger.Module;
import dagger.Provides;

/**
 * Modulo de Dagger2 que provee de las dependencias de {@link MainActivity}
 *
 * @author Jesús López Corominas
 * @see <a href="https://google.github.io/dagger/">Dagger2</a>
 */
@Module(includes = {UseCaseModule.class, PreferencesModule.class, RepositoryModule.class})
public class MainModule {

    @Provides
    MainPresenter provideMainPresenter(GetPhotosBySolUseCase getPhotosBySolUseCase, GetPhotosByDateUseCase getPhotosByDateUseCase, SetApiKeyUseCase setApiKeyUseCase) {
        return new MainPresenterImpl(getPhotosBySolUseCase, getPhotosByDateUseCase, setApiKeyUseCase);
    }
}
