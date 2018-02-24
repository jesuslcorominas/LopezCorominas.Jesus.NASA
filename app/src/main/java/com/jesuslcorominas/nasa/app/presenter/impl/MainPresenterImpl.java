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
 * @author Jesús López Corominas
 */
public class MainPresenterImpl extends AbstractPresenter<MainView> implements MainPresenter {

    private final GetPhotosUseCase getPhotosUseCase;

    public MainPresenterImpl(GetPhotosUseCase getPhotosUseCase) {
        this.getPhotosUseCase = getPhotosUseCase;
    }

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
                callbackView.showError(error.getCode(), error.getMessage());
            }
        });
    }
}
