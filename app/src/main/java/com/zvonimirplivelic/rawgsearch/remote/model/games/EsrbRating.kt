package com.zvonimirplivelic.rawgsearch.remote.model.games

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class EsrbRating(
    val id: Int,
    val name: String,
    val name_en: String,
    val name_ru: String,
    val slug: String
) : Parcelable