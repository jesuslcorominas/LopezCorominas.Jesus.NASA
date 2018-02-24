package com.jesuslcorominas.nasa.app.view.activity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.view.View;

import com.jesuslcorominas.nasa.app.App;
import com.jesuslcorominas.nasa.app.R;
import com.jesuslcorominas.nasa.app.event.impl.GetPhotosEvent;
import com.jesuslcorominas.nasa.app.model.Photo;
import com.jesuslcorominas.nasa.app.presenter.MainPresenter;
import com.jesuslcorominas.nasa.app.presenter.Presenter;
import com.jesuslcorominas.nasa.app.view.callbackview.MainView;
import com.jesuslcorominas.nasa.app.view.fragment.MainFragment;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.res.StringRes;
import org.greenrobot.eventbus.EventBus;

import java.util.Collection;

import javax.inject.Inject;

import de.psdev.licensesdialog.LicensesDialog;

@EActivity(R.layout.activity_main)
@SuppressLint("Registered")
@OptionsMenu(R.menu.menu_main)
public class MainActivity extends AbstractBaseAppCompatActivity<MainView> implements MainView,
        MainFragment.MainFragmentInteractionListener {


    @Inject
    MainPresenter presenter;

    @StringRes(R.string.photo_animation)
    String animationName;

    // ==============================
    // Activity
    // ==============================

    // ==============================
    // AbstractBaseAppCompatActivity
    // ==============================
    @Override
    void init() {
        getFragmentManager().beginTransaction()
                .add(R.id.contentMain_frameLayout_container, MainFragment.newInstance())
                .commit();
    }

    @Override
    Presenter<MainView> getPresenter() {
        return presenter;
    }

    @Override
    MainView getCallbackView() {
        return this;
    }

    @Override
    void initializeDagger() {
        ((App) getApplication()).getMainComponent().inject(this);
    }

    // ==============================
    // Menu
    // ==============================
    @OptionsItem(R.id.item_menu_copyright)
    void showLicenses() {
        new LicensesDialog.Builder(this)
                .setNotices(R.raw.notices)
                .setIncludeOwnLicense(false)
                .setCloseText(R.string.button_accept)
                .build()
                .show();
    }

    // ==============================
    // MainView
    // ==============================
    @UiThread
    @Override
    public void showPhotos(Collection<Photo> photos) {
        EventBus.getDefault().post(new GetPhotosEvent(photos));
    }

    // ==============================
    // MainFragment.MainFragmentInteractionListener
    // ==============================
    @Override
    public void onInitFinished() {
        callPresenterGetPhotos();
    }

    @Override
    public void onItemClick(View originView, Photo photo) {
        navigator.detail(this, photo.getImgSrc(), ActivityOptions.makeSceneTransitionAnimation(this, originView, animationName).toBundle());
    }

    // ==============================
    // Llamadas al presenter en Background
    // ==============================
    @Background
    void callPresenterGetPhotos() {
        presenter.getPhotos();
    }
}
