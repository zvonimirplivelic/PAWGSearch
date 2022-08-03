package com.zvonimirplivelic.rawgsearch.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import androidx.lifecycle.*
import com.zvonimirplivelic.rawgsearch.RAWGSearchApplication
import com.zvonimirplivelic.rawgsearch.db.DBGenre
import com.zvonimirplivelic.rawgsearch.db.getDatabase
import com.zvonimirplivelic.rawgsearch.remote.model.games.GameDataResponse
import com.zvonimirplivelic.rawgsearch.repository.RAWGSearchRepository
import com.zvonimirplivelic.rawgsearch.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException


class RAWGSearchViewModel(
    val app: Application
) : AndroidViewModel(app) {

    private val rawgSearchRepository = RAWGSearchRepository(getDatabase(app))

    val genres = rawgSearchRepository.genres
    var selectedGenres = rawgSearchRepository.selectedGenres

    val gameList: MutableLiveData<Resource<GameDataResponse>> = MutableLiveData()
    var gameListResponse: GameDataResponse? = null

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                rawgSearchRepository.refreshGenres()

            } catch (networkError: IOException) {
                if (genres.value.isNullOrEmpty())
                    Toast.makeText(
                        app.applicationContext,
                        "Unable to dislay genres",
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }
    }

    suspend fun storeSelectedGenres(genres: List<DBGenre>) = viewModelScope.launch {
        rawgSearchRepository.storeSelectedGenres(genres)
    }

    fun getGameList(apiKey: String, queryString: String) = viewModelScope.launch {
        safeGameListCall(apiKey, queryString)
    }

    private suspend fun safeGameListCall(apiKey: String, queryString: String) {
        gameList.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = rawgSearchRepository.getGameList(apiKey, queryString)
                delay(444L)
                gameList.postValue(handleGameListCallResponse(response))
            } else {
                gameList.postValue(Resource.Error("No internet connection"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> gameList.postValue(Resource.Error("Network Failure"))
                else -> gameList.postValue(Resource.Error("Conversion Error"))
            }
        }
    }


    private fun handleGameListCallResponse(response: Response<GameDataResponse>): Resource<GameDataResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->

                gameListResponse = resultResponse

                return Resource.Success(gameListResponse ?: resultResponse)
            }
        }

        return Resource.Error(response.message())
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<RAWGSearchApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities =
            connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}