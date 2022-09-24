@file:Suppress("unused", "UNUSED_PARAMETER")

package com.example.sampleaction.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.sampleaction.R


/*********************************************************
 * Class   :  BindingAdapters
 * Author  :  Arun Nair
 * Created :  15/09/2022
 *******************************************************
 * Purpose :
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/

/***************************************
 * Setting Observers
 ***************************************/
@BindingAdapter("isVisible")
fun View.showHide(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}


/***************************************
 * Setting Observers
 ***************************************/
@BindingAdapter("loadImageAsGif")
fun ImageView.loadImageAsGif(image: Drawable) {
    Glide.with(context).load(image).into(this)
}

/***************************************
 * Setting Observers
 ***************************************/
@BindingAdapter("loadImageAsGif")
fun ImageView.loadImageAsGif(imageResId: Int) {
    Glide.with(context).asGif().load(imageResId).into(this)
}

/***************************************
 * Setting Observers
 ***************************************/
@BindingAdapter("loadGif")
fun ImageView.loaderImageAsGif(ignored: Boolean) {
    Glide.with(context).load(R.drawable.ic_loading).into(this)
}

/***************************************
 * Setting Observers
 ***************************************/
@BindingAdapter("errorRes")
fun setTextError(view: TextView, resId: Int?) {
    return try {
        view.error = resId?.let {
            view.context.resources.getString(it)
        }
    } catch (e: Exception) {
        view.error = null
    }
}


/***************************************
 * Setting Observers
 ***************************************/
@BindingAdapter("imageUrl")
fun ImageView.displayImageFromUrl(imageUrl: String?) {
    imageUrl?.let {
        Glide.with(context)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .override(width, height)
            .placeholder(R.drawable.animated_loading)
            .error(R.drawable.ic_generic_error)
            .dontAnimate()
            .into(this)
    }
}

