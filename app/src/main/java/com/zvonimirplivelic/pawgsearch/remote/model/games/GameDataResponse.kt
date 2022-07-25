package com.zvonimirplivelic.pawgsearch.remote.model.games

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GameDataResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<GameListResult>,
    val user_platforms: Boolean
)