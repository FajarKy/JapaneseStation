package com.hafiizh.japanesestation.databinding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.hafiizh.japanesestation.R;
import com.squareup.picasso.Picasso;

/**
 * Created by HAFIIZH on 10/23/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public final class ImageBindingAdapter {
    @BindingAdapter(value = "url")
    public static void loadImageUrl(ImageView view, String url) {
        if (url != null && !url.equals(""))
            Picasso.with(view.getContext()).load(url).placeholder(R.mipmap.ic_launcher).into(view);
    }
}