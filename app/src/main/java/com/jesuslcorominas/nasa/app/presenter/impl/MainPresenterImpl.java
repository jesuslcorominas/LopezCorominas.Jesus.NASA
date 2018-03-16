package com.jesuslcorominas.nasa.app.presenter.impl;

import com.jesuslcorominas.nasa.app.BuildConfig;
import com.jesuslcorominas.nasa.app.presenter.MainPresenter;
import com.jesuslcorominas.nasa.app.view.activity.MainActivity;
import com.jesuslcorominas.nasa.app.view.callbackview.MainView;
import com.jesuslcorominas.nasa.common.Result;
import com.jesuslcorominas.nasa.common.model.Photo;
import com.jesuslcorominas.nasa.model.usecase.GetPhotosByDateUseCase;
import com.jesuslcorominas.nasa.model.usecase.GetPhotosBySolUseCase;
import com.jesuslcorominas.nasa.model.usecase.SetApiKeyUseCase;

import org.joda.time.DateTime;

import java.util.List;

/**
 * {@inheritDoc}
 * <p>
 * Implementacion del {@link com.jesuslcorominas.nasa.app.presenter.Presenter} asociado a
 * {@link MainActivity}
 *
 * @author Jesús López Corominas
 */
public class MainPresenterImpl extends AbstractPresenter<MainView> implements MainPresenter {

    /**
     * {@link com.jesuslcorominas.nasa.model.usecase.UseCase} para obtener el listado de {@link Photo}
     */
    private final GetPhotosBySolUseCase getPhotosBySolUseCase;

    private final GetPhotosByDateUseCase getPhotosByDateUseCase;

    private final SetApiKeyUseCase setApiKeyUseCase;

    /**
     * Constructor unico parametrizado con los {@link com.jesuslcorominas.nasa.model.usecase.UseCase}
     * del {@link com.jesuslcorominas.nasa.app.presenter.Presenter}. Sera utilizado por Dagger2 para
     * inyectar las dependecias de la clase.
     *
     * @param getPhotosBySolUseCase Caso de uso para obtener el listado de {@link Photo}
     */
    public MainPresenterImpl(GetPhotosBySolUseCase getPhotosBySolUseCase,
                             GetPhotosByDateUseCase getPhotosByDateUseCase,
                             SetApiKeyUseCase setApiKeyUseCase) {
        this.getPhotosBySolUseCase = getPhotosBySolUseCase;
        this.getPhotosByDateUseCase = getPhotosByDateUseCase;
        this.setApiKeyUseCase = setApiKeyUseCase;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Hace que la vista muestre un Progress para indicarle al usuario que se esta trabajando e
     * invoca al {@link GetPhotosBySolUseCase} para obtener el listado. Si lo consigue, le dice a
     * la vista que oculte el Progress y muestre el listado. Si hay un error, le dice que oculte el
     * Progress y muestre la informacion del error.
     */
    @Override
    public void getPhotosBySol(int sol) {

        callbackView.showProgress();

        getPhotosBySolUseCase.getPhotosBySol(sol, new GetPhotosBySolUseCase.GetPhotosCallback() {

            @Override
            public void onResult(Result<List<Photo>> result) {
                if (resumed) {
                    callbackView.hideProgress();

                    if (result.getResultCode() == Result.OK) {
                        callbackView.showPhotos(result.getData());
                    } else {
                        callbackView.showError(result.getResultCode(), result.getMessage());
                    }

                }
            }
        });
    }

    @Override
    public void getPhotosByDate(DateTime earthDate) {
        if (resumed) {
            callbackView.showProgress();
        }

        getPhotosByDateUseCase.getPhotosByDate(earthDate, new GetPhotosByDateUseCase.GetPhotosCallback() {
            @Override
            public void onResult(Result<List<Photo>> result) {
                if (resumed) {
                    callbackView.hideProgress();

                    if (result.getResultCode() == Result.OK) {
                        callbackView.showPhotos(result.getData());
                    } else {
                        callbackView.showError(result.getResultCode(), result.getMessage());
                    }

                }
            }
        });
    }

    @Override
    public void setApiKey() {
        setApiKeyUseCase.setApiKey(BuildConfig.API_KEY, new SetApiKeyUseCase.SetApiKeyCallback() {
            @Override
            public void onResult(Result<Void> result) {
                if (result.getResultCode() != Result.OK) {
                    // TODO no deberia darse nunca pero habria que cerrar la aplicacion
                    callbackView.showError(result.getResultCode(), result.getMessage());
                    return;
                }

                callbackView.showMain();
            }
        });
    }
}
