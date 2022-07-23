package com.zvonimirplivelic.pawgsearch.model.genre

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("games")
    val genreGames: List<GenreGame>,
    val games_count: Int,
    val id: Int,
    val image_background: String,
    val name: String,
    val slug: String
)