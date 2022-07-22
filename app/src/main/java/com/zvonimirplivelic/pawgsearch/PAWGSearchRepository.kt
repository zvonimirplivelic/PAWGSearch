package com.zvonimirplivelic.pawgsearch

import com.zvonimirplivelic.pawgsearch.remote.RetrofitInstance
import com.zvonimirplivelic.pawgsearch.util.Constants.API_KEY


class PAWGSearchRepository() {

    suspend fun getGenreList() =
        RetrofitInstance.api.getRemoteGenreList(API_KEY)
}