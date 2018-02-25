package com.jesuslcorominas.nasa.app.view.adapter.item;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.widget.LinearLayout;

import com.jesuslcorominas.nasa.app.R;
import com.jesuslcorominas.nasa.app.model.Photo;
import com.jesuslcorominas.nasa.common.util.DateUtil;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * {@inheritDoc}
 * <p>
 * ItemView de {@link Photo}
 *
 * @author Jesús López Corominas
 */
@EViewGroup(R.layout.item_photo)
public class PhotoItemView extends CardView implements ItemView<Photo> {

    /**
     * El contexto de la aplicacion
     */
    private Context context;

    @ViewById(R.id.item_photo_linearLayout_content)
    LinearLayout linearLayoutContent;

    @ViewById(R.id.imageView_photo)
    AppCompatImageView imageViewPhoto;

    @ViewById(R.id.item_photo_textView_date)
    AppCompatTextView textViewDate;

    @ViewById(R.id.item_photo_textView_cameraName)
    AppCompatTextView textViewCameraName;

    public PhotoItemView(Context context) {
        super(context);

        this.context = context;
    }

    @Override
    public void bind(Photo item, int position) {
        this.setCardBackgroundColor(context.getResources().getColor(R.color.background_light));

        linearLayoutContent.setBackgroundColor(context.getResources().getColor(position % 2 == 0 ? R.color.white : R.color.odd_color));

        textViewCameraName.setText(item.getCamera().getFullName());
        textViewDate.setText(item.getEarthDate().toString(DateUtil.PRINT_DATE_FORMAT));

        Picasso.with(context).
                load(item.getImgSrc()).
                placeholder(R.drawable.ic_no_image).
                error(R.drawable.ic_no_image).
                into(imageViewPhoto);
    }

    public AppCompatImageView getImageViewPhoto() {
        return imageViewPhoto;
    }
}
