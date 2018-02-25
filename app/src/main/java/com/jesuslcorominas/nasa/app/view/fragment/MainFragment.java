package com.jesuslcorominas.nasa.app.view.fragment;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jesuslcorominas.nasa.app.R;
import com.jesuslcorominas.nasa.app.event.impl.GetPhotosEvent;
import com.jesuslcorominas.nasa.app.model.Photo;
import com.jesuslcorominas.nasa.app.view.adapter.PhotosAdapter;
import com.jesuslcorominas.nasa.app.view.adapter.divider.HorizontalSpaceItemDecoration;
import com.jesuslcorominas.nasa.app.view.adapter.divider.VerticalSpaceItemDecoration;
import com.jesuslcorominas.nasa.app.view.adapter.item.PhotoItemView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;

/**
 * Fragment de la ventana principal. Muestra un listado de {@link Photo}
 *
 * @author Jesús López Corominas
 */
@EFragment(R.layout.fragment_main)
public class MainFragment extends AbstractBaseFragment implements PhotosAdapter.OnItemClickListener<Photo, PhotoItemView> {

    @ViewById(R.id.fragment_main_recyclerView_photos)
    RecyclerView recyclerViewPhotos;

    @Bean
    PhotosAdapter photosAdapter;

    @Bean
    HorizontalSpaceItemDecoration horizontalSpaceItemDecoration;

    @Bean
    VerticalSpaceItemDecoration verticalSpaceItemDecoration;

    private MainFragmentInteractionListener listener;

    // =========================================
    //  NewInstance
    // =========================================
    public static MainFragment newInstance() {
        return MainFragment_.builder().build();
    }

    // =========================================
    //  Fragment
    // =========================================
    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);

        try {
            listener = (MainFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new RuntimeException("Debes implementar MainFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();

        listener = null;
    }

    // =========================================
    //  AbstractBaseFragment
    // =========================================
    @Override
    void init() {
        photosAdapter.setOnItemClickListener(this);

        recyclerViewPhotos.setAdapter(photosAdapter);

        verticalSpaceItemDecoration.setHeightDimen(R.dimen.margin);
        horizontalSpaceItemDecoration.setWidthDimen(R.dimen.margin);

        recyclerViewPhotos.addItemDecoration(verticalSpaceItemDecoration);
        recyclerViewPhotos.addItemDecoration(horizontalSpaceItemDecoration);

        recyclerViewPhotos.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        if (listener != null) {
            listener.onInitFinished();
        }
    }

    // =========================================
    //  Eventos de EventBus
    // =========================================

    /**
     * {@link com.jesuslcorominas.nasa.app.event.Event} propagado a traves de EventBus para recibir
     * el listado de {@link Photo}
     *
     * @param event El evento con el listado de {@link Photo}
     */
    @SuppressWarnings("unused")
    @Subscribe
    public void onGetPhotosEvent(GetPhotosEvent event) {
        photosAdapter.addItems(event.getPhotos());
    }


    // =========================================
    //  PhotosAdapter.OnItemClickListener
    // =========================================
    @Override
    public void onItemClick(int position, PhotoItemView view, Photo data) {
        if (listener != null) {
            listener.onItemClick(view.getImageViewPhoto(), data);
        }
    }

    // =========================================
    //  Interaction
    // =========================================

    /**
     * Interface de interaccion con el Fragment que debera ser implementada por la {@link Activity}
     * contenedora
     */
    public interface MainFragmentInteractionListener {
        /**
         * Metodo a ejecutar cuando termina de cargarse el Fragment
         */
        void onInitFinished();

        /**
         * Metodo a ejecutar cuando se pulsa en un elemento del listado
         *
         * @param originView La vista sobre la que se pulso para realizar la transicion animada
         * @param photo      La photo de la que se quiere mostrar el detalle
         */
        void onItemClick(View originView, Photo photo);
    }

}
