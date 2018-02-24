package com.jesuslcorominas.nasa.app.util;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.jesuslcorominas.nasa.app.view.activity.DetailActivity_;

import org.androidannotations.annotations.EBean;

/**
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

    public void detail(Context origin, String imgSrc, Bundle options) {
        DetailActivity_.intent(origin).imgSrc(imgSrc).withOptions(options).start();
    }
}
