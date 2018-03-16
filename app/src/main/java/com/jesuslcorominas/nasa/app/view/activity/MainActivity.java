package com.jesuslcorominas.nasa.app.view.activity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.view.View;

import com.jesuslcorominas.nasa.app.App;
import com.jesuslcorominas.nasa.app.R;
import com.jesuslcorominas.nasa.app.event.impl.GetPhotosEvent;
import com.jesuslcorominas.nasa.app.presenter.MainPresenter;
import com.jesuslcorominas.nasa.app.presenter.Presenter;
import com.jesuslcorominas.nasa.app.view.callbackview.CallbackView;
import com.jesuslcorominas.nasa.app.view.callbackview.MainView;
import com.jesuslcorominas.nasa.app.view.fragment.MainFragment;
import com.jesuslcorominas.nasa.common.model.Photo;
import com.jesuslcorominas.nasa.common.util.DateUtil;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.res.StringRes;
import org.greenrobot.eventbus.EventBus;
import org.joda.time.DateTime;

import java.util.Collection;

import javax.inject.Inject;

import de.psdev.licensesdialog.LicensesDialog;


/**
 * Activity principal de la aplicacion. Muestra en un {@link MainFragment} un listado de {@link Photo}
 * que habra obtenido a traves del {@link MainPresenter} que realizara la llamada al servicio rest
 * de la NASA
 *
 * @author Jesús López Corominas
 */
@EActivity(R.layout.activity_main)
@SuppressLint("Registered")
@OptionsMenu(R.menu.menu_main)
public class MainActivity extends AbstractBaseAppCompatActivity<MainView> implements MainView,
        MainFragment.MainFragmentInteractionListener,
        DatePickerDialog.OnDateSetListener {


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
        presenter.setApiKey();
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
    @OptionsItem(R.id.item_menu_date)
    void showCalendar() {
        // La fecha maxima de las fotos es ayer
        DateTime maxDate = DateUtil.maxDate();

        // En DatePickerDialog los meses empiezan en 0 y en jodatime en 1
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this, maxDate.getYear(), maxDate.getMonthOfYear() - 1, maxDate.getDayOfMonth());
        dpd.setMaxDate(maxDate.toGregorianCalendar());
        dpd.setMinDate(DateUtil.minDate().toGregorianCalendar());
        dpd.setCancelText(R.string.button_cancel);
        dpd.setOkText(R.string.button_accept);
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

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
    public void showMain() {
        getFragmentManager().beginTransaction()
                .add(R.id.contentMain_frameLayout_container, MainFragment.newInstance())
                .commit();
    }

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
        callPresenterGetPhotosByDate(DateUtil.maxDate());
    }

    @Override
    public void onItemClick(View originView, Photo photo) {
        navigator.detail(this, photo.getImgSrc(), ActivityOptions.makeSceneTransitionAnimation(this, originView, animationName).toBundle());
    }

    // ==============================
    // DatePickerDialog.OnDateSetListener
    // ==============================
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        // En el DatePickerDialog el mes empieza a contar en 0 mientras que jodatime empieza a contar en 1
        DateTime date = new DateTime(year, monthOfYear + 1, dayOfMonth, 0, 0);
        callPresenterGetPhotosByDate(date);
    }

    // ==============================
    // Llamadas al presenter en Background
    // ==============================

    /**
     * Para no bloquear la UI todas las llamadas a los metodos del {@link Presenter} se ejecutan en
     * segundo plano. Para ello, extraemos la llamada a un metodo que anotaremos como Background. Lo
     * hacemos aqui para poder desacoplar el {@link Presenter} de Android, ya que si lo hiciesemos
     * en el mismo presenter tendria que tener una referencia a AndroidAnnotations. Es importante que
     * la implementacion de los metodos de {@link CallbackView}
     * se anoten con UIThread para volver al hilo principal y poder modificar la UI
     */
    @Background
    void callPresenterGetPhotos(int sol) {
        presenter.getPhotosBySol(sol);
    }

    @Background
    void callPresenterGetPhotosByDate(DateTime earthDate) {
        presenter.getPhotosByDate(earthDate);
    }
}
