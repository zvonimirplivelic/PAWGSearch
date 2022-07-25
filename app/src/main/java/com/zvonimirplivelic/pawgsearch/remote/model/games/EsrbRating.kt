package com.zvonimirplivelic.pawgsearch.remote.model.games

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EsrbRating(
    val id: Int,
    val name: String,
    val name_en: String,
    val name_ru: String,
    val slug: String
)