package com.zvonimirplivelic.pawgsearch

import com.zvonimirplivelic.pawgsearch.remote.RetrofitInstance


class PAWGSearchRepository() {

    suspend fun getGenreList(apiKey: String) =
        RetrofitInstance.api.getRemoteGenreList(apiKey)
}