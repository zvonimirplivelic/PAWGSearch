package com.zvonimirplivelic.pawgsearch.domain

import com.zvonimirplivelic.pawgsearch.remote.model.genre.GenreGame

data class PAWGGenre(
    var id: Int,
    var name: String,
    var slug: String,
    var isSelected: Boolean? = false
)