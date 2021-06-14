package com.tmobile.subbu.view.bindingadapters

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.tmobile.subbu.R

@BindingAdapter("url")
fun ImageView.loadImage(url: String?) {
    url?.let {
        Glide
            .with(context)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.placeholder)
            .into(this)
    }
}

@BindingAdapter("color")
fun TextView.setColor(color: String?) {
    color?.let {
        setTextColor(Color.parseColor(it))
    }
}

@BindingAdapter("w", "h")
fun ImageView.setDimensions(w: Int, h: Int) {
    layoutParams = ConstraintLayout.LayoutParams(w, h)
}