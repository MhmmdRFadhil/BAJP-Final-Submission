package com.ryz.moviecatalogue.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.ryz.moviecatalogue.R

fun ImageView.loadImageUrl(image: Int?) {
    Glide.with(this.context)
        .load(image)
        .transform(RoundedCorners(25))
        .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24))
        .error(R.drawable.ic_baseline_broken_image_24)
        .into(this)
}