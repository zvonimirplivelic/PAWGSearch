package com.zvonimirplivelic.rawgsearch.remote

import com.zvonimirplivelic.rawgsearch.remote.model.genre.GenreResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RAWGSearchService {
    @GET("genres")
    suspend fun getRemoteGenreList(
        @Query("key") apiKey: String
    ): GenreResponse
}