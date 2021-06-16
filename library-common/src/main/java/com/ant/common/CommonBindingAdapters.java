package com.ant.common;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class CommonBindingAdapters {

    @BindingAdapter(value = {"imageUrl", "cornerRadius"}, requireAll = false)
    public static void bindImage(ImageView view, String url, Float cornerRadius) {
        if (!TextUtils.isEmpty(url)) {
            RequestBuilder builder = Glide.with(view).load(url).transition(withCrossFade());
            if (cornerRadius != null) {
                builder.apply(RequestOptions.bitmapTransform(new RoundedCorners(cornerRadius.intValue())));
            }
            builder.into(view);
        }
    }

    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, Boolean value) {
        view.setVisibility(value ? View.VISIBLE : View.GONE);
    }
}