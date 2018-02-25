package com.jesuslcorominas.nasa.app.presenter.impl;

import com.jesuslcorominas.nasa.app.model.Photo;
import com.jesuslcorominas.nasa.app.presenter.MainPresenter;
import com.jesuslcorominas.nasa.app.view.callbackview.MainView;
import com.jesuslcorominas.nasa.common.Error;
import com.jesuslcorominas.nasa.model.entity.PhotoModelEntity;
import com.jesuslcorominas.nasa.model.usecase.GetPhotosUseCase;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.Collection;

/**
 * {@inheritDoc}
 * <p>
 * Implementacion del {@link com.jesuslcorominas.nasa.app.presenter.Presenter} asociado a
 * {@link com.jesuslcorominas.nasa.app.view.activity.MainActivity}
 *
 * @author Jesús López Corominas
 */
public class MainPresenterImpl extends AbstractPresenter<MainView> implements MainPresenter {

    /**
     * {@link com.jesuslcorominas.nasa.model.usecase.UseCase} para obtener el listado de {@link Photo}
     */
    private final GetPhotosUseCase getPhotosUseCase;

    /**
     * Constructor unico parametrizado con los {@link com.jesuslcorominas.nasa.model.usecase.UseCase}
     * del {@link com.jesuslcorominas.nasa.app.presenter.Presenter}. Sera utilizado por Dagger2 para
     * inyectar las dependecias de la clase.
     *
     * @param getPhotosUseCase Caso de uso para obtener el listado de {@link Photo}
     */
    public MainPresenterImpl(GetPhotosUseCase getPhotosUseCase) {
        this.getPhotosUseCase = getPhotosUseCase;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Hace que la vista muestre un Progress para indicarle al usuario que se esta trabajando e
     * invoca al {@link GetPhotosUseCase} para obtener el listado. Si lo consigue, le dice a
     * la vista que oculte el Progress y muestre el listado. Si hay un error, le dice que oculte el
     * Progress y muestre la informacion del error.
     */
    @Override
    public void getPhotos() {

        callbackView.showProgress();

        getPhotosUseCase.getPhotos(new GetPhotosUseCase.GetPhotosCallback() {
            @Override
            public void onSuccess(Collection<PhotoModelEntity> result) {
                Collection<Photo> photos = new ModelMapper().map(result, new TypeToken<Collection<Photo>>() {
                }.getType());

                if (resumed) {
                    callbackView.hideProgress();

                    callbackView.showPhotos(photos);
                }

            }

            @Override
            public void onError(Error error) {
                callbackView.hideProgress();

                callbackView.showError(error.getCode(), error.getMessage());
            }
        });
    }
}
