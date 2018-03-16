package com.jesuslcorominas.nasa.app.util;

import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Bean de preferencias de Android Annotations.
 * <p>
 * Al anotar la clase como @{@link SharedPref}, unicamente tenemos que crear los metodos que devuelvan
 * el tipo de preferencia y con el nombre de la preferencia.
 * <p>
 * Este es el unico caso de AndroidAnnotations en el que tenemos que trabajar con la clase
 * extendida es decir {@link Preferences_}
 *
 * @author Jesús López Corominas
 * @see <a href="https://github.com/androidannotations/androidannotations/wiki/SharedPreferencesHelpers" />
 * <p>
 */
@SharedPref(value = SharedPref.Scope.UNIQUE)
public interface Preferences {

    String apiKey();
}
