package com.zvonimirplivelic.pawgsearch.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.zvonimirplivelic.pawgsearch.db.DBGenre
import com.zvonimirplivelic.pawgsearch.repository.PAWGSearchRepository
import com.zvonimirplivelic.pawgsearch.db.getDatabase
import kotlinx.coroutines.launch
import java.io.IOException


class PAWGSearchViewModel(
    val app: Application
) : AndroidViewModel(app) {

    private val pawgSearchRepository = PAWGSearchRepository(getDatabase(app))

    val genres = pawgSearchRepository.genres

    fun storeSelectedGenres(genres: List<DBGenre>) = viewModelScope.launch {
        pawgSearchRepository.storeSelectedGenres(genres)
    }

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                pawgSearchRepository.refreshGenres()

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