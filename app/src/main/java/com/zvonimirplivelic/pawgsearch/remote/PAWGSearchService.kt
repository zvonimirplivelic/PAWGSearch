package com.zvonimirplivelic.pawgsearch.remote

import com.zvonimirplivelic.pawgsearch.remote.model.genre.GenreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PAWGSearchService {
    @GET("genres")
    suspend fun getRemoteGenreList(
        @Query("key") apiKey: String
    ): GenreResponse
}