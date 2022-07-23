package com.zvonimirplivelic.pawgsearch.model.genre

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class GenreResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    @SerializedName("results")
    val genreList: List<Genre>
)