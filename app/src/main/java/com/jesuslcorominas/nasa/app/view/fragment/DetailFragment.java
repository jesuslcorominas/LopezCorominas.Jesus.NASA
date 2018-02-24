package com.jesuslcorominas.nasa.app.view.fragment;

import android.app.Activity;
import android.support.v7.widget.AppCompatImageView;

import com.jesuslcorominas.nasa.app.R;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

/**
 * @author Jesús López Corominas
 */
@EFragment(R.layout.fragment_detail)
public class DetailFragment extends AbstractBaseFragment {

    @FragmentArg
    String imgSrc;

    @ViewById(R.id.fragment_detail_imageView_photo)
    AppCompatImageView imageViewPhoto;

    private DetailFragmentInteractionListener listener;

    // =========================================
    //  NewInstance
    // =========================================
    public static DetailFragment newInstance(String imgSrc) {
        return DetailFragment_.builder().imgSrc(imgSrc).build();
    }

    // =========================================
    //  Fragment
    // =========================================
    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);

        try {
            listener = (DetailFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new RuntimeException("Debes implementar DetailFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();

        listener = null;
    }

    // =========================================
    //  AbstractBaseFragment
    // =========================================
    @Override
    void init() {
        Picasso.with(getActivity()).
                load(imgSrc).
                placeholder(R.drawable.ic_no_image_white).
                error(R.drawable.ic_no_image_white).
                into(imageViewPhoto);
    }

    // =========================================
    //  Interaction
    // =========================================
    @Click(R.id.fragment_detail_imageView_photo)
    void onPhotoClick() {
        if (listener != null) {
            listener.onPhotoClick();
        }
    }


    public interface DetailFragmentInteractionListener {
        void onPhotoClick();
    }
}
