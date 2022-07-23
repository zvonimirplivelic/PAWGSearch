package com.zvonimirplivelic.pawgsearch.model.genre

import androidx.room.Entity

data class GenreGame(
    val added: Int,
    val id: Int,
    val name: String,
    val slug: String
)