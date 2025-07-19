package com.daniel.myapp.app_friend.helpers

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imageDrawable")
fun setImageDrawable(imageView: ImageView, drawable: Drawable?) {
    drawable?.let {
        imageView.setImageDrawable(it)
    }
}