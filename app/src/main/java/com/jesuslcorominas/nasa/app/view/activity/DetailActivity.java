package com.jesuslcorominas.nasa.app.view.activity;

import android.annotation.SuppressLint;

import com.jesuslcorominas.nasa.app.App;
import com.jesuslcorominas.nasa.app.R;
import com.jesuslcorominas.nasa.app.presenter.DetailPresenter;
import com.jesuslcorominas.nasa.app.presenter.Presenter;
import com.jesuslcorominas.nasa.app.view.callbackview.DetailView;
import com.jesuslcorominas.nasa.app.view.fragment.DetailFragment;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

import javax.inject.Inject;

/**
 * Activity de detalle de un {@link com.jesuslcorominas.nasa.app.model.Photo}. Muestra a pantalla
 * completa, ocultando la barra de estado y de navegacion, una imagen
 *
 * @author Jesús López Corominas
 */
@EActivity(R.layout.activity_detail)
@SuppressLint("Registered")
public class DetailActivity extends AbstractBaseActivity<DetailView> implements DetailView,
        DetailFragment.DetailFragmentInteractionListener {

    @Inject
    DetailPresenter presenter;

    @Extra
    String imgSrc;

    // ==============================
    // Activity
    // ==============================

    // ==============================
    // AbstractBaseActivity
    // ==============================
    @Override
    Presenter<DetailView> getPresenter() {
        return presenter;
    }

    @Override
    DetailView getCallbackView() {
        return this;
    }

    @Override
    void init() {
        getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        fullScreen();

        getFragmentManager().beginTransaction()
                .add(R.id.contentDetail_frameLayout_container, DetailFragment.newInstance(imgSrc))
                .commit();
    }

    @Override
    void initializeDagger() {
        ((App) getApplication()).getDetailComponent().inject(this);
    }

    // ==============================
    // DetailFragment.DetailFragmentInteractionListener
    // ==============================
    @Override
    public void onPhotoClick() {
        fullScreen();
    }

}
