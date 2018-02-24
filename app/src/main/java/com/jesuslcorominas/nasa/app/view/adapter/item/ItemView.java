package com.jesuslcorominas.nasa.app.view.adapter.item;

/**
 * Interface para vistas a utilizar desde los adaptadores de la aplicacion
 *
 * @author Jesus Lopez Corominas
 * @see <a href="https://github.com/excilys/androidannotations/wiki/Adapters-and-lists" />
 */
public interface ItemView<T> {

    void bind(T item, int position);
}
