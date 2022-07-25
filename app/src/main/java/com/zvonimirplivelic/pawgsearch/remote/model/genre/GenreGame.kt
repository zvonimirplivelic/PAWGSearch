package com.zvonimirplivelic.pawgsearch.remote.model.genre

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreGame(
    val added: Int,
    val id: Int,
    val name: String,
    val slug: String
)