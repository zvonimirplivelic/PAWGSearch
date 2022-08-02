package com.zvonimirplivelic.rawgsearch.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.zvonimirplivelic.rawgsearch.db.DBGenre
import com.zvonimirplivelic.rawgsearch.db.getDatabase
import com.zvonimirplivelic.rawgsearch.repository.RAWGSearchRepository
import kotlinx.coroutines.launch
import java.io.IOException


class RAWGSearchViewModel(
    val app: Application
) : AndroidViewModel(app) {

    private val rawgSearchRepository = RAWGSearchRepository(getDatabase(app))

    val genres = rawgSearchRepository.genres
    var selectedGenres = rawgSearchRepository.selectedGenres

    suspend fun storeSelectedGenres(genres: List<DBGenre>) = viewModelScope.launch {
        rawgSearchRepository.storeSelectedGenres(genres)
    }

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
}