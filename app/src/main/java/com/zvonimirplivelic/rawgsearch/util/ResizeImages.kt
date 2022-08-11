package com.zvonimirplivelic.rawgsearch.util

import android.content.res.Resources

object ResizeImages {
    fun setPictureWidth(): Int {
        return (Resources.getSystem().displayMetrics.widthPixels * 0.9).toInt()
    }

    fun setPictureHeight(): Int {
        return (Resources.getSystem().displayMetrics.widthPixels / 1.8).toInt()
    }
}