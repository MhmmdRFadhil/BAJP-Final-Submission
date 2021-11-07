package com.ryz.moviecatalogue.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.ryz.moviecatalogue.BuildConfig.IMAGE_URL
import com.ryz.moviecatalogue.R

fun ImageView.loadImageUrl(url: String) {
    Glide.with(this.context)
        .load(IMAGE_URL + url)
        .transform(RoundedCorners(25))
        .error(R.drawable.ic_baseline_broken_image_24)
        .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24))
        .into(this)
}