package com.jesuslcorominas.nasa.app.view.adapter.divider;


import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * {@link android.support.v7.widget.RecyclerView.ItemDecoration} vertical para hacer de separador
 * entre los elementos de un {@link RecyclerView}. Es decir, para hacer de margen inferior y
 * superior en los elementos del recycler.
 */
@EBean
public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {

    /**
     * El valor del margen
     */
    private int verticalSpaceHeight;

    /**
     * El contexto de la aplicacion
     */
    @RootContext
    Context context;

    /**
     * Establece el valor del margen a dejar.
     *
     * @param verticalSpaceDimen Valor del margen a dejar como una {@link android.support.annotation.Dimension}
     *                           definida en el xml dimens
     */
    public void setHeightDimen(@DimenRes int verticalSpaceDimen) {
        this.verticalSpaceHeight = context.getResources().getDimensionPixelOffset(verticalSpaceDimen);
    }

    /**
     * {@inheritDoc}
     *
     * @param outRect {@inheritDoc}
     * @param view    {@inheritDoc}
     * @param parent  {@inheritDoc}
     * @param state   {@inheritDoc}
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = verticalSpaceHeight;
        outRect.top = verticalSpaceHeight;
    }
}