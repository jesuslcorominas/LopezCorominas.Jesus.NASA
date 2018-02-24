package com.jesuslcorominas.nasa.app.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.jesuslcorominas.nasa.app.R;
import com.jesuslcorominas.nasa.app.event.AbstractEvent;
import com.jesuslcorominas.nasa.app.presenter.Presenter;
import com.jesuslcorominas.nasa.app.util.Navigator;
import com.jesuslcorominas.nasa.app.view.callbackview.CallbackView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * @author Jesús López Corominas
 */
@EActivity
abstract class AbstractBaseActivity<V extends CallbackView> extends Activity implements CallbackView {

    abstract void init();

    abstract Presenter<V> getPresenter();

    abstract V getCallbackView();

    abstract void initializeDagger();

    @Bean
    Navigator navigator;

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
        init();

        initializeDagger();
        initPresenter();
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
    // Otros
    // ==============================
    private void initPresenter() {
        Presenter<V> presenter = getPresenter();
        if (presenter == null) {
            throw new RuntimeException("Debes establecer un presenter para la activity");
        }

        presenter.setCallbackView(getCallbackView());
    }

    protected void showError(@StringRes int message) {
        getDialogBuilder(message).create().show();
    }

    protected void showError(String message) {
        getDialogBuilder(message).create().show();
    }

    protected AlertDialog.Builder getDialogBuilder(@StringRes int message) {
        return getDialogBuilder(getString(message));
    }

    protected AlertDialog.Builder getDialogBuilder(String message) {
        return new AlertDialog.Builder(this).
                setTitle(message).
                setMessage(message).
                setPositiveButton(R.string.button_accept, null);
    }

    protected void fullScreen() {
        int newUiOptions = getWindow().getDecorView().getSystemUiVisibility();

        // Navigation bar hiding:  Backwards compatible to ICS.
        newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

        // Status bar hiding: Backwards compatible to Jellybean
        newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;

        // Immersive mode: Backward compatible to KitKat.
        newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);

        setImmersive(true);
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
