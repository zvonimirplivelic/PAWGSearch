package com.zvonimirplivelic.rawgsearch.remote.model.games

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Rating(
    val count: Int,
    val id: Int,
    val percent: Double,
    val title: String
) : Parcelable