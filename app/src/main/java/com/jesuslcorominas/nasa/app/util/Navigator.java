package com.jesuslcorominas.nasa.app.util;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.jesuslcorominas.nasa.app.view.activity.DetailActivity;
import com.jesuslcorominas.nasa.app.view.activity.DetailActivity_;

import org.androidannotations.annotations.EBean;

/**
 * {@link EBean} para centralizar toda la navegacion de la aplicacion.
 *
 * @see <a href="https://github.com/androidannotations/androidannotations/wiki/Enhance-custom-classes">Enhance Custom Clasess</a>
 * @author Jesús López Corominas
 */
@EBean
public class Navigator {

    /**
     * Hace up en la aplicacion. En esta aplicacion, mata la Activity que lo invoca
     *
     * @param origin La activity que invoca la accion up
     * @see <a href="https://developer.android.com/design/patterns/navigation.html">Up vs Back</a>
     */
    public void up(Activity origin) {
        origin.finish();
    }

    /**
     * Navega a la {@link DetailActivity}.
     *
     * @param origin  El contexto de la aplicacion que lanza la ventana de detalle
     * @param imgSrc  El path de la imagen a mostrar en la ventana de detalle
     * @param options {@link Bundle} con la informacion necesaria para la animacion de la imagen
     *                desde la vista principal a la de detalle
     * @see <a href="https://developer.android.com/training/material/animations.html?hl=es-419">Iniciar una actividad con un elemento compartido</a>
     */
    public void detail(Context origin, String imgSrc, Bundle options) {
        DetailActivity_.intent(origin).imgSrc(imgSrc).withOptions(options).start();
    }
}
