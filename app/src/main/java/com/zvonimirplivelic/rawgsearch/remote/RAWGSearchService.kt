package com.zvonimirplivelic.rawgsearch.remote

import com.zvonimirplivelic.rawgsearch.remote.model.games.GameDataResponse
import com.zvonimirplivelic.rawgsearch.remote.model.genre.GenreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RAWGSearchService {
    @GET("genres")
    suspend fun getRemoteGenreList(
        @Query("key") apiKey: String
    ): GenreResponse

    @GET("games")
    suspend fun getGameList(
        @Query("key") apiKey: String,
        @Query("genres") genres: String
    ): Response<GameDataResponse>
}