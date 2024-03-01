package com.example.test10.presentation.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImagesWithGlide(drawable: Int) {
    Glide.with(this)
        .load(drawable)
        .into(this)
}