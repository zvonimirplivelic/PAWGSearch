package com.zvonimirplivelic.pawgsearch.remote.model.genre

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Genre(
    var genreGames: List<GenreGame>?,
    var gamesCount: Int?,
    var id: Int,
    var imageBackground: String?,
    var name: String,
    var slug: String
)

