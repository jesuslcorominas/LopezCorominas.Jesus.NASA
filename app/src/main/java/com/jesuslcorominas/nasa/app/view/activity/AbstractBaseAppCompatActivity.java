package com.jesuslcorominas.nasa.app.view.activity;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.jesuslcorominas.nasa.app.R;
import com.jesuslcorominas.nasa.app.event.AbstractEvent;
import com.jesuslcorominas.nasa.app.presenter.Presenter;
import com.jesuslcorominas.nasa.app.util.Navigator;
import com.jesuslcorominas.nasa.app.view.callbackview.CallbackView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * @author Jesús López Corominas
 */
@EActivity
abstract class AbstractBaseAppCompatActivity<V extends CallbackView> extends AppCompatActivity implements CallbackView {

    abstract void init();

    abstract Presenter<V> getPresenter();

    abstract V getCallbackView();

    abstract void initializeDagger();

    @Bean
    Navigator navigator;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    private ProgressDialog progressDialog;

    // ==============================
    // Activity
    // ==============================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onPause() {
        super.onPause();

        getPresenter().onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        getPresenter().onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    // ==============================
    // Inicializacion
    // ==============================
    @AfterViews
    void afterViews() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        initializeDagger();
        initPresenter();

        init();
    }

    // ==============================
    // EventBus
    // ==============================

    /**
     * Metodo sin funcionalidad unicamente para evitar errores de EventBus.
     *
     * @param event Un evento de mentira que nunca llegara a ejecutarse
     * @see AbstractEvent
     */
    @SuppressWarnings("unused")
    @Subscribe
    public void onEvent(AbstractEvent event) {
    }

    // ==============================
    // ActionBar
    // ==============================
    @OptionsItem(android.R.id.home)
    void homeSelected() {
        navigator.up(this);
    }

    // ==============================
    // Otros
    // ==============================
    private void initPresenter() {
        Presenter<V> presenter = getPresenter();
        if (presenter == null) {
            throw new NullPointerException("Debes establecer un presenter para la activity");
        }

        presenter.setCallbackView(getCallbackView());
    }

    protected void showError(@StringRes int message) {
        showError(getString(message));
    }

    protected void showError(String message) {
        getDialogBuilder(message).create().show();
    }

    protected AlertDialog.Builder getDialogBuilder(@StringRes int message) {
        return getDialogBuilder(getString(message));
    }

    protected AlertDialog.Builder getDialogBuilder(String message) {
        return new AlertDialog.Builder(this).
                setTitle(R.string.app_name).
                setMessage(message).
                setPositiveButton(R.string.button_accept, null);
    }

    // ==============================
    // CallbackView
    // ==============================
    @UiThread
    @Override
    public void showProgress() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this, R.style.CustomProgressDialog);
            progressDialog.setCancelable(false);

            progressDialog.setIndeterminateDrawable(getDrawable(R.drawable.indeterminate_progress));

            progressDialog.show();
        }
    }

    @UiThread
    @Override
    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.cancel();
            progressDialog = null;
        }
    }

    @UiThread
    @Override
    public void showError(int code, String message) {
        showError(message);
    }

}
