package com.zvonimirplivelic.pawgsearch.model.games

data class GameDataResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<GameListResult>,
    val user_platforms: Boolean
)