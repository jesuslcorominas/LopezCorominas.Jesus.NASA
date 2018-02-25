package com.jesuslcorominas.nasa.app.view.adapter.divider;


import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * {@link android.support.v7.widget.RecyclerView.ItemDecoration} horizontal para hacer de separador
 * entre los elementos de un {@link RecyclerView} y el propio recycler. Es decir, para hacer de margen
 * izquierdo y derecho en los elementos del recycler
 */
@EBean
public class HorizontalSpaceItemDecoration extends RecyclerView.ItemDecoration {

    /**
     * El valor del margen
     */
    private int horizontalSpaceWidth;

    /**
     * El contexto de la aplicacion
     */
    @RootContext
    Context context;

    /**
     * Establece el valor del margen a dejar.
     *
     * @param horizontalSpaceWidthDimen Valor del margen a dejar como una {@link android.support.annotation.Dimension}
     *                                  definida en el xml dimens
     */
    public void setWidthDimen(@DimenRes int horizontalSpaceWidthDimen) {
        this.horizontalSpaceWidth = context.getResources().getDimensionPixelOffset(horizontalSpaceWidthDimen);
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
        outRect.right = horizontalSpaceWidth;
        outRect.left = horizontalSpaceWidth;
    }
}