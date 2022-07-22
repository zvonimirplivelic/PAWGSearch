package com.zvonimirplivelic.pawgsearch.remote

import com.zvonimirplivelic.pawgsearch.model.genre.GenreListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PAWGSearchService {
    @GET("genres")
    suspend fun getRemoteGenreList(
        @Query("key") apiKey: String
    ): Response<GenreListResponse>
}