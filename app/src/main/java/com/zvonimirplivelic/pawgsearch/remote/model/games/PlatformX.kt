package com.zvonimirplivelic.pawgsearch.remote.model.games

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlatformX(
    val platform: Platform
)