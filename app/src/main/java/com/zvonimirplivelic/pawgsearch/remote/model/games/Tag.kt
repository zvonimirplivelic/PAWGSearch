package com.zvonimirplivelic.pawgsearch.remote.model.games

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Tag(
    val games_count: Int,
    val id: Int,
    val image_background: String,
    val language: String,
    val name: String,
    val slug: String
)