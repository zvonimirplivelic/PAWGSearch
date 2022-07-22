package com.zvonimirplivelic.pawgsearch.model.genre

data class Genre(
    val genreGameLists: List<GenreGameList>,
    val games_count: Int,
    val id: Int,
    val image_background: String,
    val name: String,
    val slug: String
)