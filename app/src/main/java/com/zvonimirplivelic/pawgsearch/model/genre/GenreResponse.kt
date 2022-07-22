package com.zvonimirplivelic.pawgsearch.model.genre

data class GenreResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    val genre: List<Genre>
)