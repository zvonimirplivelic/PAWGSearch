package com.zvonimirplivelic.rawgsearch.remote.model.games

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class GameDataResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<GameListResult>,
    val user_platforms: Boolean
) : Parcelable