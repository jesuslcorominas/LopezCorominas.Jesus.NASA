package com.jesuslcorominas.nasa.app.view.control;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * ViewHolder para los adaptadores de la aplicacion
 * Parametrizado con la vista que va a mostrar los datos por pantalla
 *
 * @author Jesus Lopez Corominas
 * @see @link {https://github.com/excilys/androidannotations/wiki/Adapters-and-lists}
 */
public class ViewWrapper<V extends View> extends RecyclerView.ViewHolder {

    private V view;

    public ViewWrapper(V itemView) {
        super(itemView);
        view = itemView;
    }

    public V getView() {
        return view;
    }
}
