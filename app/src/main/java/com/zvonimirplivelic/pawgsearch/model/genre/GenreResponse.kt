package com.zvonimirplivelic.pawgsearch.model.genre

data class GenreListResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    val genreListResults: List<GenreListResult>
)