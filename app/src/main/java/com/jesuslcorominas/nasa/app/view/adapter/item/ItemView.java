package com.jesuslcorominas.nasa.app.view.adapter.item;

/**
 * Interface para vistas a utilizar desde los adaptadores de la aplicacion
 *
 * @author Jesus Lopez Corominas
 * @see <a href="https://github.com/excilys/androidannotations/wiki/Adapters-and-lists" />
 */
public interface ItemView<T> {

    /**
     * Enlaza el elemento a pintar con la vista en la que se pintara. Establece los valores de
     * la vista en funcion del item.
     *
     * @param item     El elementos a mostrar
     * @param position La posicion en la lista
     */
    void bind(T item, int position);
}
