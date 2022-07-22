package com.zvonimirplivelic.pawgsearch

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zvonimirplivelic.pawgsearch.model.genre.GenreResponse
import com.zvonimirplivelic.pawgsearch.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException


class PAWGSearchViewModel(
    val app: Application,
) : AndroidViewModel(app) {

    private val pawgSearchRepository: PAWGSearchRepository = PAWGSearchRepository()

    val genreList: MutableLiveData<Resource<GenreResponse>> = MutableLiveData()
    var genreResponse: GenreResponse? = null

    fun getGenreList() = viewModelScope.launch {
        safeGenreListCall()
    }

    private suspend fun safeGenreListCall() {
        genreList.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = pawgSearchRepository.getGenreList()
                delay(444L)
                genreList.postValue(handleRandomFactResponse(response))
            } else {
                genreList.postValue(Resource.Error("No internet connection"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> genreList.postValue(Resource.Error("Network Failure"))
                else -> genreList.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun handleRandomFactResponse(response: Response<GenreResponse>): Resource<GenreResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->

                genreResponse = resultResponse

                return Resource.Success(genreResponse ?: resultResponse)
            }
        }

        return Resource.Error(response.message())
    }


    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<PAWGSearchApplication>().getSystemService(
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