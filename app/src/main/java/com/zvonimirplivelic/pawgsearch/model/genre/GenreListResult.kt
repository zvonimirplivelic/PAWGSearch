package com.zvonimirplivelic.pawgsearch.model.genre

data class GenreListResult(
    val genreGames: List<GenreGame>,
    val games_count: Int,
    val id: Int,
    val image_background: String,
    val name: String,
    val slug: String
)