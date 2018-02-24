package com.jesuslcorominas.nasa.app.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;

import com.jesuslcorominas.nasa.app.model.Photo;
import com.jesuslcorominas.nasa.app.view.adapter.item.PhotoItemView;
import com.jesuslcorominas.nasa.app.view.adapter.item.PhotoItemView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * @author Jesús López Corominas
 */
@EBean
public class PhotosAdapter extends RecyclerViewBaseAdapter<Photo, PhotoItemView> {

    @RootContext
    Context context;

    @Override
    protected PhotoItemView onCreateItemView(ViewGroup parent, int viewType) {
        PhotoItemView itemView = PhotoItemView_.build(context);
        itemView.setLayoutParams(new CardView.LayoutParams(CardView.LayoutParams.MATCH_PARENT, CardView.LayoutParams.WRAP_CONTENT));

        return itemView;
    }
}
