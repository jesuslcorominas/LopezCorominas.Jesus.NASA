package com.jesuslcorominas.nasa.app.di.module;

import android.content.Context;

import com.jesuslcorominas.nasa.app.util.PreferencesHelperImpl_;
import com.jesuslcorominas.nasa.model.preferences.PreferencesHelper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jlopezco on 23/02/2018.
 */

@Module
public class PreferencesModule {

    private Context context;

    public PreferencesModule(Context context) {
        this.context = context;
    }

    @Provides
    PreferencesHelper providePreferencesHelper() {
        return PreferencesHelperImpl_.getInstance_(context);
    }
}
