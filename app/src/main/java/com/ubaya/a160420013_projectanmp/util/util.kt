package com.ubaya.a160420013_projectanmp.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.a160420013_projectanmp.R

fun ImageView.loadImage(url:String?, progressBar: ProgressBar){
    Picasso.get()
        .load(url)
        .resize(320, 320)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(this, object: Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }
            override fun onError(e: Exception?) {
            }
        })
}