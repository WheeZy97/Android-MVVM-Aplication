package com.alex.microprojectmvvm.util

import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Picasso

object MicroImage {

    private lateinit var picasso: Picasso

    fun loadImageFromUrl(context: Context, url: String, imageView: ImageView) {
        init(context)

        picasso
            .load(url)
            .into(imageView)
    }

    private fun init(context: Context) {
        if (!::picasso.isInitialized)
            picasso = Picasso.Builder(context).build()
    }
}