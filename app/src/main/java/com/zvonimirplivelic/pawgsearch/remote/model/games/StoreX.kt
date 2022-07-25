package com.zvonimirplivelic.pawgsearch.remote.model.games

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StoreX(
    val id: Int,
    val name: String,
    val slug: String
)